package com.gildedrose.store;

import com.gildedrose.Item;
import com.gildedrose.store.product.AgedBrie;
import com.gildedrose.store.product.BackstagePasses;
import com.gildedrose.store.product.ConjuredMana;
import com.gildedrose.store.product.NormalProduct;
import com.gildedrose.store.product.Sulfuras;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductFactoryTest {

    @Test
    public void verifyAppropriateProductCreation() {
        Item dexterityVest = new Item("+5 Dexterity Vest", 10, 20);
        Item elixir = new Item("Elixir of the Mongoose", 5, 7);
        Item someNewNormalItem = new Item("New Normal Item", 5, 7);

        Item agedBrie = new Item("Aged Brie", 2, 0);
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        Item conjuredMana = new Item("Conjured Mana Cake", 3, 6);

        assertThat(ProductFactory.newProduct(dexterityVest)).isInstanceOf(NormalProduct.class);
        assertThat(ProductFactory.newProduct(elixir)).isInstanceOf(NormalProduct.class);
        assertThat(ProductFactory.newProduct(someNewNormalItem)).isInstanceOf(NormalProduct.class);

        assertThat(ProductFactory.newProduct(agedBrie)).isInstanceOf(AgedBrie.class);
        assertThat(ProductFactory.newProduct(sulfuras)).isInstanceOf(Sulfuras.class);
        assertThat(ProductFactory.newProduct(backstagePasses)).isInstanceOf(BackstagePasses.class);
        assertThat(ProductFactory.newProduct(conjuredMana)).isInstanceOf(ConjuredMana.class);
    }
}