package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.dto.ProductDto;
import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogService {
    @Autowired
    MasterDataService masterDataService;

    List<Product> productList = new ArrayList<>();

    public void addProducts(List<Product> product) {
        productList.addAll(product);
    }

    public List<ProductDto> listProducts() {
        List<ProductDto> products = new ArrayList<>();

        for (Product product : productList) {
            ProductDto productDto = new ProductDto();
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());

            MasterProduct masterProduct = masterDataService.findById(product.getMasterId());

            productDto.setSku(masterProduct.getSku());
            productDto.setWeight(masterProduct.getWeight());
            productDto.setUnit(masterProduct.getUnit());
            productDto.setMrp(masterProduct.getMrp());
            productDto.setImage128(masterProduct.getImage128());
            productDto.setImage256(masterProduct.getImage256());
            productDto.setParentCategory(masterProduct.getParentCategory());
            productDto.setSubCategory(masterProduct.getSubCategory());

            products.add(productDto);
        }

        return products;
    }
}
