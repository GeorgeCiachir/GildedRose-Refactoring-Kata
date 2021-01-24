package com.gildedrose.product;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class SulfurasTest extends BaseTest {

    @Test
    public void testProductCreationAcceptingSpecificQuality() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 50;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellInDays, initialQuality);

        //when
        try {
            Product.builder(Sulfuras::new).forItem(actual);
            fail("should validate quality and throw IllegalArgumentException if different than zero");
        } catch (Exception e) {
            //then
            //successfully tested quality at object creation
        }
    }

    @Test
    public void qualityRemainsFixed() {
        //given
        int initialSellInDays = 10;
        int initialQuality = 80;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellInDays, initialQuality);
        Product product = Product.builder(Sulfuras::new).forItem(actual);

        //when
        int daysPassed = initialSellInDays + 1;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(initialQuality);
    }

    @Test
    public void testTimeDoesNotPassForSulfuras() {
        //given
        int daysToPass = 60;
        int initialSellIn = 10;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellIn, 80);
        Product product = Product.builder(Sulfuras::new).forItem(actual);

        //when
        for (int i = 0; i < daysToPass; i++) {
            product.update();
        }

        //then
        assertThat(actual.sellIn).isEqualTo(initialSellIn);
    }
}
