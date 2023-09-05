package com.cozyhome.onlineshop.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cozyhome.onlineshop.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{$and:[{'status': {$ne: 'DELETED'}},{'subCategory': ?0}]}")
    Page<Product> findAllByStatusNotDeletedAndCategoryId(ObjectId categoryId, Pageable pageable);

    @Query("{$and:[{'status': {$ne: 'DELETED'}},{'subCategory': {$in: ?0}}]}")
    Page<Product> findAllByStatusNotDeletedAndCategoryIdIn(List<ObjectId> categoryIds, Pageable pageable);

    @Query("{$and:[{'status': {$ne: 'DELETED'}},{'subCategory': {$in: ?0}}]}")
    List<Product> findAllByStatusNotDeletedAndCategoryIdIn(List<ObjectId> categoryIds);

    Optional<Product> findBySkuCode(String skuCode);//TODO add by status not deleted

    @Query("{$and: [{'status': {$ne: 'DELETED'}},{'available': true}, {'collection._id': ?0}, {'skuCode': {$ne: ?1}}]}")
    List<Product> findAllByStatusNotDeletedAndCollectionIdExcludeSkuCode(ObjectId collectionId, String skuCodeToExclude);
}
