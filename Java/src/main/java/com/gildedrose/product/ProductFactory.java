package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class ProductFactory {

    private static final Map<String, Function<Item, Product>> CONSTRUCTORS = getProductConstructors();

    private static Map<String, Function<Item, Product>> getProductConstructors() {
        return Arrays.stream(ProductType.values())
                .collect(toMap(ProductType::getItemName, ProductType::getProductConstructor));
    }

    public static Product newProduct(Item item) {
        Function<Item, Product> productConstructor =
                CONSTRUCTORS.getOrDefault(item.name, defaultProductConstructor());

        return Product
                .builder(productConstructor)
                .forItem(item);
    }

    private static Function<Item, Product> defaultProductConstructor() {
        return CONSTRUCTORS.get(ProductType.NORMAL.getItemName());
    }

}
