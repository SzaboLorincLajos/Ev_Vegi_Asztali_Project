package game.data;

import game.model.Room;

import java.util.HashMap;
import java.util.Map;

public class MapData {

    public static Map<String, Room> buildMap() {
        Map<String, Room> terkep = new HashMap<>();adbawhdoiwahdihawodh

        Room bejaratiCsarnok = new Room("bejárati_csarnok",
                "Egy sötét kőcsarnokban vagy. A falakon fáklyák égnek. Innen indul a kalandod, találd meg az összes ládát és élj túl!",
                false, false);
        bejaratiCsarnok.addKijarat("north", "folyosó");
        bejaratiCsarnok.addKijarat("east", "fegyvertározó");

        Room folyoso = new Room("folyosó",
                "Egy hosszú folyosón állsz. A levegő nyirkos és hideg. Sötét van, ajánlott körbenézni (look)",
                true, true);
        folyoso.addKijarat("south", "bejárati_csarnok");
        folyoso.addKijarat("north", "torony");

        Room fegyvertarozo = new Room("fegyvertározó",
                "Egy régi fegyvertároló szoba. A falakról rozsdás kardok lógnak, lehet lapul valami a kardok alatt..",
                true, false);
        fegyvertarozo.addKijarat("west", "bejárati_csarnok");
        fegyvertarozo.addKijarat("north", "titkos_kamra");

        Room torony = new Room("torony",
                "A vár tornyának tetején vagy. Innen belátni az egész vidéket. (Ne akarj leugrani)",
                false, true);
        torony.addKijarat("south", "folyosó");

        Room titkos_kamra = new Room("titkos_kamra",
                "Egy rejtett kamra. Úgy tűnik kevesen jártak itt. (max egy miskolci csöves) Innen egy titkos ajtó vezet északra.",
                true, true);
        titkos_kamra.addKijarat("south", "fegyvertározó");
        titkos_kamra.addKijarat("north", "sarkany_feszke");

        Room sarkanyFeszke = new Room("sarkany_feszke",
                "A sárkány fészke. Hatalmas csontok borítják a padlót, a levegőben kénszag tereng.",
                true, true); 
        sarkanyFeszke.addKijarat("south", "titkos_kamra"); 

        terkep.put(bejaratiCsarnok.getId(), bejaratiCsarnok);
        terkep.put(folyoso.getId(), folyoso);
        terkep.put(fegyvertarozo.getId(), fegyvertarozo);
        terkep.put(torony.getId(), torony);
        terkep.put(titkos_kamra.getId(), titkos_kamra);
        terkep.put(sarkanyFeszke.getId(), sarkanyFeszke);

        return terkep;
    }
}
