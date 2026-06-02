package game.data;

import game.model.Room;

import java.util.HashMap;
import java.util.Map;

public class MapData {

    public static Map<String, Room> buildMap() {
        Map<String, Room> terkep = new HashMap<>();

        Room bejaratiCsarnok = new Room("bejarati_csarnok",
                "Egy sötét kőcsarnokban vagy. A falakon fáklyák égnek. Innen indul a kalandod.",
                false, false);
        bejaratiCsarnok.addKijarat("north", "folyoso");
        bejaratiCsarnok.addKijarat("east", "fegyvertarozo");

        Room folyoso = new Room("folyoso",
                "Egy hosszú folyosón állsz. A levegő nyirkos és hideg.",
                true, true);
        folyoso.addKijarat("south", "bejarati_csarnok");
        folyoso.addKijarat("north", "torony");

        Room fegyvertarozo = new Room("fegyvertarozo",
                "Egy régi fegyvertároló szoba. A falakról rozsdás kardok lógnak.",
                true, false);
        fegyvertarozo.addKijarat("west", "bejarati_csarnok");
        fegyvertarozo.addKijarat("north", "titkos_kamra");

        Room torony = new Room("torony",
                "A vár tornyának tetején vagy. Innen belátni az egész vidéket.",
                false, true);
        torony.addKijarat("south", "folyoso");

        Room titkos_kamra = new Room("titkos_kamra",
                "Egy rejtett kamra. Úgy tűnik kevesen jártak itt.",
                true, true);
        titkos_kamra.addKijarat("south", "fegyvertarozo");

        terkep.put(bejaratiCsarnok.getId(), bejaratiCsarnok);
        terkep.put(folyoso.getId(), folyoso);
        terkep.put(fegyvertarozo.getId(), fegyvertarozo);
        terkep.put(torony.getId(), torony);
        terkep.put(titkos_kamra.getId(), titkos_kamra);

        return terkep;
    }
}
