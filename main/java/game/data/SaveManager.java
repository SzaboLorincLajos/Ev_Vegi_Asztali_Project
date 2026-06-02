package game.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.model.GameState;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class SaveManager {

    private static final String MENTES_FAJL = "mentes.json";
    private Gson gson;

    public SaveManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public boolean ment(GameState allapot) {
        try {
            FileWriter writer = new FileWriter(MENTES_FAJL);
            gson.toJson(allapot, writer);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Hiba a mentés közben: " + e.getMessage());
            return false;
        }
    }

    public GameState betolt() {
        File fajl = new File(MENTES_FAJL);
        if (!fajl.exists()) {
            return null;
        }
        try {
            FileReader reader = new FileReader(MENTES_FAJL);
            GameState allapot = gson.fromJson(reader, GameState.class);
            reader.close();
            return allapot;
        } catch (IOException e) {
            System.out.println("Hiba a betöltés közben: " + e.getMessage());
            return null;
        }
    }

    public boolean vanMentes() {
        return new File(MENTES_FAJL).exists();
    }

    public void torolMentest() {
        File fajl = new File(MENTES_FAJL);
        if (fajl.exists()) {
            fajl.delete();
        }
    }
}
