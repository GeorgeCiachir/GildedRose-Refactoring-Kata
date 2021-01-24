package com.gildedrose.product;

import com.gildedrose.Item;

public class Sulfuras implements Product {

    private static final int FIXED_QUALITY = 80;

    private final Item item;

    public Sulfuras(Item item) {
        this.item = item;
        if (item.quality != FIXED_QUALITY) {
            throw new IllegalArgumentException("The quality of Sulfuras should always be 80");
        }
    }

    @Override
    public void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        // Do nothing. The quality of Sulfuras should always be 80
    }

    private void updateSellIn() {
//        item.sellIn--;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
