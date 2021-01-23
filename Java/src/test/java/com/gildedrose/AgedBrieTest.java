package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgedBrieTest extends SellInTest {

    private static final int MAX_QUALITY = 50;
    private static final int TWICE_THE_QUALITY = 2;

    @Test
    public void qualityIncreasesWithNormalStepGivenInitialSellInDaysNotExceeded() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = initialSellInDays;
        // don't overshoot initialSellInDays -> quality increases twice as fast
        assertThat(daysPassed).isLessThanOrEqualTo(initialSellInDays);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality + daysPassed);
    }

    @Test
    public void qualityIncreasesTwiceAsFastGivenInitialSellInDaysPassed() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysWithDoubleQualityIncrease = 9;
        int daysPassed = initialSellInDays + daysWithDoubleQualityIncrease;
        // don't overshoot MAX_QUALITY
        assertThat(daysWithDoubleQualityIncrease).isLessThanOrEqualTo((MAX_QUALITY - initialSellInDays - initialQuality) / TWICE_THE_QUALITY);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality + initialSellInDays + TWICE_THE_QUALITY * daysWithDoubleQualityIncrease);
    }

    @Test
    public void qualityNeverGoesAboveFifty() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysWithDoubleQualityIncrease = 10;
        int daysPassed = initialSellInDays + daysWithDoubleQualityIncrease + 2;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }
}
