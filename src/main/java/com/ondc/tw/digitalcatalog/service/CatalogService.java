package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;

@Service
public class CatalogService {
    @Autowired
    MasterDataService masterDataService;

    @Autowired
    StoreService storeService;

//   Map<UUID,CatalogProduct> catalogProductMap = new HashMap<>();

    private Map<UUID, CatalogProduct> getCatalog(String id) {
        Store store = storeService.storeMap.get(id);
        Catalog catalog = store.getCatalog();
        return catalog.getCatalogProductMap();
    }

    public void addProducts(List<Product> productList, String id) {
        Map<UUID, CatalogProduct> catalogProductMap = getCatalog(id);

        for (Product product : productList) {
            CatalogProduct catalogProduct = findById(product.getMasterId(), id);
            if (catalogProduct == null)
                catalogProductMap.put(product.getMasterId(), CatalogProduct.from(masterDataService.findById(product.getMasterId()), product));
        }
    }

    public void addCustomProduct(CatalogProduct catalogProduct, String id) {
        Map<UUID, CatalogProduct> catalogProductMap = getCatalog(id);

        UUID customProductId = UUID.randomUUID();
        catalogProduct.setId(customProductId);
        catalogProductMap.put(customProductId, catalogProduct);
    }

    public List<CatalogProduct> getProducts(String id) {
        Map<UUID, CatalogProduct> catalogProductMap = getCatalog(id);

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

    public CatalogProduct findById(UUID id, String mobileId) {
        Map<UUID, CatalogProduct> catalogProductMap = getCatalog(mobileId);

        if (catalogProductMap.containsKey(id))
            return catalogProductMap.get(id);
        return null;
    }

    public void updateProducts(Product product, String id) {
        CatalogProduct testProduct = findById(product.getMasterId(), id);
        if (testProduct != null) {
            testProduct.setPrice(product.getPrice());
            testProduct.setQuantity(product.getQuantity());
        }
    }

    public void deleteProducts(Product product, String id) {
        Map<UUID, CatalogProduct> catalogProductMap = getCatalog(id);

        catalogProductMap.remove(product.getMasterId());
    }
}