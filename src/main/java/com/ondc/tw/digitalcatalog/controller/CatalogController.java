package com.ondc.tw.digitalcatalog.controller;

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

    @PostMapping(path = "/catalog/item/add")
    public void addItem(Product product) {
        catalogService.addItem(product);
    }

    @GetMapping(path = "catalog/master/search")
    public List<MasterProduct> searchItems(@RequestParam String query) {
        return catalogService.searchItems(query);
    }

    @GetMapping(path = "/catalog/item/list")
    public List<Product> listItem() {
        return catalogService.listItem();
    }
}
