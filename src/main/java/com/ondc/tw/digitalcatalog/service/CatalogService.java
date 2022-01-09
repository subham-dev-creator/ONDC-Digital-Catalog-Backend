package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.dto.ProductDto;
import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CatalogService {
    @Autowired
    MasterDataService masterDataService;

   Map<UUID,Product> storeProductMap = new HashMap<>();

    public void addProducts(List<Product> productList) {
        for (Product product : productList) {
            Product testProduct = findById(product.getMasterId());
            if (testProduct == null)
                this.storeProductMap.put(product.getMasterId(), product);
        }
    }

    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();

        for (Product product : storeProductMap.values()) {
            System.out.println(product.toString());
            ProductDto productDto = new ProductDto();
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());

            try {
                MasterProduct masterProduct = masterDataService.findById(product.getMasterId());

                productDto.setId(masterProduct.getId());
                productDto.setSku(masterProduct.getSku());
                productDto.setWeight(masterProduct.getWeight());
                productDto.setUnit(masterProduct.getUnit());
                productDto.setMrp(masterProduct.getMrp());
                productDto.setImage128(masterProduct.getImage128());
                productDto.setImage256(masterProduct.getImage256());
                productDto.setParentCategory(masterProduct.getParentCategory());
                productDto.setSubCategory(masterProduct.getSubCategory());

                products.add(productDto);
            } catch (Exception e) {
                throw new IllegalArgumentException("No ID Found");
            }
        }
        return products;
    }

    public Product findById(UUID id) {
        if(storeProductMap.containsKey(id))
            return storeProductMap.get(id);
        return null;
    }

    public void updateProducts(Product product) {
            Product testProduct = findById(product.getMasterId());
            if (testProduct != null) {
                testProduct.setPrice(product.getPrice());
                testProduct.setQuantity(product.getQuantity());
            }
        }

    public void deleteProducts(Product product) {
            storeProductMap.remove(product.getMasterId());
        }
    }