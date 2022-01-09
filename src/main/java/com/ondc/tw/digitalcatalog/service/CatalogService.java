package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.CatalogProduct;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
import com.ondc.tw.digitalcatalog.model.Product;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CatalogService {
    @Autowired
    MasterDataService masterDataService;

   Map<UUID, CatalogProduct> catalogProductMap = new HashMap<>();

    public void addProducts(List<Product> productList) {
        for (Product product : productList) {
            CatalogProduct catalogProduct = findById(product.getMasterId());
            if (catalogProduct == null)
                this.catalogProductMap.put(product.getMasterId(), CatalogProduct.from(masterDataService.findById(product.getMasterId()), product));
        }
    }

    public void addCustomProduct(CatalogProduct catalogProduct)
    {
        val id = UUID.randomUUID();
        catalogProduct.setId(id);
        this.catalogProductMap.put(id,catalogProduct);
    }

    public List<CatalogProduct> getProducts() {
        List<CatalogProduct> products = new ArrayList<>();

        for (CatalogProduct product : catalogProductMap.values()) {
            System.out.println(product.toString());
            CatalogProduct catalogProduct = new CatalogProduct();
            catalogProduct.setPrice(product.getPrice());
            catalogProduct.setQuantity(product.getQuantity());

            try {
                MasterProduct masterProduct = masterDataService.findById(product.getId());

                catalogProduct.setId(masterProduct.getId());
                catalogProduct.setSku(masterProduct.getSku());
                catalogProduct.setWeight(masterProduct.getWeight());
                catalogProduct.setUnit(masterProduct.getUnit());
                catalogProduct.setMrp(masterProduct.getMrp());
                catalogProduct.setImage128(masterProduct.getImage128());
                catalogProduct.setImage256(masterProduct.getImage256());
                catalogProduct.setParentCategory(masterProduct.getParentCategory());
                catalogProduct.setSubCategory(masterProduct.getSubCategory());

                products.add(catalogProduct);
            } catch (Exception e) {
                throw new IllegalArgumentException("No ID Found");
            }
        }
        return products;
    }

    public CatalogProduct findById(UUID id) {
        if(catalogProductMap.containsKey(id))
            return catalogProductMap.get(id);
        return null;
    }

    public void updateProducts(Product product) {
            CatalogProduct testProduct = findById(product.getMasterId());
            if (testProduct != null) {
                testProduct.setPrice(product.getPrice());
                testProduct.setQuantity(product.getQuantity());
            }
        }

    public void deleteProducts(Product product) {
            catalogProductMap.remove(product.getMasterId());
        }
    }