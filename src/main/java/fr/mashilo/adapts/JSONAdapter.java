package fr.mashilo.adapts;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import fr.mashilo.Config;
import org.jetbrains.annotations.NotNull;
import org.json.*;

public class JSONAdapter {
    private static FileWriter fileWriter;

    @NotNull
    public static synchronized JSONObject JSONReader(String filePath) throws IOException {
        String jsonFile = Files.readString(Paths.get(filePath));
        return new JSONObject(jsonFile);
    }

    public static synchronized void JSONWriter(String filePath, String jsonPath, String key, Integer value, String use) throws IOException{


        String jsonFile = Files.readString(Paths.get(filePath));
        JSONObject json = new JSONObject(jsonFile);

        JSONObject bazaar = json.getJSONObject("ACACIA_WOOD").getJSONObject("market").getJSONObject("bazaar");
        bazaar.put("buy",
                WebAPI.get(Config.API_BAZAAR_URL + Config.APIKEY).getJSONObject("products").getJSONObject("LOG_2").getJSONObject("quick_status").getDouble("buyPrice")
        );
        System.out.println(json.getJSONObject("ACACIA_WOOD").getJSONObject("market").getJSONObject("bazaar"));
        System.out.println(WebAPI.get(Config.API_BAZAAR_URL+Config.APIKEY).getDouble("lastUpdated"));

        try{
            fileWriter = new FileWriter(Config.ITEMS_FOLDER);
            fileWriter.write(json.toString(3));
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            fileWriter.flush();
            fileWriter.close();
        }

    }

    /*
            modifier : items.json / ACACIA_WOOD / market / bazaar / buy-sell

            fonction(items.json, ACACIA_WOOD/market/bazaar, buy-sell)
    */

}


