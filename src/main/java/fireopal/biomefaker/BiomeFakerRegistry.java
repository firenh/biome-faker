package fireopal.biomefaker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public enum BiomeFakerRegistry {
    /**
     * The instance of this
     */
    INSTANCE;

    private Map<Integer, Integer> customBiome2DefaultBiome = new HashMap<>(); 

    public Map<Integer, Integer> get() {
        return this.customBiome2DefaultBiome;
    }

    public int convertOrDefault(int inputId) {
        Optional<Integer> output = this.convertRawId(inputId);
        return output.isPresent() ? output.get() : inputId;
    }

    public Optional<Integer> convertRawId(int inputId) {
        Integer output = this.customBiome2DefaultBiome.getOrDefault(inputId, -1);
        return output.equals(-1) ? Optional.empty() : Optional.of(output);
    }

    /**
     * Convert a {@linkplain net.minecraft.resources.ResourceLocation} to another.
     * @param inputId The biome to convert
     * @return The converted biome
     */

    public Optional<ResourceLocation> convertIdentifier(ResourceLocation inputId) {
        Optional<Integer> possibleOutputRawId = this.convertRawId(
            BuiltinRegistries.BIOME.getId(
                BuiltinRegistries.BIOME.get(inputId)
            )
        );

        if (!possibleOutputRawId.isPresent()) {
            return Optional.empty();
        }

        Optional<Holder<Biome>> possibleOutputBiome = BuiltinRegistries.BIOME.getHolder(possibleOutputRawId.get());

        if (!possibleOutputBiome.isPresent()) {
            return Optional.empty();
        }

        return Optional.of( BuiltinRegistries.BIOME.getResourceKey(possibleOutputBiome.get().value()).get().location() );
    }

    public void addToRegistry(Integer key, Integer value) {
        this.customBiome2DefaultBiome.put(key, value);
    }

    public void addResourceLocationsToRegistry(ResourceLocation key, ResourceLocation value) {
        // BiomeFaker.LOGGER.info("biome:" + BuiltinRegistries.BIOME.get(key));
        
        int keyInt = BuiltinRegistries.BIOME.getId(
            BuiltinRegistries.BIOME.get(key)
        );
        int valueInt = BuiltinRegistries.BIOME.getId(
            BuiltinRegistries.BIOME.get(value)
        );

        // BiomeFaker.LOGGER.info("key: " + key + "; value: " + value);
        // BiomeFaker.LOGGER.info("keyInt: " + keyInt + "; valueInt: " + valueInt);
    
        this.customBiome2DefaultBiome.put(
            keyInt, valueInt
        );
    }
}
