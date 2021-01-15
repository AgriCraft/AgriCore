package com.agricraft.agricore.plant.old.v1;

import com.agricraft.agricore.plant.AgriProductList;

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

    public AgriProductList toNew() {
        return new AgriProductList(this.products.stream().map(AgriProduct_1_12::toNew).collect(Collectors.toList()));
    }

}
