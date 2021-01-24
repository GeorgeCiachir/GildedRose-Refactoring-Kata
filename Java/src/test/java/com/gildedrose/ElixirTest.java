package com.gildedrose;

import com.gildedrose.product.NormalProduct;
import com.gildedrose.product.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElixirTest extends SellInTest {

    private static final int DEGRADE_TWICE = 2;
    private static final int MIN_QUALITY = 0;

    @Test
    public void degradesNormalStepBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Product normalProduct = Product.builder(NormalProduct::new).forItem(actual);

        //when
        int daysPassed = 9;
        for (int i = 0; i < daysPassed; i++) {
            normalProduct.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality - daysPassed);
    }

    @Test
    public void degradesNormalStepBeforeSellInNeverBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 4;
        Item actual = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Product normalProduct = Product.builder(NormalProduct::new).forItem(actual);

        //when
        int daysPassed = 9;
        for (int i = 0; i < daysPassed; i++) {
            normalProduct.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialEvenQuality() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item actual = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Product normalProduct = Product.builder(NormalProduct::new).forItem(actual);

        //when
        int daysAfterSellInPassed = 4;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isLessThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE - 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        for (int i = 0; i < totalDaysPassed; i++) {
            normalProduct.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality - initialSellInDays - DEGRADE_TWICE * daysAfterSellInPassed);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialOddQualityNotBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 21;
        Item actual = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Product normalProduct = Product.builder(NormalProduct::new).forItem(actual);

        //when
        int daysAfterSellInPassed = 6;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isGreaterThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE + 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        for (int i = 0; i < totalDaysPassed; i++) {
            normalProduct.update();
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
        Item actual = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Product normalProduct = Product.builder(NormalProduct::new).forItem(actual);

        //when
        int daysToPassAfterSellToReachZeroQuality = (initialQuality - initialSellInDays) / DEGRADE_TWICE;
        int totalDaysPassed = initialSellInDays + daysToPassAfterSellToReachZeroQuality + 1;
        for (int i = 0; i < totalDaysPassed; i++) {
            normalProduct.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(MIN_QUALITY);
    }
}
