package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DexterityVestTest extends SellInTest {

    private static final int DEGRADE_TWICE = 2;
    private static final int MIN_QUALITY = 0;

    @Test
    public void degradesNormalStepBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 9;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality - daysPassed);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialEvenQuality() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysAfterSellInPassed = 4;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isLessThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE - 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < totalDaysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality - initialSellInDays - DEGRADE_TWICE * daysAfterSellInPassed);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialOddQualityNotBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 21;
        Item actual = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysAfterSellInPassed = 6;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isGreaterThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE + 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < totalDaysPassed; i++) {
            app.updateQuality();
        }

        //then
        System.out.println(actual.quality);
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void qualityNeverGoesBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysToPassAfterSellToReachZeroQuality = (initialQuality - initialSellInDays) / DEGRADE_TWICE;
        int totalDaysPassed = initialSellInDays + daysToPassAfterSellToReachZeroQuality + 1;
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < totalDaysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }
}
