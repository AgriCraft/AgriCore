package com.agricraft.agricore.templates;

import com.agricraft.agricore.core.AgriCore;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class AgriRequirement {
    private final AgriSoilCondition soil_humidity;
    private final AgriSoilCondition soil_acidity;
    private final AgriSoilCondition soil_nutrients;

    private final int min_light;
    private final int max_light;
    private final double light_tolerance_factor;

    private final AgriListCondition biomes;
    private final AgriListCondition dimensions;

    private final List<String> seasons;
    private final List<AgriBlockCondition> conditions;
    private final AgriStateObject fluid;

    public AgriRequirement() {
        this.soil_humidity = new AgriSoilCondition("damp", "equal", 0.15D);
        this.soil_acidity = new AgriSoilCondition("neutral", "equal", 0.2D);
        this.soil_nutrients = new AgriSoilCondition("medium", "equal_or_higher", 0.1D);
        this.min_light = 10;
        this.max_light = 16;
        this.light_tolerance_factor = 0.5D;
        this.biomes = new AgriListCondition();
        this.dimensions = new AgriListCondition();
        this.seasons = Lists.newArrayList("spring", "summer", "autumn", "winter");
        this.conditions = new ArrayList<>();
        this.fluid = new AgriStateObject("fluid", "minecraft:empty");
    }

    public AgriRequirement(AgriSoilCondition soil_humidity, AgriSoilCondition soil_acidity, AgriSoilCondition soil_nutrients,
                           int min_light, int max_light, double light_tolerance_factor, AgriListCondition biomes, AgriListCondition dimensions,
                           List<String> seasons, List<AgriBlockCondition> conditions, AgriStateObject fluid) {
        this.soil_humidity = soil_humidity;
        this.soil_acidity = soil_acidity;
        this.soil_nutrients = soil_nutrients;
        this.min_light = min_light;
        this.max_light = max_light;
        this.light_tolerance_factor = light_tolerance_factor;
        this.biomes = biomes;
        this.dimensions = dimensions;
        this.seasons = new ArrayList<>(seasons);
        this.conditions = conditions;
        this.fluid = fluid;
    }

    public AgriSoilCondition getHumiditySoilCondition() {
        return this.soil_humidity;
    }

    public AgriSoilCondition getAciditySoilCondition() {
        return this.soil_acidity;
    }

    public AgriSoilCondition getNutrientSoilCondition() {
        return this.soil_nutrients;
    }

    public int getMinLight() {
        return this.min_light;
    }

    public int getMaxLight() {
        return this.max_light;
    }

    public double getLightToleranceFactor() {
        return this.light_tolerance_factor;
    }

    public AgriListCondition getBiomeCondition() {
        return this.biomes;
    }

    public AgriListCondition getDimensionCondition() {
        return this.dimensions;
    }

    public List<String> getSeasons() {
        return this.seasons;
    }

    public List<AgriBlockCondition> getConditions() {
        return new ArrayList<>(this.conditions);
    }

    public AgriObject getFluid() {
        return this.fluid;
    }

    public boolean validate() {
        if(!this.getHumiditySoilCondition().validate(value -> AgriCore.getValidator().isValidHumidity(value))) {
            return false;
        }
        if(!this.getAciditySoilCondition().validate(value -> AgriCore.getValidator().isValidAcidity(value))) {
            return false;
        }
        if(!this.getNutrientSoilCondition().validate(value -> AgriCore.getValidator().isValidNutrients(value))) {
            return false;
        }
        if(this.getMinLight() > this.getMaxLight()) {
            AgriCore.getCoreLogger().info("Minimum light value can not be higher than the maximum light value");
            return false;
        }
        for(String season : this.getSeasons()) {
            if(!AgriCore.getValidator().isValidSeason(season)) {
                AgriCore.getCoreLogger().info("Invalid Season: \"{0}\"", season);
                return false;
            }
        }
        for (AgriBlockCondition condition : this.getConditions()) {
            if (!condition.validate()) {
                AgriCore.getCoreLogger().info("Invalid Block Condition: {0}", condition);
                return false;
            }
        }
        if(!this.getFluid().validate()) {
            AgriCore.getCoreLogger().info("Invalid Fluid Condition: {0}", this.getFluid());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nRequirement:");
        sb.append("\n\t- Soil Humidity:");
        sb.append("\n\t\t").append(this.getHumiditySoilCondition().toString());
        sb.append("\n\t- Soil Acidity:");
        sb.append("\n\t\t").append(this.getAciditySoilCondition().toString());
        sb.append("\n\t- Soil Nutrients:");
        sb.append("\n\t\t").append(this.getNutrientSoilCondition().toString());
        sb.append("\n\t- Light:");
        sb.append("\n\t\t- Min: ").append(min_light);
        sb.append("\n\t\t- Max: ").append(max_light);
        sb.append("\n\t- Biomes:");
        sb.append("\n\t\t").append(this.getBiomeCondition().toString());
        sb.append("\n\t- Dimensions:");
        sb.append("\n\t\t").append(this.getDimensionCondition().toString());
        sb.append("\n\t- Seasons: ");
        this.getSeasons().forEach(s -> sb.append("\n\t\t").append(s));
        sb.append("\n\t- Conditions:");
        this.getConditions().forEach(c -> sb.append("\n\t\t- ").append(c.toString().replaceAll("\n", "\n\t\t").trim()));
        return sb.toString();
    }

}
