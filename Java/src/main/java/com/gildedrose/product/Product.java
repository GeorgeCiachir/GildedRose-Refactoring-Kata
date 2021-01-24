package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.function.Function;

public interface Product {

    void update();

    static ProductBuilder builder(Function<Item, Product> productConstructor) {
        return new ProductBuilder(productConstructor);
    }
}
