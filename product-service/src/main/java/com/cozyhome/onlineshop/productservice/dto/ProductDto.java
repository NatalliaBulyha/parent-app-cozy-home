package com.cozyhome.onlineshop.productservice.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private String skuCode;
    private String name;
    private String shortDescription;
    private BigDecimal price;
    private Byte discount;
    private BigDecimal priceWithDiscount;
    private List<ImageDto> imageDtoList;
    private List<ColorDto> colorDtoList;
    private String productQuantityStatus;

}
