package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellInTest {

    private static final int DAYS_TO_PASS = 45;

    @Test
    public void testSellInDecrease() {
        //given
        int initialSellIn = 10;
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellIn, 20);
        Item[] items = new Item[]{dexterityVest};

        //when
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < DAYS_TO_PASS; i++) {
            app.updateQuality();
        }

        //then
        assertEquals(initialSellIn - DAYS_TO_PASS, dexterityVest.sellIn);
    }
}
