package me.tomqnto.mysticWeaving;

import de.slikey.effectlib.EffectManager;
import me.tomqnto.mysticWeaving.commands.*;
import me.tomqnto.mysticWeaving.listeners.JoinListener;
import me.tomqnto.mysticWeaving.listeners.MysticDustListener;
import me.tomqnto.mysticWeaving.managers.PlayerData;
import me.tomqnto.mysticWeaving.menus.api.InventoryListener;
import me.tomqnto.mysticWeaving.tasks.ActionBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticWeaving extends JavaPlugin {

    public static MysticWeaving plugin;
    private EffectManager effectManager;

    @Override
    public void onEnable() {

        plugin = this;
        effectManager = new EffectManager(this);

        PlayerData.loadConfig();

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new MysticDustListener(), this);

        getCommand("add_mystic_dust").setExecutor(new AddMagicDustCommand());
        getCommand("mysticgui").setExecutor(new MysticGuiCommand());
        getCommand("levelup").setExecutor(new LevelUpCommand());
        getCommand("upgrade_energy_level").setExecutor(new UpgradeEnergyLevelCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("upgrade_energy_regen_amount").setExecutor(new UpgradeEnergyRegenAmountCommand());
        getCommand("get_mystic_dust").setExecutor(new GetMysticDustCommand());

        ActionBar actionBar = new ActionBar();
        actionBar.runTaskTimer(this, 0, 5);

    }

    @Override
    public void onDisable() {
        if (effectManager != null)
            effectManager.dispose();
    }

    public EffectManager getEffectManager() {
        return effectManager;
    }
}