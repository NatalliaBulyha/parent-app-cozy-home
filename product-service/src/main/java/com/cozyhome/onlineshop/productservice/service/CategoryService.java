package com.cozyhome.onlineshop.productservice.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.cozyhome.onlineshop.productservice.dto.CategoryWithIconDto;
import com.cozyhome.onlineshop.productservice.dto.CategoryWithPhotoDto;
import com.cozyhome.onlineshop.productservice.dto.CategoryWithSubCategoriesDto;

public interface CategoryService {
    List<CategoryWithIconDto> getCategoryWithIcon();
    List<ObjectId> getCategoriesIdsByParentId(String parentId);
    List<CategoryWithSubCategoriesDto> getCategoriesWithPhoto();
    List<CategoryWithPhotoDto> getCategoriesForHomepage();
}
