package com.gildedrose.product;

import com.gildedrose.Item;
import com.gildedrose.product.error.Validator;

import java.util.List;

import static com.gildedrose.product.error.Validators.defaultValidators;

public class AgedBrie implements Product {

    private static final int NORMAL_INCREASE = 1;
    private static final int DOUBLE_INCREASE = 2 * NORMAL_INCREASE;
    private static final int MAX_QUALITY = 50;

    private final Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (maxQualityReached()) {
            return;
        }

        if (withinSellInPeriod()) {
            normalQualityIncrease();
        } else {
            doubleQualityIncrease();
        }
    }

    private boolean maxQualityReached() {
        return item.quality == MAX_QUALITY;
    }

    private boolean withinSellInPeriod() {
        return item.sellIn > 0;
    }

    private void normalQualityIncrease() {
        item.quality++;
    }

    private void doubleQualityIncrease() {
        item.quality = item.quality + DOUBLE_INCREASE;
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    public static List<Validator> getValidators() {
        return defaultValidators();
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
