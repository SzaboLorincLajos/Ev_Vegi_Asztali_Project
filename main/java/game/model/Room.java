package game.model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private String id;
    private String leiras;
    private Map<String, String> kijaratok;
    private boolean vanLada;
    private boolean ladaNyitva;
    private boolean vanEllenfel;

    public Room(String id, String leiras, boolean vanLada, boolean vanEllenfel) {
        this.id = id;
        this.leiras = leiras;
        this.vanLada = vanLada;
        this.ladaNyitva = false;
        this.vanEllenfel = vanEllenfel;
        this.kijaratok = new HashMap<>();
    }

    public Room() {
        this.kijaratok = new HashMap<>();
    }

    public void addKijarat(String irany, String celSzoba) {
        this.kijaratok.put(irany.toLowerCase(), celSzoba);
    }

    public String getKijarat(String irany) {
        return this.kijaratok.get(irany.toLowerCase());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLeiras() { return leiras; }
    public void setLeiras(String leiras) { this.leiras = leiras; }
    public Map<String, String> getKijaratok() { return kijaratok; }
    public void setKijaratok(Map<String, String> kijaratok) { this.kijaratok = kijaratok; }
    public boolean isVanLada() { return vanLada; }
    public void setVanLada(boolean vanLada) { this.vanLada = vanLada; }
    public boolean isLadaNyitva() { return ladaNyitva; }
    public void setLadaNyitva(boolean ladaNyitva) { this.ladaNyitva = ladaNyitva; }
    public boolean isVanEllenfel() { return vanEllenfel; }
    public void setVanEllenfel(boolean vanEllenfel) { this.vanEllenfel = vanEllenfel; }
}
