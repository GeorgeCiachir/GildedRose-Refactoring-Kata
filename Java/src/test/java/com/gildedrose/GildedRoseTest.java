package com.gildedrose;

import com.gildedrose.store.product.Product;
import com.gildedrose.store.ProductFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void testUpdatingIsDelegatedToProduct() {
        //given
        Item actualItem = new Item("foo", 0, 0);
        Item[] items = new Item[]{actualItem};
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        //then
        Item expectedItem = new Item("foo", 0, 0);
        Product product = ProductFactory.newProduct(expectedItem);
        product.update();
        assertThat(actualItem.toString()).isEqualTo(expectedItem.toString());
    }

}
