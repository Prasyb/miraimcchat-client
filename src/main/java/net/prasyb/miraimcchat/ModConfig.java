package net.prasyb.miraimcchat;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfig {
    private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.ConfigValue<String> HOST;
    public static ForgeConfigSpec.IntValue PORT;
    public static ForgeConfigSpec.BooleanValue IS_ENABLED;
    public static ForgeConfigSpec.ConfigValue<String> KEY;
    static {
        CFG.comment("Connection Settings").push("connection");
        HOST = CFG.comment("Server Host").define("host", "127.0.0.1");
        PORT = CFG.comment("Server Port").defineInRange("port", 0,0,65536);
        IS_ENABLED = CFG.comment("Enable Client").define("enabled", true);
        KEY = CFG.comment("Access Key").define("key", "");
        CFG.pop();
        COMMON_CONFIG = CFG.build();
    }

    public static void setup(Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        COMMON_CONFIG.setConfig(configData);
    }
}
