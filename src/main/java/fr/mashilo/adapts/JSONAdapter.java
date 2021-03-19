package fr.mashilo.adapts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jetbrains.annotations.NotNull;
import org.json.*;

public class JSONAdapter {

    @NotNull
    public static synchronized JSONObject JSONReader(String filePath) throws IOException {
        String jsonFile = Files.readString(Paths.get(filePath));
        return new JSONObject(jsonFile);
    }

}


