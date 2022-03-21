package fireopal.biomefaker.mixin;

import java.util.Objects;
import java.util.Optional;

import org.geysermc.geyser.translator.level.BiomeTranslator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import fireopal.biomefaker.BiomeFakerRegistry;
import net.minecraft.resources.ResourceLocation;

@Mixin(BiomeTranslator.class)
public class BiomeTranslatorMixin {
	// @Inject(
	// 	method = "loadServerBiomes",
	// 	at = @At("TAIL"), 
	// 	remap = false
	// )
	// private static void loadServerBiomes(GeyserSession session, CompoundTag codec, CallbackInfo ci) {
	// 	Int2IntMap biomeTranslations = session.getBiomeTranslations();

	// 	for (Map.Entry<Integer, Integer> entry : BiomeFakerRegistry.INSTANCE.get().entrySet()) {
	// 		BiomeFaker.LOGGER.info(biomeTranslations.get((int) (entry.getKey())));
	// 	}
	// }

	@ModifyVariable(
		method = "loadServerBiomes(Lorg/geysermc/geyser/session/GeyserSession;Lcom/github/steveice10/opennbt/tag/builtin/CompoundTag;)V",
		at = @At("STORE"), 
		ordinal = 0,
		remap = false
	)
	private static String convertBedrockId(String javaIdentifier) {
		ResourceLocation possibleResourceLocation = ResourceLocation.tryParse(javaIdentifier); 
		if (Objects.isNull(possibleResourceLocation)) return javaIdentifier;
		
		Optional<ResourceLocation> possibleNewIdentifier = BiomeFakerRegistry.INSTANCE.convertIdentifier(possibleResourceLocation);
		if (possibleNewIdentifier.isEmpty()) return javaIdentifier;

		ResourceLocation newIdentifier = possibleNewIdentifier.get();

		// BiomeFaker.LOGGER.info("Biome id " + javaIdentifier + " converted to new biome id " + newIdentifier);
		return newIdentifier.toString();
	}

	@ModifyVariable(
		method = "loadServerBiomes(Lorg/geysermc/geyser/session/GeyserSession;Lcom/github/steveice10/opennbt/tag/builtin/CompoundTag;)V",
		at = @At("STORE"), 
		ordinal = 0,
		remap = false
	)
	private static int bedrockId(int bedrockId) {
		// BiomeFaker.LOGGER.info("bedrockId: " + bedrockId);
		return bedrockId;
	}

	// @ModifyVariable(
	// 	method = "loadServerBiomes(Lorg/geysermc/geyser/session/GeyserSession;Lcom/github/steveice10/opennbt/tag/builtin/CompoundTag;)V",
	// 	at = @At("STORE"), 
	// 	ordinal = 1,
	// 	remap = false
	// )
	// private static int convertJavaId(int javaId) {
	// 	int newId = BiomeFakerRegistry.INSTANCE.convertOrDefault(javaId);
	// 	if (newId != javaId) BiomeFaker.LOGGER.info("Biome id " + javaId + " converted to new biome id " + newId);
	// 	return newId;
	// }


	// @Inject(method = "loadServerBiomes", at = @At("HEAD"), cancellable = false) 
	// public static void loadServerBiomes(GeyserSession session, CompoundTag codec) {
	// 	CompoundTag worldGen = codec.getCompound("minecraft:worldgen/biome");
    //     ListTag serverBiomes = (ListTag) worldGen.get("value");

	// 	for (Tag tag : serverBiomes) {
	// 		CompoundTag biomeTag = (CompoundTag) tag;
	// 		String javaIdentifier = ((StringTag) biomeTag.get("name")).getAsString();
			
    //         int bedrockId = Registries.BIOME_IDENTIFIERS.get().getOrDefault(javaIdentifier, -1);
    //         int javaId = ((IntTag) biomeTag.get("id")).getAsInt();
	// 		int convertedId = BiomeFakerRegistry.INSTANCE.convertRawId(javaId).orElse(-1);

	// 		if (convertedId >= 0) {
				
	// 		}


	// 	}
	// }
}
