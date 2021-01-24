package com.gildedrose.product;

import com.gildedrose.Item;

public class BackstagePasses implements Product {

    private final Item item;

    public BackstagePasses(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {

    }

    private void updateSellIn() {
        item.sellIn--;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
