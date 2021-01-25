package com.gildedrose;

import com.gildedrose.store.product.Product;
import com.gildedrose.store.ProductFactory;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class GildedRose {

    Item[] items;
    private final List<Product> products;

    public GildedRose(Item[] items) {
        this.items = items;
        products = Arrays.stream(items)
                .map(ProductFactory::newProduct)
                .collect(toList());
    }

    public void updateQuality() {
        products.forEach(Product::update);
    }
}