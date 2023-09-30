package com.tcoded.terragenbiomesmockregistry;

import org.bukkit.plugin.java.JavaPlugin;
import org.terraform.main.TLogger;
import org.terraform.main.TerraformGeneratorPlugin;
import org.terraform.v1_20_R2.CustomBiomeHandler;

import java.io.File;

public final class TerragenBiomesMockRegistry extends JavaPlugin {

    @Override
    public void onEnable() {
        registerBiomes();
    }

    private void registerBiomes() {
        File pluginsFolder = getDataFolder().getParentFile();
        File terraFolder = new File(pluginsFolder, "TerraformGenerator");

        if (!terraFolder.exists()) {
            terraFolder.mkdirs();
        }

        TerraformGeneratorPlugin.logger = new TLogger();
        CustomBiomeHandler.init();
    }

//    private void doMagic() throws NoSuchFieldException, IllegalAccessException {
//        CraftServer craftserver = (CraftServer) Bukkit.getServer();
//        DedicatedServer dedicatedserver = craftserver.getServer();
//
//        Optional<Registry<Biome>> registry = dedicatedserver.registryAccess().registry(Registries.BIOME);
//        if (registry.isPresent()) {
//            getLogger().info("Biome registry is present");
//        } else {
//            getLogger().info("Biome registry is not present");
//            return;
//        }
//
//        Registry<Biome> biomeRegistry = registry.get();
//        MappedRegistry<Biome> writableRegistry = (MappedRegistry<Biome>) biomeRegistry;
//
//        Class<? extends WritableRegistry> writableRegistryClass = writableRegistry.getClass();
//        Field frozenField = writableRegistryClass.getDeclaredField("frozen");
//        frozenField.setAccessible(true);
//        frozenField.set(writableRegistry, false);
//
//        biomeRegistry.freeze();
//    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
