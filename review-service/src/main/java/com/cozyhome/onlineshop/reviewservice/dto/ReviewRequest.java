package com.cozyhome.onlineshop.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReviewRequest {
    private byte rating;
    private String comment;
    private String userName;
    private String productSkuCode;
}
