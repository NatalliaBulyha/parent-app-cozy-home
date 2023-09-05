package com.cozyhome.onlineshop.productservice.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cozyhome.onlineshop.productservice.model.ImageCategory;

public interface ImageCategoryRepository extends MongoRepository<ImageCategory, ObjectId> {
	
    List<ImageCategory> findAllByCatalogAndCategoryIn(boolean catalog, List<ObjectId> ids);

    List<ImageCategory> findAllByCatalogFalse();
}
