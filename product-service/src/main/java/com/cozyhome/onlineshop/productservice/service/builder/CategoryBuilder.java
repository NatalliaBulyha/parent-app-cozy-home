package com.cozyhome.onlineshop.productservice.service.builder;

import com.cozyhome.onlineshop.productservice.dto.CategoryDto;
import com.cozyhome.onlineshop.productservice.dto.CategoryWithPhotoDto;
import com.cozyhome.onlineshop.productservice.dto.CategoryWithSubCategoriesDto;
import com.cozyhome.onlineshop.productservice.model.Category;
import com.cozyhome.onlineshop.productservice.model.ImageCategory;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryBuilder {
    private final ModelMapper modelMapper;
    @Value("${image.category.path.base}")
    private String imagePathBase;
    public List<CategoryWithSubCategoriesDto> buildParentCategoryWithCategoriesDtoList(List<Category> categories,
                                                                                          List<ImageCategory> images) {
        Map<ObjectId, List<Category>> categoryMap = getCategoryMap(categories);
        List<CategoryWithSubCategoriesDto> parentCategoryWithCategoriesDtoList = new ArrayList<>();
        for (Category category : categoryMap.get(null)) {
            CategoryWithSubCategoriesDto categoryDto = CategoryWithSubCategoriesDto.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .categoryDtos(buildCategoryDtoList(categoryMap.get(category.getId())))
                .build();
            Optional<String> imagePath = images.stream()
                .filter(imageCategory -> imageCategory.getCategory().getId().equals(category.getId()))
                .map(ImageCategory::getCategoryImageName).findFirst();
            imagePath.ifPresent(s -> categoryDto.setCategoryImagePath(imagePathBase + s));
            parentCategoryWithCategoriesDtoList.add(categoryDto);
        }
        return parentCategoryWithCategoriesDtoList;
    }

    private List<CategoryDto> buildCategoryDtoList(List<Category> categories) {
        return categories.stream().map(x -> modelMapper.map(x, CategoryDto.class)).toList();
    }

    private Map<ObjectId, List<Category>> getCategoryMap(List<Category> images) {
        List<Category> categories = new ArrayList<>();
        Map<ObjectId, List<Category>> map = images.stream()
            .peek(x -> {
                if (x.getParentId() == null) {
                    categories.add(x);
                }
            })
            .filter(x -> x.getParentId() != null)
            .collect(Collectors.groupingBy(Category::getParentId, Collectors.toList()));
        map.put(null, categories);
        return map;
    }

    public CategoryWithPhotoDto mapToCategoryForHomepageDto(ImageCategory imageCategory) {
        return CategoryWithPhotoDto.builder()
            .categoryId(imageCategory.getCategory().getId().toString())
            .categoryName(imageCategory.getCategory().getName())
            .imagePath(imagePathBase + imageCategory.getCategoryImageName())
            .imageSize(imageCategory.getImageSize())
            .build();
    }
}
