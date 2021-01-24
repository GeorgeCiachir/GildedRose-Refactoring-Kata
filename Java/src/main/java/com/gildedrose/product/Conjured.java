package com.gildedrose.product;

import com.gildedrose.Item;

public class Conjured implements Product {

    private final Item item;

    public Conjured(Item item) {
        this.item = item;
    }

    @Override
    public void update() {

    }
}
