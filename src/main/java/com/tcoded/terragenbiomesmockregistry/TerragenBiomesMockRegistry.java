package com.tcoded.terragenbiomesmockregistry;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import org.terraform.main.TLogger;
import org.terraform.main.TerraformGeneratorPlugin;
import org.terraform.v1_19_R3.CustomBiomeHandler;

import java.lang.reflect.Field;
import java.util.Optional;

public final class TerragenBiomesMockRegistry extends JavaPlugin {

    @Override
    public void onEnable() {
        doMagic2();
    }

    private void doMagic2() {
        TerraformGeneratorPlugin.logger = new TLogger();
        CustomBiomeHandler.init();
    }

    private void doMagic() throws NoSuchFieldException, IllegalAccessException {
        CraftServer craftserver = (CraftServer) Bukkit.getServer();
        DedicatedServer dedicatedserver = craftserver.getServer();

        Optional<Registry<Biome>> registry = dedicatedserver.registryAccess().registry(Registries.BIOME);
        if (registry.isPresent()) {
            getLogger().info("Biome registry is present");
        } else {
            getLogger().info("Biome registry is not present");
            return;
        }

        Registry<Biome> biomeRegistry = registry.get();
        MappedRegistry<Biome> writableRegistry = (MappedRegistry<Biome>) biomeRegistry;

        Class<? extends WritableRegistry> writableRegistryClass = writableRegistry.getClass();
        Field frozenField = writableRegistryClass.getDeclaredField("frozen");
        frozenField.setAccessible(true);
        frozenField.set(writableRegistry, false);

        biomeRegistry.freeze();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
