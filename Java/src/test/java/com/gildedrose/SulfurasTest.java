package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SulfurasTest extends SellInTest {

    @Test
    public void qualityRemainsFixed() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 80;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = initialSellInDays + 1;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality);
    }
}
