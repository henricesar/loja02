package com.github.ericomonteiro.smartstock.config.error;

public class ErrorKeys {

    public static class General {
        public static final String UNEXPECTED_ERROR = "general-500";
    }

    public static class Product {
        public static final String NAME_NOT_BLANK = "product-1";
        public static final String DETAIL_NOT_BLANK = "product-2";
        public static final String PRICE_MUST_GRATER_ZERO = "product-3";
    }

}
