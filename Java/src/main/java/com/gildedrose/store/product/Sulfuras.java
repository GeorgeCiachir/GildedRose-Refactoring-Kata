package com.gildedrose.store.product;

import com.gildedrose.Item;
import com.gildedrose.store.validation.Validator;

import java.util.List;

import static com.gildedrose.store.validation.ValidationError.SULFURAS_CUSTOM;
import static java.util.Collections.singletonList;

public class Sulfuras implements Product {

    private static final int FIXED_QUALITY = 80;

    private final Item item;

    public Sulfuras(Item item) {
        this.item = item;
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
        // Do nothing. Sulfuras doesn't age
    }

    public static List<Validator> getValidators() {
        Validator validator = new Validator(item -> item.quality != FIXED_QUALITY, SULFURAS_CUSTOM);
        return singletonList(validator);
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
