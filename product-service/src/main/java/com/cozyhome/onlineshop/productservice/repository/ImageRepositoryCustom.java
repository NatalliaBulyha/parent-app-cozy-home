package com.cozyhome.onlineshop.productservice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cozyhome.onlineshop.productservice.model.Color;
import com.cozyhome.onlineshop.productservice.model.ImageProduct;

@Repository
public interface ImageRepositoryCustom {

	List<ImageProduct> findImagesByMainPhotoAndProductSkuCodeIn(List<String> skuCodes, boolean isMain);

    List<Color> findColorsByProductSkuCodeIn(List<String> productSkuCodes);
    
    List<Color> findColorsByProductSkuCode(String productSkuCode);

    Map<String, List<Color>> groupColorsByProductSkuCodeIn(List<String> productSkuCodes);

}
