package com.ondc.tw.digitalcatalog.service;

import com.ondc.tw.digitalcatalog.model.Product;
import com.ondc.tw.digitalcatalog.model.MasterProduct;
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
   static List<MasterProduct>  masterProductList;
   List<Product> productList = new ArrayList<>();

    static {
        masterProductList  = readProductsFromCSV("catalog_sample.csv");
        for(MasterProduct masterProduct: masterProductList){
            System.out.println(masterProduct);
        }
}
    public List<MasterProduct> searchItems(String query) {
        List<MasterProduct> collect = new ArrayList<>();
        if (barcodeValidator(query)) {
            collect = masterProductList.stream().filter(item -> item.getBarcode().equals(query)).collect(Collectors.toList());
        } else {
            String[] words = query.split("\\s+");
            List<MasterProduct> wordCollect;
            for (String word : words) {
                wordCollect = masterProductList.stream().filter(item -> item.getSku().contains(word)).collect(Collectors.toList());
                collect.addAll(wordCollect);
            }
        }
        return collect;
    }

    private static List<MasterProduct> readProductsFromCSV(String fileName) {
        List<MasterProduct> product = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                MasterProduct singleProduct = createProduct(attributes);
                product.add(singleProduct);
                line = br.readLine();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return product;
    }

    private static MasterProduct createProduct(String[] metadata) {
        String barcode = metadata[0];
        String sku = metadata[1];
        String weight = metadata[2];
        String unit = metadata[3];
        String mrp = metadata[4];
        String image128 = metadata[5];
        String image256 = metadata[6];
        String parentCategory = metadata[7];
        String subCategory = metadata[8];
        return new MasterProduct(barcode, sku, weight, unit, mrp, image128, image256, parentCategory, subCategory);
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
