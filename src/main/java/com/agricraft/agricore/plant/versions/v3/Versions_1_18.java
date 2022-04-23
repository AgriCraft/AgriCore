package com.agricraft.agricore.plant.versions.v3;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.AgriMutation;
import com.agricraft.agricore.plant.AgriPlant;
import com.agricraft.agricore.plant.AgriSoil;
import com.agricraft.agricore.plant.AgriWeed;
import com.agricraft.agricore.plant.fertilizer.AgriFertilizer;
import com.agricraft.agricore.plant.versions.v1.AgriPlant_1_12;
import com.agricraft.agricore.plant.versions.v1.AgriSoil_1_12;
import com.agricraft.agricore.plant.versions.v1.Versions_1_12;
import com.agricraft.agricore.plant.versions.v2.AgriPlant_1_16;
import com.agricraft.agricore.plant.versions.v2.Versions_1_16;

import javax.annotation.Nullable;
import java.util.function.Function;

public class Versions_1_18 {
    public static final String VERSION = "1.18.2";

    public static final AgriJsonVersion<AgriMutation> MUTATION = new AgriJsonVersion<>() {
        @Override
        public Class<AgriMutation> getElementClass() {
            return AgriMutation.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriPlant> PLANT = new AgriJsonVersion<>() {
        @Override
        public Class<AgriPlant> getElementClass() {
            return AgriPlant.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }

        @Nullable
        public AgriJsonVersion<AgriPlant_1_16> previousVersion() {
            return Versions_1_16.PLANT;
        }

        @Nullable
        public Function<AgriSerializable, AgriPlant> versionConverter() {
            return (obj) -> {
                if(obj instanceof AgriPlant_1_12) {
                    return ((AgriPlant_1_12) obj).toNew().toNew();
                } else if(obj instanceof AgriPlant_1_16) {
                    return ((AgriPlant_1_16) obj).toNew();
                } else {
                    return null;
                }
            };
        }
    };

    public static final AgriJsonVersion<AgriWeed> WEED = new AgriJsonVersion<>() {
        @Override
        public Class<AgriWeed> getElementClass() {
            return AgriWeed.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriSoil> SOIL = new AgriJsonVersion<>() {
        @Override
        public Class<AgriSoil> getElementClass() {
            return AgriSoil.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }

        @Nullable
        public AgriJsonVersion<AgriSoil_1_12> previousVersion() {
            return Versions_1_12.SOIL;
        }

        @Nullable
        public Function<AgriSerializable, AgriSoil> versionConverter() {
            return (obj) -> {
                if(obj instanceof AgriSoil_1_12) {
                    return ((AgriSoil_1_12) obj).toNew();
                } else {
                    return null;
                }
            };
        }
    };

    public static final AgriJsonVersion<AgriFertilizer> FERTILIZER = new AgriJsonVersion<>() {
        @Override
        public Class<AgriFertilizer> getElementClass() {
            return AgriFertilizer.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };
}
