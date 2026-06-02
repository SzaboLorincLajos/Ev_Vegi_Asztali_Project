package game.logic;

import game.model.Character;
import game.model.Enemy;

import java.util.Random;
import java.util.Scanner;

public class CombatManager {

    private Scanner scanner;
    private Random rand;

    public CombatManager(Scanner scanner) {
        this.scanner = scanner;
        this.rand = new Random();
    }

    public boolean harc(Character jatekos, Enemy ellenfel) {
        System.out.println("\nEllenfel jelenik meg: " + ellenfel.getNev());
        System.out.println(ellenfel.getNev() + " eletero: " + ellenfel.getEletero());

        while (jatekos.elHarcban() && ellenfel.elHarcban()) {
            System.out.println("\n-- A te korod --");
            System.out.println("Eletero: " + jatekos.getEletero() + "/" + jatekos.getMaxEletero()
                    + "  Mana: " + jatekos.getMana() + "/" + jatekos.getMaxMana()
                    + "  Gyogyital: " + jatekos.getGyogyital());
            System.out.println(ellenfel.getNev() + " eletero: " + ellenfel.getEletero());
            System.out.println("Mit teszel? (1) tamadas  (2) varazslat  (3) gyogyital");

            String valasz = scanner.nextLine().trim();

            if (valasz.equals("1")) {
                jatekos.tamad(ellenfel);
                System.out.println("Tamadsz! Sebzés: " + jatekos.getSebzes());
            } else if (valasz.equals("2")) {
                boolean siker = jatekos.varazslat(ellenfel);
                if (siker) {
                    System.out.println("Varazslat! Sebzés: " + (jatekos.getSebzes() * 2));
                } else {
                    System.out.println("Nincs eleg manad! (kell: 20)");
                    continue;
                }
            } else if (valasz.equals("3")) {
                boolean siker = jatekos.gyogyit();
                if (siker) {
                    System.out.println("Gyogyitalt hasznaltál. Eletero: " + jatekos.getEletero());
                } else {
                    System.out.println("Nincs tobbe gyogyitalod!");
                    continue;
                }
            } else {
                System.out.println("Ervenytelen parancs. Irj 1, 2 vagy 3-at.");
                continue;
            }

            if (!ellenfel.elHarcban()) {
                System.out.println("\nLegyozted: " + ellenfel.getNev() + "!");
                return true;
            }

            int ellenfelSebzes = ellenfel.tamad();
            int tenylegesSebzes = ellenfelSebzes - rand.nextInt(4);
            if (tenylegesSebzes < 1) tenylegesSebzes = 1;
            jatekos.sebzodes(tenylegesSebzes);
            System.out.println(ellenfel.getNev() + " tamad! Kapsz " + tenylegesSebzes + " sebzest.");

            if (!jatekos.elHarcban()) {
                System.out.println("\nMeghaltál a harcban...");
                return false;
            }
        }

        return jatekos.elHarcban();
    }
}
