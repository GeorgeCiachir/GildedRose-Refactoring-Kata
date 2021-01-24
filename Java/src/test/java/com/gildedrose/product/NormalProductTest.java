package com.gildedrose.product;

import com.gildedrose.Item;
import com.gildedrose.SellInTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalProductTest extends SellInTest {

    private static final int DEGRADE_TWICE = 2;
    private static final int MIN_QUALITY = 0;

    private Item elixir;
    private Item dexterityVest;
    private Item anotherNormal;
    private Product elixirProduct;
    private Product dexterityVestProduct;
    private Product anotherNormalProduct;

    @Test
    public void degradesNormalStepBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        setup(initialSellInDays, initialQuality);

        //when
        int daysPassed = 9;
        for (int i = 0; i < daysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.quality).isEqualTo(initialQuality - daysPassed);
        assertThat(dexterityVest.quality).isEqualTo(initialQuality - daysPassed);
        assertThat(anotherNormal.quality).isEqualTo(initialQuality - daysPassed);
    }

    @Test
    public void degradesNormalStepBeforeSellInNeverBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 4;
        setup(initialSellInDays, initialQuality);

        //when
        int daysPassed = 9;
        for (int i = 0; i < daysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.quality).isEqualTo(MIN_QUALITY);
        assertThat(dexterityVest.quality).isEqualTo(MIN_QUALITY);
        assertThat(anotherNormal.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialEvenQuality() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        setup(initialSellInDays, initialQuality);

        //when
        int daysAfterSellInPassed = 4;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isLessThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE - 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        for (int i = 0; i < totalDaysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.quality).isEqualTo(initialQuality - initialSellInDays - DEGRADE_TWICE * daysAfterSellInPassed);
        assertThat(dexterityVest.quality).isEqualTo(initialQuality - initialSellInDays - DEGRADE_TWICE * daysAfterSellInPassed);
        assertThat(anotherNormal.quality).isEqualTo(initialQuality - initialSellInDays - DEGRADE_TWICE * daysAfterSellInPassed);
    }

    @Test
    public void degradesTwiceAsFastAfterSellInInitialOddQualityNotBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 21;
        setup(initialSellInDays, initialQuality);

        //when
        int daysAfterSellInPassed = 6;
        // don't overshoot this, so the quality won't go to zero
        assertThat(daysAfterSellInPassed).isGreaterThanOrEqualTo((initialQuality - initialSellInDays) / DEGRADE_TWICE + 1);
        int totalDaysPassed = initialSellInDays + daysAfterSellInPassed;
        for (int i = 0; i < totalDaysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.quality).isEqualTo(MIN_QUALITY);
        assertThat(dexterityVest.quality).isEqualTo(MIN_QUALITY);
        assertThat(anotherNormal.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void qualityNeverGoesBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        setup(initialSellInDays, initialQuality);

        //when
        int daysToPassAfterSellToReachZeroQuality = (initialQuality - initialSellInDays) / DEGRADE_TWICE;
        int totalDaysPassed = initialSellInDays + daysToPassAfterSellToReachZeroQuality + 1;
        for (int i = 0; i < totalDaysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.quality).isEqualTo(MIN_QUALITY);
        assertThat(dexterityVest.quality).isEqualTo(MIN_QUALITY);
        assertThat(anotherNormal.quality).isEqualTo(MIN_QUALITY);
    }

    private void setup(int initialSellInDays, int initialQuality) {
        elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        elixirProduct = Product.builder(NormalProduct::new).forItem(elixir);
        dexterityVestProduct = Product.builder(NormalProduct::new).forItem(dexterityVest);
        anotherNormalProduct = Product.builder(NormalProduct::new).forItem(anotherNormal);
    }
}