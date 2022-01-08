package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.dto.ProductDto;
import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {
    @Autowired
    MasterDataService masterDataService;

    List<Product> productList = new ArrayList<>();

    public void addProducts(List<Product> productList) {

        this.productList.addAll(productList);
    }

    public List<ProductDto> listProducts() {
        List<ProductDto> products = new ArrayList<>();

        for (Product product : productList) {
            System.out.println(product.toString());
            ProductDto productDto = new ProductDto();
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());

            try {
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
            catch (Exception e){
                throw new IllegalArgumentException("No ID Found");
            }
        }

        return products;
    }
}
