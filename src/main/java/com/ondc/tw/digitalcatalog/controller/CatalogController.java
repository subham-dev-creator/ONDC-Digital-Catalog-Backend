package com.ondc.tw.digitalcatalog.controller;

import com.ondc.tw.digitalcatalog.model.MasterItem;
import com.ondc.tw.digitalcatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping(path = "/catalog/item/add")
    public void addItem() {

    }

    @GetMapping(path = "catalog/master/search")
    public List<MasterItem> searchItems(@RequestParam String query) {
        return catalogService.searchItems(query);
    }

}
