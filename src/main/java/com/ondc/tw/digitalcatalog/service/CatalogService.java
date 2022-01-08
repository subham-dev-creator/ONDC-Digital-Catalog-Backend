package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.MasterItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    List<MasterItem> masterItemList = new ArrayList<>();

    public List<MasterItem> searchItems(String query) {
        List<MasterItem> collect = new ArrayList<>();
        if(barcodeValidator(query)){
            collect = masterItemList.stream().filter(item -> item.getBarcode().equals(query)).collect(Collectors.toList());
        }
        else{
            String[] words = query.split("\\s+");
            List<MasterItem> wordCollect = new ArrayList<>();
            for(String word : words){
                wordCollect = masterItemList.stream().filter(item -> item.getSku().contains(word)).collect(Collectors.toList());
                collect.addAll(wordCollect);
            }
        }
        return collect;
    }

     boolean barcodeValidator(String query) {
        char[] array = query.toCharArray();
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
