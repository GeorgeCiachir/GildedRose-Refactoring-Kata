package com.gildedrose.product;

import com.gildedrose.Item;

public class NormalProduct implements Product {

    private final Item item;

    public NormalProduct(Item item) {
        this.item = item;
    }

    @Override
    public void update() {

    }
}
