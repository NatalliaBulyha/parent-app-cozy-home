package com.cozyhome.onlineshop.productservice.dto.productcard;

import java.math.BigDecimal;
import java.util.List;

import com.cozyhome.onlineshop.productservice.dto.CollectionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductCardDto {
    private String categoryName;
    private String subCategoryName;

    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
    private byte discount;
    private BigDecimal priceWithDiscount;
//    private List<ColorDto> colors;
    private List<ColorQuantityStatusDto> colors;

    private float averageRating;

    private byte countOfReviews;
    private List<ReviewDto> reviews;

    private List<ProductCardImageDto> images;

    private List<String> materials;
    private CollectionDto collection;
    private boolean transformation;
    private boolean heightAdjustment;
    private float weight;
    private float height;
    private float width;
    private float depth;
    private byte numberOfDoors;
    private byte numberOfDrawers;
    private float bedLength;
    private float bedWidth;
    private short maxLoad;

    private String quantityStatus;
}
