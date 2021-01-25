package com.gildedrose.store.product;

import com.gildedrose.Item;
import com.gildedrose.store.ProductBuilder;
import com.gildedrose.store.ProductType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class SulfurasTest extends BaseTest {

    private static final int SULFURAS_FIXED_QUALITY = 80;

    @Test
    public void verifyCreationAcceptingSpecificQuality() {
        //given
        int initialQuality = 50;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", 10, initialQuality);

        //when
        try {
            new ProductBuilder(ProductType.SULFURAS).buildFrom(actual);
            fail("should validate quality and throw IllegalArgumentException if different than zero");
        } catch (Exception e) {
            //then
            //successfully tested quality at object creation
        }
    }

    @Test
    public void verifyQualityNeverChanges() {
        //given
        int initialSellInDays = 10;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellInDays, SULFURAS_FIXED_QUALITY);
        Product product = new ProductBuilder(ProductType.SULFURAS).buildFrom(actual);

        //when
        int daysPassed = initialSellInDays + 1;
        for (int i = 0; i < daysPassed; i++) {
            product.update();
        }

        //then
        assertThat(actual.quality).isEqualTo(SULFURAS_FIXED_QUALITY);
    }

    @Test
    public void verifyTimeDoesNotPassForSulfuras() {
        //given
        int daysToPass = 60;
        int initialSellIn = 10;
        Item actual = new Item("Sulfuras, Hand of Ragnaros", initialSellIn, SULFURAS_FIXED_QUALITY);
        Product product = new ProductBuilder(ProductType.SULFURAS).buildFrom(actual);

        //when
        for (int i = 0; i < daysToPass; i++) {
            product.update();
        }

        //then
        assertThat(actual.sellIn).isEqualTo(initialSellIn);
    }
}
