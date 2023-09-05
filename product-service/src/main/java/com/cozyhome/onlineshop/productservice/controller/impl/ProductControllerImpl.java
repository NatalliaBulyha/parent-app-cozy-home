package com.cozyhome.onlineshop.productservice.controller.impl;

import java.util.List;

import com.cozyhome.onlineshop.productservice.controller.ProductController;
import com.cozyhome.onlineshop.productservice.dto.request.PageableDto;
import com.cozyhome.onlineshop.productservice.validation.ValidId;
import com.cozyhome.onlineshop.productservice.validation.ValidSkuCode;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cozyhome.onlineshop.productservice.dto.ProductDto;
import com.cozyhome.onlineshop.productservice.dto.ProductStatusDto;
import com.cozyhome.onlineshop.productservice.dto.filter.FilterDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ProductCardDto;
import com.cozyhome.onlineshop.productservice.dto.request.ProductColorDto;
import com.cozyhome.onlineshop.productservice.dto.request.SortDto;
import com.cozyhome.onlineshop.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.basePath}/product")
//@Validated
public class ProductControllerImpl implements ProductController {

	private final ProductService productService;

	@Override
    @GetMapping("/statuses")
	public ResponseEntity<List<ProductStatusDto>> getProductsStatuses() {
		return new ResponseEntity<>(productService.getProductStatuses(), HttpStatus.OK);
	}

    @Override
    @GetMapping("/homepage/status")
    public ResponseEntity<List<ProductDto>> getRandomProductsByStatus(@RequestParam Byte status,
                                                                      @RequestParam int countOfProducts) {
        return new ResponseEntity<>(productService.getRandomProductsByStatus(status, countOfProducts), HttpStatus.OK);
    }

    @Override
    @GetMapping("/homepage/category_status")
    public ResponseEntity<List<ProductDto>> getRandomProductsByStatusAndCategoryId(@RequestParam Byte status,
                                                                                   @RequestParam @ValidId String categoryId,
                                                                                   @RequestParam int countOfProducts) {
        return new ResponseEntity<>(productService.getRandomProductsByStatusAndCategoryId(status, categoryId, countOfProducts),
            HttpStatus.OK);
    }

    @Override
    @GetMapping("/catalog/category")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@RequestParam @ValidId String categoryId,
                                                                    @Valid PageableDto pageable) {
        return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId, pageable), HttpStatus.OK);
    }

    @Override
    @PostMapping("/filter")
    public ResponseEntity<List<ProductDto>> filterProductsByCriterias(@RequestBody FilterDto filter,
                                                                      @Valid PageableDto pageable,
                                                                      @Valid SortDto sortDto) {
        return new ResponseEntity<>(productService.filterProductsByCriterias(filter, pageable, sortDto), HttpStatus.OK);
    }

    @Override
    @PostMapping("/filter/parameters")
    public ResponseEntity<FilterDto> getFilterParameters(@RequestBody @Valid FilterDto filter, @RequestParam byte size) {
        return new ResponseEntity<>(productService.getFilterParameters(filter, size), HttpStatus.OK);
    }

    @Override
    @PostMapping("/skuCode")
    public ResponseEntity<ProductCardDto> getProductCardForColorBySkuCode(@RequestBody @Valid ProductColorDto dto){
        return ResponseEntity.ok().body(productService.getForColorBySkuCode(dto));
    }

    @Override
    @GetMapping("/collection")
    public ResponseEntity<List<ProductDto>> getProductsByCollection(@RequestParam String collectionId,
                                                                    @RequestParam @ValidSkuCode String productSkuCode){
        return ResponseEntity.ok().body(productService.getProductsByCollectionExcludeSkuCode(collectionId, productSkuCode));
    }
}
