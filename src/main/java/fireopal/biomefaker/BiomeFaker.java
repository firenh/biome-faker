package fireopal.biomefaker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BiomeFaker implements ModInitializer {
	public static final String MODID = "biomefaker";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final FOModVersion VERSION = FOModVersion.fromString("0.0.0");

	private static BiomeFakerConfig config = BiomeFakerConfig.init();

	public static void reload() {
		config = BiomeFakerConfig.init();

		for (Map.Entry<String, String> entry : config.biomes.entrySet()) {
			ResourceLocation key = ResourceLocation.tryParse(entry.getKey());
			ResourceLocation value = ResourceLocation.tryParse(entry.getValue());

			// LOGGER.info("RLkey: " + key + "; RLvalue: " + value);

			BiomeFakerRegistry.INSTANCE.addResourceLocationsToRegistry(key, value);
		}

		for (Map.Entry<Integer, Integer> entry : BiomeFakerRegistry.INSTANCE.get().entrySet()) {
			LOGGER.info("Biomefaker:: Old Id: " + entry.getKey() + " => New Id: " + entry.getValue());
		}
	}

	@Override
	public void onInitialize() {
		reload();
	}

	public static Map<String, String> getDefaultBiomeMappings() {
		Map<String, String> map = new HashMap<>();
		map.put("thermorarium:crimson_plains", "minecraft:crimson_forest");
		map.put("thermorarium:warped_plains", "minecraft:warped_forest");
		return map;
	}
}
