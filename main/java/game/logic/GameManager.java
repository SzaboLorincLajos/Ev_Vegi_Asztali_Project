package game.logic;

import game.data.MapData;
import game.data.SaveManager;
import game.model.Character;
import game.model.Enemy;
import game.model.GameState;
import game.model.Room;

import java.util.Map;
import java.util.Scanner;

public class GameManager {

    private GameState allapot;
    private Map<String, Room> terkep;
    private SaveManager saveManager;
    private CombatManager combatManager;
    private Scanner scanner;

    public GameManager(Scanner scanner) {
        this.scanner = scanner;
        this.terkep = MapData.buildMap();
        this.saveManager = new SaveManager();
        this.combatManager = new CombatManager(scanner);
    }

    public void ujJatek(String nev, String kaszt) {
        Character karakter = new Character(nev, kaszt);
        this.allapot = new GameState(karakter, "bejarati_csarnok");
    }

    public boolean betoltJatek() {
        GameState betoltott = saveManager.betolt();
        if (betoltott == null) {
            return false;
        }
        this.allapot = betoltott;
        return true;
    }

    public boolean vanMentes() {
        return saveManager.vanMentes();
    }

    public void ment() {
        boolean ok = saveManager.ment(allapot);
        if (ok) {
            System.out.println("Jatek mentve.");
        }
    }

    public void korokFeldolgozasa(String parancs) {
        String[] reszek = parancs.trim().toLowerCase().split(" ");

        if (reszek.length == 0 || parancs.isEmpty()) {
            System.out.println("Nem adtal meg parancsot.");
            return;
        }

        String akcio = reszek[0];

        if (akcio.equals("go") && reszek.length >= 2) {
            mozgas(reszek[1]);
        } else if (akcio.equals("open") && reszek.length >= 2 && reszek[1].equals("chest")) {
            ladaNyitas();
        } else if (akcio.equals("look")) {
            szobaNezese();
        } else if (akcio.equals("status")) {
            karakterStatusz();
        } else if (akcio.equals("save")) {
            ment();
        } else if (akcio.equals("help")) {
            segitseg();
        } else {
            System.out.println("Ismeretlen parancs. Ird: help");
        }
    }

    private void mozgas(String irany) {
        Room jelenlegiSzoba = terkep.get(allapot.getJelenlegiSzobaId());
        String celSzobaId = jelenlegiSzoba.getKijarat(irany);

        if (celSzobaId == null) {
            System.out.println("Arra nem tudsz menni.");
            return;
        }

        allapot.setJelenlegiSzobaId(celSzobaId);
        Room celSzoba = terkep.get(celSzobaId);
        System.out.println("\nAtmegyek: " + celSzoba.getId().replace("_", " "));
        System.out.println(celSzoba.getLeiras());

        if (celSzoba.isVanEllenfel()) {
            System.out.println("\nEllenfel van a szobaban!");
            Enemy ellenfel = new Enemy();
            boolean gyozelem = combatManager.harc(allapot.getKarakter(), ellenfel);
            if (!gyozelem) {
                allapot.setJatekVege(true);
                saveManager.torolMentest();
            } else {
                celSzoba.setVanEllenfel(false);
            }
        }

        kijaratok(celSzoba);
    }

    private void ladaNyitas() {
        Room jelenlegiSzoba = terkep.get(allapot.getJelenlegiSzobaId());

        if (!jelenlegiSzoba.isVanLada()) {
            System.out.println("Nincs lada ebben a szobaban.");
            return;
        }

        if (jelenlegiSzoba.isLadaNyitva()) {
            System.out.println("A lada mar nyitva van, es ures.");
            return;
        }

        jelenlegiSzoba.setLadaNyitva(true);
        int talalt = (int) (Math.random() * 3) + 1;
        allapot.getKarakter().setGyogyital(allapot.getKarakter().getGyogyital() + talalt);
        System.out.println("Kinyitod a ladat! Talalsz benne " + talalt + " gyogyitalt.");
        System.out.println("Gyogyitalok szama: " + allapot.getKarakter().getGyogyital());
    }

    private void szobaNezese() {
        Room jelenlegiSzoba = terkep.get(allapot.getJelenlegiSzobaId());
        System.out.println("\n" + jelenlegiSzoba.getLeiras());

        if (jelenlegiSzoba.isVanLada() && !jelenlegiSzoba.isLadaNyitva()) {
            System.out.println("Latszik egy lada a szobaban. (open chest)");
        }

        kijaratok(jelenlegiSzoba);
    }

    private void kijaratok(Room szoba) {
        if (!szoba.getKijaratok().isEmpty()) {
            System.out.print("Kijaratok: ");
            System.out.println(String.join(", ", szoba.getKijaratok().keySet()));
        }
    }

    private void karakterStatusz() {
        Character k = allapot.getKarakter();
        System.out.println("\n-- " + k.getName() + " (" + k.getKaszt() + ") --");
        System.out.println("Eletero: " + k.getEletero() + "/" + k.getMaxEletero());
        System.out.println("Mana:    " + k.getMana() + "/" + k.getMaxMana());
        System.out.println("Sebzes:  " + k.getSebzes());
        System.out.println("Gyogyital: " + k.getGyogyital());
    }

    private void segitseg() {
        System.out.println("\nErheto parancsok:");
        System.out.println("  go north/south/east/west  - mozgas");
        System.out.println("  open chest                - lada kinyitasa");
        System.out.println("  look                      - szoba megnezese");
        System.out.println("  status                    - karakter adatok");
        System.out.println("  save                      - jatek mentese");
        System.out.println("  help                      - parancsok listaja");
        System.out.println("  exit                      - kilepes");
    }

    public void szobaMegjelenites() {
        szobaNezese();
    }

    public boolean isJatekVege() {
        return allapot.isJatekVege();
    }
}
