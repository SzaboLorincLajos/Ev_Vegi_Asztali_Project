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
        System.out.println("\nEllenfél jelenik meg: " + ellenfel.getNev());
        System.out.println(ellenfel.getNev() + " életerő: " + ellenfel.getEletero());

        while (jatekos.elHarcban() && ellenfel.elHarcban()) {
            System.out.println("\n-- Életkorod --");
            System.out.println("Életero: " + jatekos.getEletero() + "/" + jatekos.getMaxEletero()
                    + "  Mana: " + jatekos.getMana() + "/" + jatekos.getMaxMana()
                    + "  Gyógyital: " + jatekos.getGyogyital());
            System.out.println(ellenfel.getNev() + " életerő: " + ellenfel.getEletero());
            System.out.println("Mit teszel? (1) támadás  (2) varázslat  (3) gyógyital (4) menekülés");

            String valasz = scanner.nextLine().trim();

            if (valasz.equals("1")) {
                int alapSebzes = jatekos.getSebzes();
                if (rand.nextInt(100) < 20) { // 20% kritikus találat
                    int kritikusSebzes = alapSebzes * 2;
                    ellenfel.sebzodes(kritikusSebzes);
                    System.out.println("Kritikus találat! Dupla sebzést vittél be: " + kritikusSebzes);
                } else {
                    ellenfel.sebzodes(alapSebzes);
                    System.out.println("Megtámadtad az ellenfelet! Sebzés: " + alapSebzes);
                }
            } else if (valasz.equals("2")) {
                boolean siker = jatekos.varazslat(ellenfel);
                if (siker) {
                    System.out.println("Varázslat! Sebzés: " + (jatekos.getSebzes() * 2));
                } else {
                    System.out.println("Nincs elég manad! (kell: 20)");
                    continue;
                }
            } else if (valasz.equals("3")) {
                boolean siker = jatekos.gyogyit();
                if (siker) {
                    System.out.println("Gyógyitalt használtál. Életerő: " + jatekos.getEletero());
                } else {
                    System.out.println("Nincs több gyógyitalod!");
                    continue;
                }
            } else if (valasz.equals("4")) {
                if (rand.nextInt(100) < 40) {
                    System.out.println("Sikeresen elmenekültél a harcból!");
                    return false; 
                } else {
                    System.out.println("Nem sikerült elmenekülnöd, az ellenfél utadat állja!");
                }
            } else {
                System.out.println("Érvénytelen parancs. Írj 1, 2, 3 vagy 4-et.");
                continue;

            if (!ellenfel.elHarcban()) {
                System.out.println("\nLegyőzted: " + ellenfel.getNev() + "!");
                return true;
            }

            int ellenfelSebzes = ellenfel.tamad();
            int tenylegesSebzes = ellenfelSebzes - rand.nextInt(4);
            if (tenylegesSebzes < 1) tenylegesSebzes = 1;
            jatekos.sebzodes(tenylegesSebzes);
            System.out.println(ellenfel.getNev() + " támad! Kaptál " + tenylegesSebzes + " sebzést.");

            if (!jatekos.elHarcban()) {
                System.out.println("\nMeghaltál a harcban... :(");
                return false;
            }
        }

        return jatekos.elHarcban();
    }
}
