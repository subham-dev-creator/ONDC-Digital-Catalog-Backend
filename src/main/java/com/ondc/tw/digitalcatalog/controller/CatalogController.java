package com.ondc.tw.digitalcatalog.controller;

import com.ondc.tw.digitalcatalog.dto.ProductDto;
import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import com.ondc.tw.digitalcatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping(path = "/catalog/products/add")
    public void addProducts(@RequestBody List<Product> products) {
        catalogService.addProducts(products);
    }

    @GetMapping(path = "/catalog/products/list")
    public List<ProductDto> listProducts() {
        return catalogService.listProducts();
    }
}
