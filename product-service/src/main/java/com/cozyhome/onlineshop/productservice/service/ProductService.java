package com.cozyhome.onlineshop.productservice.service;

import java.util.List;

import com.cozyhome.onlineshop.productservice.dto.ProductDto;
import com.cozyhome.onlineshop.productservice.dto.ProductStatusDto;
import com.cozyhome.onlineshop.productservice.dto.filter.FilterDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ProductCardDto;
import com.cozyhome.onlineshop.productservice.dto.request.PageableDto;
import com.cozyhome.onlineshop.productservice.dto.request.ProductColorDto;
import com.cozyhome.onlineshop.productservice.dto.request.SortDto;

public interface ProductService {
    List<ProductStatusDto> getProductStatuses();

    List<ProductDto> getRandomProductsByStatus(Byte status, int productCount);

    List<ProductDto> getProductsByCategoryId(String categoryId, PageableDto pageable);

    List<ProductDto> getRandomProductsByStatusAndCategoryId(Byte status, String categoryId, int countOfProducts);

    List<ProductDto> filterProductsByCriterias(FilterDto filter, PageableDto pageable, SortDto sortDto);

    FilterDto getFilterParameters(FilterDto filter, byte size);

    ProductCardDto getForColorBySkuCode(ProductColorDto dto);

    List<ProductDto> getProductsByCollectionExcludeSkuCode(String collection, String skuCodeToExclude);

}
