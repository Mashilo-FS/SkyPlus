package fr.mashilo;

import fr.mashilo.adapts.JSONAdapter;
import org.json.JSONObject;

import java.io.IOException;

public class Item {

    private String id;

    private JSONObject names;
    private JSONObject family;

    private JSONObject recipes;
    private JSONObject market;

    private JSONObject collection;

    public Item(String name) throws IOException {
        this.id = name;
        this.names = JSONAdapter.JSONReader(Config.ITEMS_FOLDER).getJSONObject(name).getJSONObject("names");
        this.family = JSONAdapter.JSONReader(Config.ITEMS_FOLDER).getJSONObject(name).getJSONObject("family");
        this.recipes = JSONAdapter.JSONReader(Config.ITEMS_FOLDER).getJSONObject(name).getJSONObject("recipes");
        this.market = JSONAdapter.JSONReader(Config.ITEMS_FOLDER).getJSONObject(name).getJSONObject("market");
        this.collection = JSONAdapter.JSONReader(Config.ITEMS_FOLDER).getJSONObject(name).getJSONObject("collection");
    }

    public String getID(){
        return this.id;
    }
    public JSONObject getNames(){
        return this.names;
    }
    public JSONObject getFamily(){
        return this.family;
    }
    public JSONObject getRecipes(){
        return this.recipes;
    }
    public JSONObject getMarket(){
        return this.market;
    }
    public JSONObject getCollection(){
        return this.collection;
    }

    public void setMarket(JSONObject market) {
        this.market = market;
    }
}
