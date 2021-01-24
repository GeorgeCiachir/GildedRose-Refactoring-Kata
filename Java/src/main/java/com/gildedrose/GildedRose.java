package com.gildedrose;

import com.gildedrose.product.Product;
import com.gildedrose.product.ProductFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {

    Item[] items;
    private final List<Product> products;

    public GildedRose(Item[] items) {
        this.items = items;
        products = Arrays.stream(items)
                .map(ProductFactory::productFor)
                .collect(Collectors.toList());
    }

    public void updateQuality() {
        products.forEach(Product::update);
    }
}