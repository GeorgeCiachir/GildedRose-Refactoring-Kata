package com.gildedrose.product;

import com.gildedrose.BaseTest;
import com.gildedrose.Item;
import com.gildedrose.product.AgedBrie;
import com.gildedrose.product.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgedBrieTest extends BaseTest {

    private static final int MAX_QUALITY = 50;
    private static final int TWICE_THE_QUALITY = 2;

    @Test
    public void qualityIncreasesWithNormalStepGivenInitialSellInDaysNotExceeded() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Product product = Product.builder(AgedBrie::new).forItem(actual);

        //when
        int daysPassed = initialSellInDays;
        // don't overshoot initialSellInDays -> quality increases twice as fast
        assertThat(daysPassed).isLessThanOrEqualTo(initialSellInDays);
        for (int i = 0; i < daysPassed; i++) {
            product.update();
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
        Product product = Product.builder(AgedBrie::new).forItem(actual);

        //when
        int daysWithDoubleQualityIncrease = 9;
        int daysPassed = initialSellInDays + daysWithDoubleQualityIncrease;
        // don't overshoot MAX_QUALITY
        assertThat(daysWithDoubleQualityIncrease).isLessThanOrEqualTo((MAX_QUALITY - initialSellInDays - initialQuality) / TWICE_THE_QUALITY);
        for (int i = 0; i < daysPassed; i++) {
            product.update();
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
        Product product = Product.builder(AgedBrie::new).forItem(actual);

        //when
        int daysWithDoubleQualityIncrease = 10;
        int daysPassed = initialSellInDays + daysWithDoubleQualityIncrease + 2;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }
}
