package com.cozyhome.onlineshop.productservice.service.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cozyhome.onlineshop.productservice.dto.CollectionDto;
import com.cozyhome.onlineshop.productservice.dto.ColorDto;
import com.cozyhome.onlineshop.productservice.dto.ProductDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ColorQuantityStatusDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ProductCardDto;
import com.cozyhome.onlineshop.productservice.model.Category;
import com.cozyhome.onlineshop.productservice.model.Color;
import com.cozyhome.onlineshop.productservice.model.ImageProduct;
import com.cozyhome.onlineshop.productservice.model.Material;
import com.cozyhome.onlineshop.productservice.model.Product;
import com.cozyhome.onlineshop.productservice.model.enums.ProductQuantityStatus;
import com.cozyhome.onlineshop.productservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class ProductBuilder {
	private final ImageBuilder imageBuilder;
	private final ModelMapper modelMapper;
	private final CategoryRepository categoryRepository;

	private static final BigDecimal NULL_PERCENT = new BigDecimal("0");
	private static final String ROUND_FLOAT_ONE_DECIMAL_PLACE = "%.0f";

	@Value("${digits.after.decimal}")
	private int digitsAfterDecimal;

	public List<ProductDto> buildProductDtoList(List<Product> products, List<ImageProduct> images,
			Map<String, List<Color>> productColors) {
		Map<String, List<ImageProduct>> imageMap = getImageMap(images);
		return products.stream().map(product -> buildProductDto(product, imageMap.get(product.getSkuCode()),
				productColors.get(product.getSkuCode()))).toList();
	}

	public ProductDto buildProductDto(Product product, List<ImageProduct> images, List<Color> colors) {
		BigDecimal discount = BigDecimal.valueOf(product.getDiscount());

		ProductDto productDto = ProductDto.builder().skuCode(product.getSkuCode()).name(product.getName())
				.shortDescription(product.getShortDescription())
				.price(roundBigDecimalToZeroDecimalPlace(product.getPrice()))
				.imageDtoList(imageBuilder.buildImageDtoList(images))
				.colorDtoList(colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).toList())
				.productQuantityStatus(ProductQuantityStatus.getDescriptionForFalseAvailability(product.isAvailable()))
				.build();

		if (!discount.equals(NULL_PERCENT)) {
			productDto.setDiscount(product.getDiscount());
			productDto.setPriceWithDiscount(roundBigDecimalToZeroDecimalPlace(product.getPriceWithDiscount()));
		}
		return productDto;
	}

	public ProductCardDto buildProductCardDto(Product product, List<ImageProduct> images, List<Color> colors) {
		byte countOfReviews = (byte) new Random().nextInt(4);
		byte defaultNumberOfDoors = 0;
		byte defaultNumberOfDrawers = 0;
		float defaultBedLength = 0.0f;
		float defaultBedWidth = 0.0f;

		BigDecimal discount = BigDecimal.valueOf(product.getDiscount());

		Optional<Byte> numberOfDoorsOpt = Optional.ofNullable(product.getNumberOfDoors());
		Optional<Byte> numberOfDrawersOpt = Optional.ofNullable(product.getNumberOfDrawers());
		Optional<Float> bedLengthOpt = Optional.ofNullable(product.getBedLength());
		Optional<Float> bedWidthOpt = Optional.ofNullable(product.getBedWidth());

		String categoryName = categoryRepository.findById(product.getSubCategory().getParentId()).map(Category::getName)
				.orElse("");
		String quantityStatus = ProductQuantityStatus.getByProductQuantity(new Random().nextInt(6)).getDescription();
		ProductCardDto productCardDto = ProductCardDto.builder().categoryName(categoryName)
				.subCategoryName(product.getSubCategory().getName()).skuCode(product.getSkuCode())
				.name(product.getName()).description(product.getDescription())
				.price(roundBigDecimalToZeroDecimalPlace(product.getPrice()))
				.images(imageBuilder.buildProductCardImageDtos(images)).colors(setColors(colors, quantityStatus))
				.averageRating(roundFloatToOneDecimalPlace(product.getAverageRating())).countOfReviews(countOfReviews)
				.materials(product.getMaterials().stream().map(Material::getName).toList())
				.collection(modelMapper.map(product.getCollection(), CollectionDto.class))
				.weight(roundFloatToOneDecimalPlace(product.getWeight()))
				.height(roundFloatToOneDecimalPlace(product.getHeight()))
				.width(roundFloatToOneDecimalPlace(product.getWidth()))
				.depth(roundFloatToOneDecimalPlace(product.getDepth())).maxLoad(product.getMaxLoad())
				.quantityStatus(quantityStatus).numberOfDoors(numberOfDoorsOpt.orElse(defaultNumberOfDoors))
				.numberOfDrawers(numberOfDrawersOpt.orElse(defaultNumberOfDrawers))
				.bedLength(roundFloatToOneDecimalPlace(bedLengthOpt.orElse(defaultBedLength)))
				.bedWidth(roundFloatToOneDecimalPlace(bedWidthOpt.orElse(defaultBedWidth))).build();
		if (product.getTransformation() != null) {
			productCardDto.setSubCategoryName(categoryName);
		}
		if (product.getHeightRegulation() != null) {
			productCardDto.setHeightAdjustment(product.getHeightRegulation());
		}

		if (!discount.equals(NULL_PERCENT)) {
			productCardDto.setDiscount(product.getDiscount());
			productCardDto.setPriceWithDiscount(roundBigDecimalToZeroDecimalPlace(product.getPriceWithDiscount()));
		}
		return productCardDto;
	}

	// temporal method until will not created an inventory service
	private List<ColorQuantityStatusDto> setColors(List<Color> colors, String quantityStatus) {
		List<ColorQuantityStatusDto> colorsDto = new ArrayList<>();
		for (int i = 0; i < colors.size(); i++) {
			if (i == 0) {
				colorsDto.add(
						new ColorQuantityStatusDto(colors.get(i).getId(), colors.get(i).getName(), quantityStatus));
			} else {
				String status = ProductQuantityStatus.getByProductQuantity(new Random().nextInt(6)).getDescription();
				colorsDto.add(new ColorQuantityStatusDto(colors.get(i).getId(), colors.get(i).getName(), status));
			}
		}
		return colorsDto;
	}

	private Float roundFloatToOneDecimalPlace(Float floatValue) {
		return Float.valueOf(String.format(ROUND_FLOAT_ONE_DECIMAL_PLACE, floatValue));
	}

	private BigDecimal roundBigDecimalToZeroDecimalPlace(BigDecimal value) {
		return value.setScale(digitsAfterDecimal, RoundingMode.HALF_UP);
	}

	private Map<String, List<ImageProduct>> getImageMap(List<ImageProduct> images) {
		return images.stream()
				.collect(Collectors.groupingBy(image -> image.getProduct().getSkuCode(), Collectors.toList()));
	}
}
