package services;

import com.google.gson.Gson;
import database.DatabaseCredentials;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONService {

    public DatabaseCredentials readEnvironment() {

        try {
            Gson gson = new Gson();
            BufferedReader reader = Files.newBufferedReader(Paths.get("src\\main\\resources\\environment.json"));
            DatabaseCredentials credentials = gson.fromJson(reader, DatabaseCredentials.class);
            reader.close();
            return credentials;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
