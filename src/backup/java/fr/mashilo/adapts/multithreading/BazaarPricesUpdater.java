package fr.mashilo.adapts.multithreading;

import fr.mashilo.Config;
import fr.mashilo.adapts.NamingConvention;
import fr.mashilo.adapts.WebAPI;
import fr.mashilo.adapts.JSONAdapter;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Iterator;

public class BazaarPricesUpdater extends Thread {

    public BazaarPricesUpdater(){
        System.out.println("[BPU] Thread created : " + this);
        setName("BazaarPricesUpdater - Thread");
        start();
    }
    public void run(){
        try{
            JSONObject response = new JSONObject(WebAPI.get(Config.API_BAZAAR_URL + Config.APIKEY).toString()).getJSONObject("products");
            for (Iterator<String> it = response.keys(); it.hasNext(); ) {
                String next = it.next();
                NamingConvention item = new NamingConvention();
                String itemFileName = item.convert(next);
                try{
                    File file = new File(Config.ITEMS_FOLDER + "\\" + itemFileName + ".json");
                    FileWriter fileWriter;
                    if (file.exists()){
                        JSONObject itemFile = JSONAdapter.JSONReader(file.getPath());
                        itemFile.getJSONObject("market").getJSONObject("bazaar").put(
                                "buy",
                                response.getJSONObject(next).getJSONObject("quick_status").getDouble("buyPrice")
                        );
                        itemFile.getJSONObject("market").getJSONObject("bazaar").put(
                                "sell",
                                response.getJSONObject(next).getJSONObject("quick_status").getDouble("sellPrice")
                        );

                        fileWriter = new FileWriter(file.getPath());
                        fileWriter.write(itemFile.toString(3));

                    } else {
                        System.out.println("Erreur fichier pour " + itemFileName);
                        System.out.println("Création du fichier pour " + itemFileName);
                        fileWriter = new FileWriter(file.getPath());
                        fileWriter.write(JSONAdapter.JSONReader(Config.ITEMS_FOLDER + "\\" + "INK_SACK_3" + ".json").toString(3));
                    }
                    fileWriter.flush();
                    fileWriter.close();
                } catch (NoSuchFileException ex){
                    ex.printStackTrace();
                    System.out.println("Erreur Fichiers ITEM pour : " + itemFileName);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("[BPU] Thread terminated : " + this);
    }
}
