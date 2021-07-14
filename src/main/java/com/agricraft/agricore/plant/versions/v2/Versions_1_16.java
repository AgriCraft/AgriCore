package com.agricraft.agricore.plant.versions.v2;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.plant.*;
import com.agricraft.agricore.plant.fertilizer.AgriFertilizer;
import com.agricraft.agricore.plant.versions.v1.AgriPlant_1_12;
import com.agricraft.agricore.plant.versions.v1.AgriSoil_1_12;
import com.agricraft.agricore.plant.versions.v1.Versions_1_12;

import javax.annotation.Nullable;
import java.util.function.Function;

public class Versions_1_16 {
    public static final String VERSION = "1.16.4";

    public static final AgriJsonVersion<AgriMutation> MUTATION = new AgriJsonVersion<AgriMutation>() {
        @Override
        public Class<AgriMutation> getElementClass() {
            return AgriMutation.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriPlant> PLANT = new AgriJsonVersion<AgriPlant>() {
        @Override
        public Class<AgriPlant> getElementClass() {
            return AgriPlant.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }

        @Nullable
        public AgriJsonVersion<AgriPlant_1_12> previousVersion() {
            return Versions_1_12.PLANT;
        }

        @Nullable
        public Function<AgriSerializable, AgriPlant> versionConverter() {
            return (obj) -> {
                if(obj instanceof AgriPlant_1_12) {
                    return ((AgriPlant_1_12) obj).toNew();
                } else {
                    return null;
                }
            };
        }
    };

    public static final AgriJsonVersion<AgriWeed> WEED = new AgriJsonVersion<AgriWeed>() {
        @Override
        public Class<AgriWeed> getElementClass() {
            return AgriWeed.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriSoil> SOIL = new AgriJsonVersion<AgriSoil>() {
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

    public static final AgriJsonVersion<AgriFertilizer> FERTILIZER = new AgriJsonVersion<AgriFertilizer>() {
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
