package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.MasterItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    List<MasterItem> masterItemList = new ArrayList<>();

    public void searchItems(String query) {
        if(barcodeValidator(query)){
            List<MasterItem> collect = masterItemList.stream().filter(item -> item.getBarcode().equals(query)).collect(Collectors.toList());
        }
        else{

        }
    }

     boolean barcodeValidator(String query) {
        char array[] = query.toCharArray();
        for(char index : array){
            try {
                Integer.parseInt(String.valueOf(index));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
