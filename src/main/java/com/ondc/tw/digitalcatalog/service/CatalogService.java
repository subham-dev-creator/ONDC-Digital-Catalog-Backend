package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    List<MasterProduct> masterProductList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    public List<MasterProduct> searchItems(String query) {
        List<MasterProduct> collect = new ArrayList<>();
        if (barcodeValidator(query)) {
            collect = masterProductList.stream().filter(item -> item.getBarcode().equals(query)).collect(Collectors.toList());
        } else {
            String[] words = query.split("\\s+");
            List<MasterProduct> wordCollect = new ArrayList<>();
            for (String word : words) {
                wordCollect = masterProductList.stream().filter(item -> item.getSku().contains(word)).collect(Collectors.toList());
                collect.addAll(wordCollect);
            }
        }
        return collect;
    }

    boolean barcodeValidator(String query) {
        char[] array = query.toCharArray();
        for (char index : array) {
            try {
                Integer.parseInt(String.valueOf(index));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public void addItem(Product product) {
        productList.add(product);
    }

    public List<Product> listItem() {
        return productList;
    }
}
