package com.gildedrose.store.product;

import com.gildedrose.Item;
import com.gildedrose.store.ProductBuilder;
import com.gildedrose.store.ProductType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackstagePassesTest extends BaseTest {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private static final int NORMAL_QUALITY = 1;
    private static final int TWICE_THE_QUALITY = 2 * NORMAL_QUALITY;
    private static final int THRICE_THE_QUALITY = 3 * NORMAL_QUALITY;

    @Test
    public void qualityIncreasesNormalBeforeTenDaysToSellIn() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 29 of increase 1 -> 29
        int daysPassed = 29;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality + daysPassed);
    }

    @Test
    public void qualityIncreasesNormalStartingFromZeroIfNotExpired() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 0;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 29 of increase 1 -> 29
        int daysPassed = 29;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality + daysPassed);
    }

    @Test
    public void qualityIncreasesNormalThenTwiceThenThrice() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 30 of increase 1 -> 30
        // 5 of increase 2 -> 10
        // 2 of increase 3 -> 6
        int daysPassed = 37;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        int normalQuality = 30 * NORMAL_QUALITY;
        int twiceTheQuality = 5 * TWICE_THE_QUALITY;
        int thriceTheQuality = 2 * THRICE_THE_QUALITY;
        assertThat(actual.quality).isEqualTo(initialQuality + normalQuality + twiceTheQuality + thriceTheQuality);
    }

    @Test
    public void qualityIncreasesNormalThenTwiceThenThriceThenTwice() {
        //given
        int initialSellInDays = 35;
        int initialQuality = 1;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 25 of increase 1 -> 25
        // 5 of increase 2 -> 10
        // 4 of increase 3 -> 12
        // 1 of increase 2 -> 2
        int daysPassed = 35;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        int normalQuality = 25 * NORMAL_QUALITY;
        int twiceTheQuality = 5 * TWICE_THE_QUALITY;
        int thriceTheQuality = 4 * THRICE_THE_QUALITY;
        int twiceTheQualityAgain = 1 * TWICE_THE_QUALITY;

        assertThat(actual.quality).isEqualTo(initialQuality + normalQuality + twiceTheQuality + thriceTheQuality + twiceTheQualityAgain);
    }

    @Test
    public void qualityIncreasesNormalThenTwiceThenThriceThenNormal() {
        //given
        int initialSellInDays = 35;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 25 of increase 1 -> 25
        // 5 of increase 2 -> 10
        // 4 of increase 3 -> 12
        // 1 of increase 1 -> 1
        int daysPassed = 35;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        int normalQuality = 25 * NORMAL_QUALITY;
        int twiceTheQuality = 5 * TWICE_THE_QUALITY;
        int thriceTheQuality = 4 * THRICE_THE_QUALITY;
        int normalQualityAgain = 1 * NORMAL_QUALITY;

        System.out.println(actual.quality);
        assertThat(actual.quality).isEqualTo(initialQuality + normalQuality + twiceTheQuality + thriceTheQuality + normalQualityAgain);
    }

    @Test
    public void verifyQualityNeverAboveMaximum() {
        //given
        int initialSellInDays = 40;
        int initialQuality = 2;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        // 30 of increase 1 -> 30
        // 5 of increase 2 -> 10
        // 3 of increase 3 -> 9
        int daysPassed = 38;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MAX_QUALITY);
    }

    @Test
    public void qualityDropsToZeroAfterSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 49;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellInDays, initialQuality);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        int daysPassed = 11;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void verifySellInDecreases() {
        //given
        int daysToPass = 60;
        int initialSellIn = 10;
        Item actual = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, 20);
        Product product = new ProductBuilder(ProductType.BACKSTAGE_PASSES).buildFrom(actual);

        //when
        for (int i = 0; i < daysToPass; i++) {
            product.update();
        }

        //then
        assertThat(actual.sellIn).isEqualTo(initialSellIn - daysToPass);
    }
}
