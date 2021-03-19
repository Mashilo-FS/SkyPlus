package fr.mashilo;

import fr.mashilo.adapts.JSONAdapter;
import fr.mashilo.adapts.NamingConvention;
import org.json.JSONObject;

import java.io.IOException;

public class Item {

    private String id;
    private JSONObject names;
    private JSONObject family;
    private JSONObject recipes;
    private JSONObject market;

    public Item(String name) throws IOException {
        JSONObject itemFile = JSONAdapter.JSONReader(Config.ITEMS_FOLDER + new NamingConvention().convert(name) + ".json");
        this.id = new NamingConvention().convert(name);
        this.names = itemFile.getJSONObject("names");
        this.family = itemFile.getJSONObject("family");
        this.recipes = itemFile.getJSONObject("recipes");
        this.market = itemFile.getJSONObject("market");
    }

    public synchronized String getID(){
        return this.id;
    }
    public synchronized JSONObject getNames(){
        return this.names;
    }
    public synchronized JSONObject getFamily(){
        return this.family;
    }
    public synchronized JSONObject getRecipes(){
        return this.recipes;
    }
    public synchronized JSONObject getMarket(){
        return this.market;
    }
}
