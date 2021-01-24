package com.gildedrose.product;

import com.gildedrose.Item;
import com.gildedrose.product.NormalProduct;
import com.gildedrose.product.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {

    private static final int DAYS_TO_PASS = 45;

    @Test
    public void testSellInDecrease() {
        //given
        int initialSellIn = 10;
        Item actual = new Item("+5 Dexterity Vest", initialSellIn, 20);
        Product product = Product.builder(NormalProduct::new).forItem(actual);

        //when
        for (int i = 0; i < DAYS_TO_PASS; i++) {
            product.update();
        }

        //then
        assertThat(actual.sellIn).isEqualTo(initialSellIn - DAYS_TO_PASS);
    }

    @Test
    void testToString() {
        //given
        Item item = new Item("+5 Dexterity Vest", 10, 20);

        //when
        Product actual = Product.builder(NormalProduct::new).forItem(item);

        //then
        assertThat(actual.toString()).isEqualTo(item.toString());

    }
}
