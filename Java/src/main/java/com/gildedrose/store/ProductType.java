package com.gildedrose.store;

import com.gildedrose.Item;
import com.gildedrose.store.product.AgedBrie;
import com.gildedrose.store.product.BackstagePasses;
import com.gildedrose.store.product.ConjuredMana;
import com.gildedrose.store.product.NormalProduct;
import com.gildedrose.store.product.Product;
import com.gildedrose.store.product.Sulfuras;
import com.gildedrose.store.validation.Validator;

import java.util.List;
import java.util.function.Function;

public enum ProductType {

    NORMAL("Normal", NormalProduct::new, NormalProduct.getValidators()),
    AGED_BRIE("Aged Brie", AgedBrie::new, AgedBrie.getValidators()),
    SULFURAS("Sulfuras, Hand of Ragnaros", Sulfuras::new, Sulfuras.getValidators()),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", BackstagePasses::new, BackstagePasses.getValidators()),
    CONJURED_MANA("Conjured Mana Cake", ConjuredMana::new, ConjuredMana.getValidators());

    private final String itemName;
    private final Function<Item, Product> productConstructor;
    private final List<Validator> productValidators;

    ProductType(String itemName, Function<Item, Product> productConstructor, List<Validator> productValidators) {
        this.itemName = itemName;
        this.productConstructor = productConstructor;
        this.productValidators = productValidators;
    }

    public String getItemName() {
        return itemName;
    }

    public Function<Item, Product> getProductConstructor() {
        return productConstructor;
    }

    public List<Validator> getProductValidators() {
        return productValidators;
    }
}
