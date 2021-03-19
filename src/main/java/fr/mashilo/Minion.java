package fr.mashilo;

import fr.mashilo.adapts.NamingConvention;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import fr.mashilo.adapts.JSONAdapter;

public class Minion {

    private String name;
    private String folder;
    private JSONArray upgrades_compatible;
    private JSONArray generated_items;
    private JSONArray quantity;
    private JSONArray tiers;

    public Minion(String name) throws IOException {
        JSONObject minionFile = JSONAdapter.JSONReader(Config.MINIONS_FOLDER + new NamingConvention().convert(name) + ".json");
        this.name = name;
        this.folder = minionFile.getString("folder");
        this.upgrades_compatible = minionFile.getJSONArray("upgrades_compatible");
        this.generated_items = minionFile.getJSONArray("generated_items");
        this.quantity = minionFile.getJSONArray("quantity");
        this.tiers = minionFile.getJSONArray("tiers");
    }

    public String getName() {
        return this.name;
    }

    public String getFolder() {
        return this.folder;
    }

    public String getUpgrades_compatible() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.upgrades_compatible.length(); i++) {
            result.append(this.upgrades_compatible.get(i)).append(" ");
        }
        return result.toString();
    }

    public JSONArray getGenerated_items() {
        return this.generated_items;
    }

    public JSONArray getQuantity() {
        return this.quantity;
    }

    public JSONArray getTiers() {
        return this.tiers;
    }

    public JSONObject item_summary(int tier, int time) throws IOException {
        /*
        Best seller determined and selected for each ressources generated
         */

        double cooldown = getTiers().getJSONObject(tier - 1).getDouble("cooldown");
        JSONArray drops = getGenerated_items();
        JSONObject summary = new JSONObject();

        for (Object item : drops) {
            summary.put((String) item, 0);
        }

        for (Object item: drops) {
            JSONObject itemMarket = JSONAdapter.JSONReader(Config.ITEMS_FOLDER + new NamingConvention().convert((String) item) + ".json")
                    .getJSONObject("market");
            double betterPrice = 0;
            String betterSeller = "";
            if (itemMarket.getBoolean("in_bazaar")){
                betterPrice = Math.max(
                        itemMarket.getJSONObject("npc").getDouble("sell"),
                        itemMarket.getJSONObject("bazaar").getDouble("sell")
                );
                betterSeller = itemMarket.getJSONObject("bazaar").getDouble("sell") > itemMarket.getJSONObject("npc").getDouble("sell")
                        ? "bazaar"
                        : "npc";
            } else betterPrice = itemMarket.getJSONObject("npc").getDouble("sell"); betterSeller = "npc";

        }
        return new JSONObject();
    }

    public JSONObject item_summary(int tier, int time, String seller) throws IOException{
        /*
        Force a seller as sell method
         */
        return  new JSONObject();
    }

}