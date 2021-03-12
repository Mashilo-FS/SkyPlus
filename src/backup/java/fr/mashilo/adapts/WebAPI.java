package fr.mashilo.adapts;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebAPI {

    @NotNull
    public static synchronized JSONObject get(String url) throws IOException {
        URL link = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) link.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() != 200){
            throw new RuntimeException("HttpResponseCode Error : " + conn.getResponseCode());
        } else {

            Scanner scanner = new Scanner(link.openStream());
            JSONObject json = new JSONObject(scanner.nextLine());
            scanner.close();

            return(json);
        }
    }
}
