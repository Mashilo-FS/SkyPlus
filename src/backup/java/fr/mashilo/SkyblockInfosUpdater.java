package fr.mashilo;

import fr.mashilo.adapts.WebAPI;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class SkyblockInfosUpdater extends Thread {
    private FileWriter fileWriterCollections;
    private FileWriter fileWriterSkills;

    public SkyblockInfosUpdater(){
        System.out.println("[SIU] Thread created : " + this);
        setName("SkyblockInfosUpdater - Thread");
        start();
    }
    public void run() {
        try{
            String collections = WebAPI.get(Config.API_COLLECTIONS_URL + Config.APIKEY).toString();
            String skills = WebAPI.get(Config.API_SKILLS_URL + Config.APIKEY).toString();

            new FileWriter(Config.COLLECTION_PATH).write(
                    new JSONObject(collections).toString(3)
            );
            new FileWriter(Config.SKILLS_PATH).write(
                    new JSONObject(skills).toString(3)
            );

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue");
        }
        System.out.println("[SIU] Thread terminated : " + this);
    }
}
