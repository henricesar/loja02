package com.github.ericomonteiro.smartstock.model.dto.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.Product.DETAIL_NOT_BLANK;
import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.Product.NAME_NOT_BLANK;
import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.Product.PRICE_MUST_GRATER_ZERO;


@Data
public class ProductCreateDto {
    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = DETAIL_NOT_BLANK)
    private String details;

    @Positive(message = PRICE_MUST_GRATER_ZERO)
    private Float price;
}
