package com.ondc.tw.digitalcatalog.controller;

import com.ondc.tw.digitalcatalog.model.MasterProduct;
import com.ondc.tw.digitalcatalog.service.CatalogService;
import com.ondc.tw.digitalcatalog.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class MasterDataController {
    @Autowired
    private MasterDataService mastrerDataService;

    @GetMapping(path = "catalog/master/search")
    public List<MasterProduct> searchProducts(@RequestParam String query) {
        return mastrerDataService.searchProducts(query);
    }
}
