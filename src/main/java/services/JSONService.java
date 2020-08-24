package services;

import com.google.gson.Gson;
import database.DatabaseCredentials;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONService {

    public DatabaseCredentials readEnviroment() {
        try {
            Gson gson = new Gson();

            BufferedReader reader = Files.newBufferedReader(Paths.get("enviroment.json"));

            DatabaseCredentials credentials = gson.fromJson(reader, DatabaseCredentials.class);

            reader.close();

            return credentials;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
