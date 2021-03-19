package fr.mashilo.adapts.multithreading;

import fr.mashilo.Config;
import fr.mashilo.adapts.NamingConvention;
import fr.mashilo.adapts.WebAPI;
import fr.mashilo.adapts.JSONAdapter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

public class BazaarPricesUpdater extends Thread {
    private FileWriter fileWriter;

    public BazaarPricesUpdater(){
        setName("BazaarPricesUpdater - Thread");
        start();
    }
    public void run(){
        try{
            JSONObject raw_response = new JSONObject(WebAPI.get(Config.API_BAZAAR_URL + Config.APIKEY).toString());
            JSONObject response = raw_response.getJSONObject("products");
            for (Iterator<String> iterator = response.keys() ; iterator.hasNext(); ){
                String next = iterator.next();
                String itemLocalName = new NamingConvention().convert(next);
                try{
                    File localFile = new File(Config.ITEMS_FOLDER + itemLocalName + ".json");
                    if (!localFile.exists()){
                        fileWriter = new FileWriter(localFile.getPath());
                        fileWriter.write(
                                JSONAdapter.JSONReader(Config.ITEMS_FOLDER + "defaultItem.json").toString(3)
                        );
                    }
                    else {
                        JSONObject itemFile = JSONAdapter.JSONReader(localFile.getPath());
                        itemFile.getJSONObject("market").put("in_bazaar", true);
                        itemFile.getJSONObject("market").getJSONObject("bazaar").put(
                                "buy",
                                response.getJSONObject(next).getJSONObject("quick_status").getDouble("buyPrice")
                        );
                        itemFile.getJSONObject("market").getJSONObject("bazaar").put(
                                "sell",
                                response.getJSONObject(next).getJSONObject("quick_status").getDouble("sellPrice")
                        );
                        fileWriter = new FileWriter(localFile.getPath());
                        fileWriter.write(itemFile.toString(3));
                    }
                    fileWriter.flush();
                    fileWriter.close();

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            JSONObject lastUpdatedJson = JSONAdapter.JSONReader(Config.ITEMS_FOLDER + "last_updated.json");
            lastUpdatedJson.put("last_updated", raw_response.getBigInteger("lastUpdated"));
            File lastUpdatedFile = new File(Config.ITEMS_FOLDER + "last_updated.json");
            fileWriter = new FileWriter(lastUpdatedFile.getPath());
            fileWriter.write(
                    lastUpdatedJson.toString(3)
            );
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
