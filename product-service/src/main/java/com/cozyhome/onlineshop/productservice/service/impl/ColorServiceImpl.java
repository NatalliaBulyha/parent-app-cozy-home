package com.cozyhome.onlineshop.productservice.service.impl;

import com.cozyhome.onlineshop.productservice.dto.ColorDto;
import com.cozyhome.onlineshop.productservice.model.Color;
import com.cozyhome.onlineshop.productservice.repository.ColorRepository;
import com.cozyhome.onlineshop.productservice.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ColorDto> getColors() {
        List<Color> colors = colorRepository.findAllByActive(true);
        return colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).toList();
    }
}
