package com.cozyhome.onlineshop.productservice.service;

import java.util.List;

import com.cozyhome.onlineshop.productservice.dto.CollectionDto;

public interface CollectionService {
    List<CollectionDto> getCollections();
}
