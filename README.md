# biomefaker

## A fabric mod for GeyserMC that translates custom biome IDs to vanilla biome IDs for bedrock players.

In Java Edition, custom biomes can be made easily, such as with data packs, and ran on the server for players to use. However, if you use GeyserMC to allow Bedrock players to join your server with those custom biomes, GeyserMC will have to translate that biome ID to a vanilla biome ID, as Bedrock doesn't have the same capabilities as Java for server-side biomes, causing those biome effects to be that of another biome. Most of the time GeyserMC can infer the biome properly by category, but sometimes not, in which case biomefaker comes in handy. 

The biggest reason to use biomefaker (and the reason I made it in the first place) is for custom variant Nether biomes. I have a mod that adds, for instance, a Crimson and Warped Plains biome, which are simply biomes like their forest counterparts, but with less huge fungi. On Java, those biomes look fine, but on Bedrock via GeyserMC they have the nether wastes' dull red fog, no particles, and just feel strange. So, using biomefaker those biome IDs can be translated to those of Crimson and Warped Forests, fixing the issue.

The mod works by using Mixin to `@ModifyVariable` the Java String ID, replacing it with a different ID specified from the config. If an ID isn't specified in the config or it is invalid, the ID will just not translate; either way it leaves GeyserMC to do the rest with Java => Bedrock biome translation.

The mod is tested and works fine on GeyserMC Fabric 2.0.2-SNAPSHOT plus Floodgate-Fabric, but Floodgate-Fabric is not needed.

Dependencies: 
 - [Geyser-Fabric](https://github.com/GeyserMC/Geyser-Fabric)
 - [Fabric API](https://github.com/FabricMC/fabric)
