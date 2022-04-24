package com.agricraft.agricore.plant.versions.v1;

import com.agricraft.agricore.plant.versions.v2.AgriProductList_1_16;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AgriProductList_1_12 {
    private final List<AgriProduct_1_12> products;


    public AgriProductList_1_12() {
        this.products = new ArrayList<>();
    }

    public AgriProductList_1_12(List<AgriProduct_1_12> products) {
        this.products = products;
    }

    public AgriProductList_1_16 toNew() {
        return new AgriProductList_1_16(this.products.stream().map(AgriProduct_1_12::toNew).collect(Collectors.toList()));
    }

}
