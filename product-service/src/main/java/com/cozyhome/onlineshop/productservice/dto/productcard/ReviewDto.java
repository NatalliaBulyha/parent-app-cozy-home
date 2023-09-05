package com.cozyhome.onlineshop.productservice.dto.productcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDto {
    private String userName;
    private String review;
    private byte rating;
    private LocalDate date;
}
