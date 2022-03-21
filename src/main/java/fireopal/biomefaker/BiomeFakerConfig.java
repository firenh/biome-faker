package fireopal.biomefaker;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

public class BiomeFakerConfig {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Config Default Values

    public String CONFIG_VERSION_DO_NOT_TOUCH_PLS = BiomeFaker.VERSION.toString();

    public Map<String, String> biomes = BiomeFaker.getDefaultBiomeMappings();
    
    //~~~~~~~~


    public static BiomeFakerConfig init() {
        BiomeFakerConfig config = null;

        try {
            Path configPath = Paths.get("", "config", BiomeFaker.MODID + ".json");

            if (Files.exists(configPath)) {
                config = gson.fromJson(
                    new FileReader(configPath.toFile()),
                    BiomeFakerConfig.class
                );

                if (!config.CONFIG_VERSION_DO_NOT_TOUCH_PLS.equals(BiomeFaker.VERSION.toString())) {
                    config.CONFIG_VERSION_DO_NOT_TOUCH_PLS = BiomeFaker.VERSION.toString();

                    BufferedWriter writer = new BufferedWriter(
                        new FileWriter(configPath.toFile())
                    );

                    writer.write(gson.toJson(config));
                    writer.close();
                }

            } else {
                config = new BiomeFakerConfig();
                Paths.get("", "config").toFile().mkdirs();

                BufferedWriter writer = new BufferedWriter(
                    new FileWriter(configPath.toFile())
                );

                writer.write(gson.toJson(config));
                writer.close();
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return config;
    }
}
