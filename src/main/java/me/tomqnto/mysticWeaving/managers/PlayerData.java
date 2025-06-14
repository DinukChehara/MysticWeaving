package me.tomqnto.mysticWeaving.managers;

import me.tomqnto.mysticWeaving.MysticWeaving;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    private static File file;
    private static FileConfiguration config;

    public static FileConfiguration getConfig(){
        return config;
    }

    public static void loadConfig(){
        file = new File(MysticWeaving.plugin.getDataFolder(), "player_data.yml");
        if (!file.exists())
            MysticWeaving.plugin.saveResource("player_data.yml", false);

        config = MysticWeaving.plugin.getConfig();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getEnergy(Player player) {
        return getConfig().getInt(player.getName() + ".energy");
    }



    public static int getMaxEnergy(Player player) {
        return getConfig().getInt(player.getName() + ".max-energy");
    }

    public static void setMaxEnergy(Player player,int amount) {
        getConfig().set(player.getName() + ".max-energy", amount);
        saveConfig();
    }

    public static int getNextLevelupCost(Player player){
        return (int) Math.floor(100 * Math.pow(getLevel(player) + 1, 1.5));
    }

    public static int getLevel(Player player) {
        return getConfig().getInt(player.getName() + ".level");
    }

    public static void levelup(Player player) {
        getConfig().set(player.getName() + ".level", getLevel(player) + 1);
        setLevelUpgradePaid(player, 0);
        saveConfig();
    }

    public static void ifNoDataSetDefaults(Player player){
        if (!getConfig().contains(player.getName()))
            setDefaults(player);
    }

    public static void setDefaults(Player player){
        getConfig().set(player.getName() + ".level", 0);
        getConfig().set(player.getName() + ".energy", 50);
        getConfig().set(player.getName() + ".max-energy", 50);
        getConfig().set(player.getName() + ".energy-regen-amount", 1);
        getConfig().set(player.getName() + ".energy-level-upgrade-paid", 0);
        getConfig().set(player.getName() + ".energy-regen-upgrade-paid", 0);
        getConfig().set(player.getName() + ".mystic-level-upgrade-paid", 0);
        saveConfig();
    }

    public static int getEnergyUpgradeCost(Player player){
        int upgradedAmount = ((getMaxEnergy(player)-50)/25);
        int baseCost = 150;
        double scale = 1.5;

        return (int) Math.floor(baseCost * Math.pow(scale, upgradedAmount));
    }

    public static void upgradeEnergyLevel(Player player){
        setMaxEnergy(player, getMaxEnergy(player) + 25);
        setEnergyLevelUpgradePaid(player, 0);
        saveConfig();
    }

    public static int getEnergyRegenAmount(Player player){
        return getConfig().getInt(player.getName() + ".energy-regen-amount");
    }

    public static void upgradeEnergyRegenAmount(Player player){
        getConfig().set(player.getName() + ".energy-regen-amount", getEnergyRegenAmount(player) + 1);
        setEnergyRegenUpgradePaid(player, 0);
        saveConfig();
    }

    public static int getEnergyRegenAmountUpgradeCost(Player player){
        return (int) Math.round(200 * Math.pow(1.4, getEnergyRegenAmount(player)-1));
    }

    public static int getLevelUpgradePaid(Player player){
        return getConfig().getInt(player.getName() + ".mystic-level-upgrade-paid");
    }

    public static void setLevelUpgradePaid(Player player, int amount){
        getConfig().set(player.getName() + ".mystic-level-upgrade-paid", amount);
    }

    public static void addLevelUpgradePaid(Player player, int amount){
        setLevelUpgradePaid(player, getLevelUpgradePaid(player) + amount);
    }

    public static int getEnergyLevelUpgradePaid(Player player){
        return getConfig().getInt(player.getName() + ".energy-level-upgrade-paid");
    }

    public static void setEnergyLevelUpgradePaid(Player player, int amount){
        getConfig().set(player.getName() + ".energy-level-upgrade-paid", amount);
    }

    public static void addEnergyLevelUpgradePaid(Player player, int amount){
        setEnergyLevelUpgradePaid(player, getEnergyLevelUpgradePaid(player) + amount);
    }

    public static int getEnergyRegenUpgradePaid(Player player){
        return getConfig().getInt(player.getName() + ".energy-regen-upgrade-paid");
    }

    public static void setEnergyRegenUpgradePaid(Player player, int amount){
        getConfig().set(player.getName() + ".energy-regen-upgrade-paid", amount);
    }

    public static void addEnergyRegenUpgradePaid(Player player, int amount){
        setEnergyRegenUpgradePaid(player, getEnergyRegenUpgradePaid(player) + amount);
    }

}
