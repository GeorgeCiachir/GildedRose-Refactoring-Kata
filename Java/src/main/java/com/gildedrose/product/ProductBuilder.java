package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.function.Function;

public class ProductBuilder {

    private final Function<Item, Product> productConstructor;

    public ProductBuilder(Function<Item, Product> productConstructor) {
        this.productConstructor = productConstructor;
    }

    public Product forItem(Item item) {
        if (item.quality < 0) {
            throw new IllegalArgumentException("The quality should never be lower than zero");
        }

        return productConstructor.apply(item);
    }
}
