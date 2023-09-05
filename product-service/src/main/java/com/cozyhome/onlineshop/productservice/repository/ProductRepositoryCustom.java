package com.cozyhome.onlineshop.productservice.repository;

import java.util.List;

import com.cozyhome.onlineshop.productservice.dto.filter.FilterDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import com.cozyhome.onlineshop.productservice.model.Product;
import com.cozyhome.onlineshop.productservice.model.enums.ProductStatus;

public interface ProductRepositoryCustom {

	List<Product> getRandomByStatusAndInStock(ProductStatus status, int count);
	List<Product> getRandomByStatusAndCategoryIdAndInStock(ProductStatus status, List<ObjectId> categoriesIds, int count);
	List<Product> filterProductsByCriterias(FilterDto dto, Pageable page);

}
