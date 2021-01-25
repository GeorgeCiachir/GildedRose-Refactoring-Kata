package com.gildedrose.store.product;

import com.gildedrose.Item;
import com.gildedrose.store.validation.Validator;

import java.util.List;

import static com.gildedrose.store.validation.Validators.defaultValidators;

public class ConjuredMana implements Product {

    private static final int NORMAL_DEGRADATION = 2;
    private static final int DOUBLE_DEGRADATION = 2 * NORMAL_DEGRADATION;

    private final Item item;

    public ConjuredMana(Item item) {
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

        if (shouldDegradeTwice()) {
            degradeTwice();
            return;
        }

        if (shouldDegradeNormal()) {
            degradeNormal();
            return;
        }

        degradeByOne();
    }

    private boolean expired() {
        return item.quality == 0;
    }

    private boolean shouldDegradeTwice() {
        return sellInPassed()
                && canDegradeTwice();
    }

    private boolean canDegradeTwice() {
        return item.quality >= DOUBLE_DEGRADATION;
    }

    private void degradeTwice() {
        item.quality = item.quality - DOUBLE_DEGRADATION;
    }

    private boolean shouldDegradeNormal() {
        return canDegradeNormal();
    }

    private boolean canDegradeNormal() {
        return item.quality >= NORMAL_DEGRADATION;
    }

    private void degradeNormal() {
        item.quality = item.quality - NORMAL_DEGRADATION;
    }

    private void degradeByOne() {
        item.quality--;
    }

    private boolean withinSellIn() {
        return item.sellIn > 0;
    }

    private boolean sellInPassed() {
        return !withinSellIn();
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
