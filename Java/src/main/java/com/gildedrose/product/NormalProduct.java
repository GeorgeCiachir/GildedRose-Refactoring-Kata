package com.gildedrose.product;

import com.gildedrose.Item;

public class NormalProduct implements Product {

    private static final int NORMAL_DEGRADATION = 1;
    private static final int DOUBLE_DEGRADATION = 2 * NORMAL_DEGRADATION;

    private final Item item;

    public NormalProduct(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (expired()) {
            return;
        }

        if (withinSellInPeriod()) {
            degradeNormal();
            return;
        }

        if (canDegradeTwice()) {
            degradeTwice();
        } else {
            degradeNormal();
        }
    }

    private boolean expired() {
        return item.quality <= 0;
    }

    private boolean withinSellInPeriod() {
        return item.sellIn > 0;
    }

    private void degradeNormal() {
        item.quality--;
    }

    private void degradeTwice() {
        item.quality = item.quality - DOUBLE_DEGRADATION;
    }

    private boolean canDegradeTwice() {
        return item.quality >= 2;
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
