package com.mall.pojo.dto;

import lombok.Data;

@Data
public class CartAddDTO {

    private Integer productId;

    private Boolean selected = true;
}
