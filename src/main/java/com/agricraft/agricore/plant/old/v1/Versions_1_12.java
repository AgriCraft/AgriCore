package com.agricraft.agricore.plant.old.v1;

import com.agricraft.agricore.json.AgriJsonVersion;

public class Versions_1_12 {
    public static final AgriJsonVersion<AgriPlant_1_12> PLANT = new AgriJsonVersion<AgriPlant_1_12>() {
        @Override
        public Class<AgriPlant_1_12> getElementClass() {
            return AgriPlant_1_12.class;
        }

        @Override
        public String descriptor() {
            return "1.12.2";
        }
    };

    public static final AgriJsonVersion<AgriSoil_1_12> SOIL = new AgriJsonVersion<AgriSoil_1_12>() {
        @Override
        public Class<AgriSoil_1_12> getElementClass() {
            return AgriSoil_1_12.class;
        }

        @Override
        public String descriptor() {
            return "1.12.2";
        }
    };
}
