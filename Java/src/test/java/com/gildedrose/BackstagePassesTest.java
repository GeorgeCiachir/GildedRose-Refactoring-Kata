package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackstagePassesTest extends BaseTest {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private static final int TWICE_THE_QUALITY = 2;
    private static final int THRICE_THE_QUALITY = 3;

    @Test
    public void qualityIncreasesWithNormalStepBeforeTenDaysToSellIn() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 29;
        //don't overshoot the 10 days before sellIn
        assertThat(daysPassed).isLessThanOrEqualTo(initialSellInDays - 10);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        System.out.println(actual.quality);
        assertThat(actual.quality).isEqualTo(initialQuality + daysPassed);
    }

    @Test
    public void qualityIncreasesTwiceAsFastBetweenTenAndFiveDaysToSellIn() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 31;
        //between 10 to 5 days before sellIn
        assertThat(daysPassed).isLessThanOrEqualTo(initialSellInDays - 5);
        assertThat(daysPassed).isGreaterThan(initialSellInDays - 10);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        int normalQuality = initialSellInDays - 10;
        int twiceTheQuality = TWICE_THE_QUALITY * (daysPassed - (initialSellInDays - 10));
        assertThat(actual.quality).isEqualTo(initialQuality + normalQuality + twiceTheQuality);
    }

    @Test
    public void qualityIncreasesThriceAsFastAfterFiveDaysToSellIn() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 37;
        //after 5 days before sellIn
        assertThat(daysPassed).isGreaterThan(initialSellInDays - 5);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        int normalQuality = initialSellInDays - 10;
        int daysPassedAfterTwiceTheQuality = daysPassed - (initialSellInDays - 5);
        int twiceTheQuality = TWICE_THE_QUALITY * (daysPassed - (initialSellInDays - 10) - daysPassedAfterTwiceTheQuality);
        int thriceTheQuality = THRICE_THE_QUALITY * (daysPassed - (initialSellInDays - 5));
        assertThat(actual.quality).isEqualTo(initialQuality + normalQuality + twiceTheQuality + thriceTheQuality);
    }

    @Test
    public void qualityNeverGoesAboveFifty() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 39;
        //after 5 days before sellIn
        assertThat(daysPassed).isGreaterThan(initialSellInDays - 5);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }

    @Test
    public void qualityDropsToZeroAfterSellIn() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Item[] items = new Item[]{actual};

        //when
        int daysPassed = 41;
        //after 5 days before sellIn
        assertThat(daysPassed).isGreaterThan(initialSellInDays - 5);
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < daysPassed; i++) {
            app.updateQuality();
        }

        //then
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }
}
