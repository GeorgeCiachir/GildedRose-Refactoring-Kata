package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalProductTest extends BaseTest {

    private static final int DEGRADE_TWICE = 2;
    private static final int MIN_QUALITY = 0;

    @Test
    public void degradesNormalStepBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

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
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

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
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

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
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

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
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

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

    @Test
    public void testSellInDecrease() {
        //given
        int daysToPass = 60;
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item elixir = new Item("Elixir of the Mongoose", initialSellInDays, initialQuality);
        Item dexterityVest = new Item("+5 Dexterity Vest", initialSellInDays, initialQuality);
        Item anotherNormal = new Item("a normal item", initialSellInDays, initialQuality);
        Product elixirProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(elixir);
        Product dexterityVestProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(dexterityVest);
        Product anotherNormalProduct = new ProductBuilder(ProductType.NORMAL).buildFrom(anotherNormal);

        //when
        for (int i = 0; i < daysToPass; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        assertThat(elixir.sellIn).isEqualTo(initialSellInDays - daysToPass);
        assertThat(dexterityVest.sellIn).isEqualTo(initialSellInDays - daysToPass);
        assertThat(anotherNormal.sellIn).isEqualTo(initialSellInDays - daysToPass);
    }
}