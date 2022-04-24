package com.agricraft.agricore.templates.versions.v2;

import com.agricraft.agricore.json.AgriJsonVersion;
import com.agricraft.agricore.json.AgriSerializable;
import com.agricraft.agricore.templates.versions.v1.AgriPlant_1_12;
import com.agricraft.agricore.templates.versions.v1.AgriSoil_1_12;
import com.agricraft.agricore.templates.versions.v1.Versions_1_12;

import javax.annotation.Nullable;
import java.util.function.Function;

public class Versions_1_16 {
    public static final String VERSION = "1.16.4";

    public static final AgriJsonVersion<AgriMutation_1_16> MUTATION = new AgriJsonVersion<AgriMutation_1_16>() {
        @Override
        public Class<AgriMutation_1_16> getElementClass() {
            return AgriMutation_1_16.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriPlant_1_16> PLANT = new AgriJsonVersion<AgriPlant_1_16>() {
        @Override
        public Class<AgriPlant_1_16> getElementClass() {
            return AgriPlant_1_16.class;
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
        public Function<AgriSerializable, AgriPlant_1_16> versionConverter() {
            return (obj) -> {
                if(obj instanceof AgriPlant_1_12) {
                    return ((AgriPlant_1_12) obj).toNew();
                } else {
                    return null;
                }
            };
        }
    };

    public static final AgriJsonVersion<AgriWeed_1_16> WEED = new AgriJsonVersion<AgriWeed_1_16>() {
        @Override
        public Class<AgriWeed_1_16> getElementClass() {
            return AgriWeed_1_16.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };

    public static final AgriJsonVersion<AgriSoil_1_16> SOIL = new AgriJsonVersion<AgriSoil_1_16>() {
        @Override
        public Class<AgriSoil_1_16> getElementClass() {
            return AgriSoil_1_16.class;
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
        public Function<AgriSerializable, AgriSoil_1_16> versionConverter() {
            return (obj) -> {
                if(obj instanceof AgriSoil_1_12) {
                    return ((AgriSoil_1_12) obj).toNew();
                } else {
                    return null;
                }
            };
        }
    };

    public static final AgriJsonVersion<AgriFertilizer_1_16> FERTILIZER = new AgriJsonVersion<AgriFertilizer_1_16>() {
        @Override
        public Class<AgriFertilizer_1_16> getElementClass() {
            return AgriFertilizer_1_16.class;
        }

        @Override
        public String descriptor() {
            return VERSION;
        }
    };
}
