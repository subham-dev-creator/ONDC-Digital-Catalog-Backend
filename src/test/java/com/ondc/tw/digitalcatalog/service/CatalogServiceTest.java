package com.ondc.tw.digitalcatalog.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogServiceTest {

    @Test
    void testBarcodeValidator() {
        String query = "1212424231312323131313131311323421";
        CatalogService catalogService = new CatalogService();
        assertEquals(catalogService.barcodeValidator(query),true);
    }
    @Test
    void testBarcodeValidatorFalse() {
        String query = "1212424231312323131313131skfsifwi311323421";
        CatalogService catalogService = new CatalogService();
        assertEquals(catalogService.barcodeValidator(query),false);
    }
}