package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AgedBrieTest extends BaseTest {

    private static final int MAX_QUALITY = 50;
    private static final int NORMAL_INCREASE = 1;
    private static final int DOUBLE_INCREASE = 2 * NORMAL_INCREASE;

    @Test
    public void qualityIncreasesNormalBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.AGED_BRIE).buildFrom(actual);

        //when
        int daysPassed = initialSellInDays - 1;
        assertThat(daysPassed).isLessThanOrEqualTo(initialSellInDays);
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality + daysPassed);
    }

    @Test
    public void qualityIncreasesNormalBeforeSellInThenTwiceAsFast() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.AGED_BRIE).buildFrom(actual);

        //when
        // 10 of increase 1 -> 10
        // 9 of increase 2 -> 18
        int daysPassed = 19;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        int normalIncrease = 10 * NORMAL_INCREASE;
        int doubleIncrease = 9 * DOUBLE_INCREASE;
        assertThat(actual.quality).isEqualTo(initialQuality + normalIncrease + doubleIncrease);
    }

    @Test
    public void qualityIncreasesNormalBeforeSellInThenTwiceAsFastThenNormal() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 1;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.AGED_BRIE).buildFrom(actual);

        //when
        // 40 of increase 1 -> 40
        // 4 of increase 2 -> 8
        // 1 of increase 1-> 1
        int daysPassed = 45;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }

    @Test
    public void verifyQualityNeverAboveMaximum() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Aged Brie", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.AGED_BRIE).buildFrom(actual);

        //when
        // 10 of increase 1 -> 10
        // 20 of increase 2 -> 40
        int daysPassed = 30;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }

    @Test
    public void verifySellInDecreases() {
        //given
        int daysToPass = 60;
        int initialSellIn = 10;
        Item actual = new Item("Aged Brie", initialSellIn, 20);
        Product product = new ProductBuilder(ProductType.AGED_BRIE).buildFrom(actual);

        //when
        for (int i = 0; i < daysToPass; i++) {
            product.update();
        }

        //then
        assertThat(actual.sellIn).isEqualTo(initialSellIn - daysToPass);
    }
}
