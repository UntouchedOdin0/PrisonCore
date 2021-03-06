package me.untouchedodin0.prisoncore.modules.ranks;

import co.aikar.commands.PaperCommandManager;
import me.untouchedodin0.prisoncore.Module;
import me.untouchedodin0.prisoncore.PrisonCore;
import me.untouchedodin0.prisoncore.modules.ranks.commands.RanksCommand;
import me.untouchedodin0.prisoncore.modules.ranks.config.RanksConfig;
import org.bukkit.Material;
import redempt.redlib.config.ConfigManager;

public class Ranks implements Module {

    PrisonCore prisonCore = PrisonCore.getInstance();
    PaperCommandManager paperCommandManager;
    boolean enabled = false;

    @Override
    public String getName() {
        return "Ranks";
    }

    @Override
    public String getDescription() {
        return "Ranks module";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getAuthor() {
        return "UntouchedOdin0";
    }

    @Override
    public void onEnable() {
        prisonCore.getLogger().info(String.format("Module %s v%s has been enabled!", getName(), getVersion()));
        enabled = true;
        paperCommandManager = PrisonCore.getPaperCommandManager();
        prisonCore.getLogger().info(String.format("Registering commands for module %s v%s...", getName(), getVersion()));
        paperCommandManager.registerCommand(new RanksCommand());
    }

    @Override
    public void onDisable() {
        prisonCore.getLogger().info(String.format("Module %s v%s has been disabled!", getName(), getVersion()));
        enabled = false;
    }

    @Override
    public void onReload() {
        prisonCore.getLogger().info(String.format("Reloading module %s v%s...", getName(), getVersion()));

        prisonCore.getLogger().info(String.format("%s v%s has been reloaded!", getName(), getVersion()));
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void saveDefaultConfig() {
        ConfigManager configManager = ConfigManager
                .create(prisonCore, "ranks.yml")
                .addConverter(Material.class, Material::valueOf, Material::toString)
                .target(RanksConfig.class)
                .saveDefaults()
                .load();
    }
}
