package com.cozyhome.onlineshop.productservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cozyhome.onlineshop.productservice.dto.ImageDto;
import com.cozyhome.onlineshop.productservice.dto.PopUpImageDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ProductCardImageDto;
import com.cozyhome.onlineshop.productservice.dto.request.ProductColorDto;
import com.cozyhome.onlineshop.productservice.model.ImageProduct;
import com.cozyhome.onlineshop.productservice.repository.ImageProductRepository;
import com.cozyhome.onlineshop.productservice.service.ImageService;
import com.cozyhome.onlineshop.productservice.service.builder.ImageBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ImageProductRepository imageProductRepository;
	private final ImageBuilder imageBuilder;

    @Override
    public ImageDto getPreviewImageForProductByColor(ProductColorDto productColor) {
        ImageProduct image = imageProductRepository.findByProductSkuCodeAndColorIdAndMainPhotoTrue(
                productColor.getProductSkuCode(), productColor.getColorHex());
        return imageBuilder.buildPreviewImageDto(image);
    }

	@Override
	public List<PopUpImageDto> getPopUpImageForProductByColor(ProductColorDto productColor) {
		List<ImageProduct> popUpImages = imageProductRepository
				.findByProductSkuCodeAndColorId(productColor.getProductSkuCode(), productColor.getColorHex());
		return imageBuilder.buildPopUpImageDtos(popUpImages);
	}

	@Override
	public List<ProductCardImageDto> getProductCardImagesByColor(ProductColorDto productColor) {
		List<ImageProduct> productCardImages = imageProductRepository
				.findByProductSkuCodeAndColorId(productColor.getProductSkuCode(), productColor.getColorHex());
		return imageBuilder.buildProductCardImageDtos(productCardImages);
	}
}
