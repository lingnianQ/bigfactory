package com.mall.pojo.dto;

import lombok.Data;

@Data
public class CartUpdateDTO {
    private Integer quantity;

    private Boolean selected;
}
