package net.prasyb.miraimcchat.registry;

import net.minecraftforge.common.config.Configuration;
import net.prasyb.miraimcchat.config.IHasConfig;

import java.util.ArrayList;
import java.util.List;

public class ConfigRegistry {
    private static Configuration config;
    private static List<IHasConfig> configHandlers;

    public static Configuration getConfig() {
        return config;
    }

    public static void init(Configuration cfg) {
        config = cfg;
        config.load();
        configHandlers = new ArrayList<>();
    }

    public static void register(IHasConfig cfg) {
        configHandlers.add(cfg);
    }

    public static void syncAllConfig() {
        for (IHasConfig cfg : ConfigRegistry.configHandlers) {
            cfg.syncConfig(config);
        }
        config.save();
    }
}
