package com.ondc.tw.digitalcatalog.controller;

import com.ondc.tw.digitalcatalog.dto.StoreDTO;
import com.ondc.tw.digitalcatalog.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping(path = "store/create")
    public void createStore(@RequestBody StoreDTO storeDTO) {
        storeService.createStore(storeDTO);
    }

}
