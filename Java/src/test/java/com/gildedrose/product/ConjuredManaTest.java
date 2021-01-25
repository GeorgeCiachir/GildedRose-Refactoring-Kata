package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConjuredManaTest extends BaseTest {

    private static final int DEGRADE_NORMAL = 2;
    private static final int DEGRADE_TWICE = 2 * DEGRADE_NORMAL;
    private static final int MIN_QUALITY = 0;

    @Test
    public void degradesNormalBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 30;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        int daysPassed = initialSellInDays - 1;
        for (int i = 0; i < daysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        int degradation = DEGRADE_NORMAL * daysPassed;
        assertThat(conjured.quality).isEqualTo(initialQuality - degradation);
    }

    @Test
    public void degradesNormalThenStepOneBeforeSellIn() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 3;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        // 1 of degradation 2 -> 2
        // 1 of degradation 1 -> 1
        int daysPassed = 3;
        for (int i = 0; i < daysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        assertThat(conjured.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void degradesNormalBeforeSellInThenTwiceAsFastThenNormal() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 31;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        // 10 of degradation 2 -> 20
        // 2 of degradation 4 -> 8
        // 1 of degradation 2 -> 2
        int totalDaysPassed = 13;
        for (int i = 0; i < totalDaysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        int normalDegradation = initialSellInDays * DEGRADE_NORMAL;
        int doubleDegradation = 2 * DEGRADE_TWICE;
        int oneStepDegradation = 1 * DEGRADE_NORMAL;
        assertThat(conjured.quality).isEqualTo(initialQuality - normalDegradation - doubleDegradation - oneStepDegradation);
        assertThat(conjured.quality).isEqualTo(1);
    }

    @Test
    public void degradesNormalBeforeSellInThenTwiceAsFastThenNormalThenStepOne() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 31;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        // 10 of degradation 2 -> 20
        // 2 of degradation 4 -> 8
        // 1 of degradation 2 -> 2
        // 1 of degradation 1 -> 1
        int totalDaysPassed = 14;
        for (int i = 0; i < totalDaysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        assertThat(conjured.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void qualityNeverGoesBelowZero() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 10;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        int totalDaysPassed = initialSellInDays + 1;
        for (int i = 0; i < totalDaysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        assertThat(conjured.quality).isEqualTo(MIN_QUALITY);
    }

    @Test
    public void testSellInDecrease() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 20;
        Item conjured = new Item("Conjured Mana Cake", initialSellInDays, initialQuality);
        Product conjuredProduct = new ProductBuilder(ProductType.CONJURED_MANA).buildFrom(conjured);

        //when
        int totalDaysPassed = 60;
        for (int i = 0; i < totalDaysPassed; i++) {
            conjuredProduct.update();
        }

        //then
        assertThat(conjured.sellIn).isEqualTo(initialSellInDays - totalDaysPassed);
    }
}
