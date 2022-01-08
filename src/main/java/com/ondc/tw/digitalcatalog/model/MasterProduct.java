package com.ondc.tw.digitalcatalog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public final class MasterProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String barcode;

    private String sku;

    private String weight;

    private String unit;

    private String mrp;

    private String image128;

    private String image256;

    private String parentCategory;

    private String subCategory;

    public MasterProduct(String barcode, String sku, String weight, String unit, String mrp, String image128, String image256, String parentCategory, String subCategory) {
        this.barcode = barcode;
        this.sku = sku;
        this.weight = weight;
        this.unit = unit;
        this.mrp = mrp;
        this.image128 = image128;
        this.image256 = image256;
        this.parentCategory = parentCategory;
        this.subCategory = subCategory;
    }
}
