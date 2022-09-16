package com.mall.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO {
    //@NotBlank 用于 String 判断空格
    //@NotEmpty 用于集合
    //@NotNull
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
}
