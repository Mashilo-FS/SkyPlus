package fr.mashilo;

import fr.mashilo.adapts.NamingConvention;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import fr.mashilo.adapts.JSONAdapter;

public class Minion{

    private String name;
    private String folder;
    private JSONArray upgrades_compatible;
    private JSONArray generated_items;
    private JSONArray quantity;
    private JSONArray tiers;

    private float cooldown;
    private int slots;

    private String recipeType;
    private String centeredItem;
    private String[] items;
    private int itemsQuantityPerSlot;

    public Minion(String name) throws IOException {
        this.name = name;
        this.folder = getMinionFolder(name);
        this.upgrades_compatible = getMinionUpgrades_compatibles(name);
        this.generated_items = getMinionGenerated_items(name);
        this.quantity = getMinionQuantity(name);
        this.tiers = getMinionTiers(name);
    }

    public String getName(){
        return this.name;
    }
    public String getFolder(){
        return this.folder;
    }
    public String getUpgrades_compatible(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.upgrades_compatible.length(); i++){
            result.append(this.upgrades_compatible.get(i)).append(" ");
        }
        return result.toString();
    }
    public JSONArray getGenerated_items(){
        return this.generated_items;
    }
    public JSONArray getQuantity(){
        return this.quantity;
    }
    public JSONArray getTiers(){
        return this.tiers;
    }
    public JSONObject getTier(int tier){
        return getTiers().getJSONObject(tier-1);
    }
    public long getCooldown(int tier){
        return getTier(tier).getLong("cooldown");
    }
    public int getSlots(int tier){
        return getTier(tier).getInt("slots");
    }

    public static String getMinionFolder(String minionName) throws IOException {
        return JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(minionName))
                .getString("folder");
    }
    public static JSONArray getMinionUpgrades_compatibles(String minionName) throws IOException {
        return JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(minionName))
                .getJSONArray("upgrades_compatible");
    }
    public static JSONArray getMinionGenerated_items(String minionName) throws IOException {
        return JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(minionName))
                .getJSONArray("generated_items");
    }
    public static JSONArray getMinionQuantity(String minionName) throws IOException {
        return JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(minionName))
                .getJSONArray("quantity");
    }
    public static JSONArray getMinionTiers(String minionName) throws IOException {
        return JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(minionName))
                .getJSONArray("tiers");
    }
}
