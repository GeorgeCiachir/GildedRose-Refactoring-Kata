package com.gildedrose.product;

import com.gildedrose.Item;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ProductFactory {

    private static final Map<String, ProductType> PRODUCT_TYPES = getProductTypes();

    private static Map<String, ProductType> getProductTypes() {
        return Arrays.stream(ProductType.values())
                .collect(toMap(ProductType::getItemName, type -> type));
    }

    public static Product newProduct(Item item) {
        ProductType productType = PRODUCT_TYPES.getOrDefault(item.name, ProductType.NORMAL);

        return new ProductBuilder(productType)
                .buildFrom(item);
    }
}
