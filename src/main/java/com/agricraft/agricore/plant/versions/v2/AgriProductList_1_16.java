package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.plant.AgriProductList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriProductList_1_16 {

    private final List<AgriProduct_1_16> products;

    public AgriProductList_1_16() {
        this.products = new ArrayList<>();
    }

    public AgriProductList_1_16(List<AgriProduct_1_16> products) {
        this.products = products;
    }

    public AgriProductList toNew() {
        return new AgriProductList(
                this.products.stream()
                        .map(AgriProduct_1_16::toNew)
                        .collect(Collectors.toList())
        );
    }
}
