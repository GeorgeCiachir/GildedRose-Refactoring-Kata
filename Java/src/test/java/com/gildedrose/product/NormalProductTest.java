package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalProductTest extends BaseTest {

    private static final int DEGRADE_NORMAL = 1;
    private static final int DEGRADE_TWICE = 2 * DEGRADE_NORMAL;
    private static final int MIN_QUALITY = 0;

    @Test
    public void qualityDegradesNormalBeforeSellIn() {
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
    public void qualityDegradesNormalBeforeSellInThenTwiceAsFast() {
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
        // 10 of degradation 1 -> 10
        // 4 of degradation 2 -> 8
        int totalDaysPassed = 14;
        for (int i = 0; i < totalDaysPassed; i++) {
            elixirProduct.update();
            dexterityVestProduct.update();
            anotherNormalProduct.update();
        }

        //then
        int normalDegradation = initialSellInDays * DEGRADE_NORMAL;
        int doubleDegradation = 4 * DEGRADE_TWICE;
        assertThat(elixir.quality).isEqualTo(initialQuality - normalDegradation - doubleDegradation);
        assertThat(dexterityVest.quality).isEqualTo(initialQuality - normalDegradation - doubleDegradation);
        assertThat(anotherNormal.quality).isEqualTo(initialQuality - normalDegradation - doubleDegradation);
    }

    @Test
    public void qualityDegradesNormalBeforeSellInThenTwiceAsFastThenNormal() {
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
        // 10 of degradation 1 -> 10
        // 5 of degradation 2 -> 10
        // 1 of degradation 1 -> 1
        int totalDaysPassed = 16;
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
    public void verifyQualityNeverBelowMinimum() {
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
    public void verifySellInDecreases() {
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