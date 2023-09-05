package com.cozyhome.onlineshop.productservice.service;

import java.util.List;

import com.cozyhome.onlineshop.productservice.dto.ImageDto;
import com.cozyhome.onlineshop.productservice.dto.PopUpImageDto;
import com.cozyhome.onlineshop.productservice.dto.productcard.ProductCardImageDto;
import com.cozyhome.onlineshop.productservice.dto.request.ProductColorDto;

public interface ImageService {

	ImageDto getPreviewImageForProductByColor(ProductColorDto previewImageProduct);

    List<PopUpImageDto> getPopUpImageForProductByColor(ProductColorDto popUpImageProduct);
    
    List<ProductCardImageDto> getProductCardImagesByColor(ProductColorDto request);
}
