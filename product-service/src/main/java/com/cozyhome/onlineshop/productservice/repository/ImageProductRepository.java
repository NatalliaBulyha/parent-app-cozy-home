package com.cozyhome.onlineshop.productservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cozyhome.onlineshop.productservice.model.ImageProduct;

import java.util.List;

public interface ImageProductRepository extends MongoRepository<ImageProduct, ObjectId> {

    ImageProduct findByProductSkuCodeAndColorIdAndMainPhotoTrue(String productSkuCode, String colorId);

    List<ImageProduct> findByProductSkuCodeAndColorId(String productSkuCode, String colorId);

}
