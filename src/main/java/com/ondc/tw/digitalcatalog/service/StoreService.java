package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.dto.StoreDTO;
import com.ondc.tw.digitalcatalog.model.Store;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    public List<Store> storeList = new ArrayList<>();
    public void createStore(StoreDTO storeDTO) {
        Store store = new Store();
        store.setName(storeDTO.getName());
        store.setContactNumberList(storeDTO.getContactNumberList());
        store.setLocation(storeDTO.getLocation());
        storeList.add(store);
    }
}
