package uk.sigma_co.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.bettercombat.config.ClientConfig;
import net.bettercombat.config.ClientConfigWrapper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class GrovewatchClient implements ClientModInitializer {

    Path skibidiPath = FabricLoader.getInstance().getConfigDir().resolve("skibidi.txt");

    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("bettercombat")) {
            ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
                if (!Files.exists(skibidiPath)) {
                    ClientConfig config = AutoConfig.getConfigHolder(ClientConfigWrapper.class).getConfig().client;
                    config.isShowingArmsInFirstPerson = true;
                    AutoConfig.getConfigHolder(ClientConfigWrapper.class).save();
                    // REALLY scuffed, but meh
                    try {
                        Files.write(skibidiPath, List.of("67"), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        throw new RuntimeException("No permission to create skibidi.txt, please report this to https://github.com/NuttiesNuts", e);
                    }
                }
            });
        }
    }
}
