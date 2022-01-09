package com.ondc.tw.digitalcatalog.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;

    private double price;

    private long quantity;

    private String sku;

    private String weight;

    private String unit;

    private String mrp;

    private String image128;

    private String image256;

    private String parentCategory;

    private String subCategory;
}
