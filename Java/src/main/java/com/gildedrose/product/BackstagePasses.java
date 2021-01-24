package com.gildedrose.product;

import com.gildedrose.Item;

public class BackstagePasses implements Product {

    private static final int NORMAL_INCREASE = 1;
    private static final int DOUBLE_INCREASE = 2 * NORMAL_INCREASE;
    private static final int THRICE_INCREASE = 3 * NORMAL_INCREASE;
    private static final int MAX_QUALITY = 50;

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
        if (expired()) {
            return;
        }

        if (sellInPassed()) {
            item.quality = 0;
            return;
        }

        if (maxQualityReached()) {
            return;
        }

        if (qualityShouldIncreaseThrice()) {
            thriceQualityIncrease();
            return;
        }

        if (qualityShouldIncreaseTwice()) {
            twiceQualityIncrease();
            return;
        }

        normalQualityIncrease();
    }

    //TODO: add test for this case.
    // For the others it was enough to check the quality drop to 0
    private boolean expired() {
        return item.quality == 0 && sellInPassed();
    }

    private boolean sellInPassed() {
        return item.sellIn <= 0;
    }

    private boolean maxQualityReached() {
        return item.quality == MAX_QUALITY;
    }

    private boolean qualityShouldIncreaseThrice() {
        return isInIncreaseThriceInterval()
                && qualityCanIncreaseThrice();
    }

    private boolean isInIncreaseThriceInterval() {
        return !sellInPassed()
                && item.sellIn <= 5;
    }

    private boolean qualityCanIncreaseThrice() {
        return item.quality <= MAX_QUALITY - THRICE_INCREASE;
    }

    private void thriceQualityIncrease() {
        item.quality = item.quality + THRICE_INCREASE;
    }

    private boolean qualityShouldIncreaseTwice() {
        return isInIncreaseTwiceInterval()
                && qualityCanIncreaseTwice();
    }

    private boolean isInIncreaseTwiceInterval() {
        return !sellInPassed()
                && item.sellIn <= 10;
    }

    private boolean qualityCanIncreaseTwice() {
        return item.quality <= MAX_QUALITY - DOUBLE_INCREASE;
    }

    private void twiceQualityIncrease() {
        item.quality = item.quality + DOUBLE_INCREASE;
    }

    private void normalQualityIncrease() {
        item.quality++;
    }

    private void updateSellIn() {
        item.sellIn--;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
