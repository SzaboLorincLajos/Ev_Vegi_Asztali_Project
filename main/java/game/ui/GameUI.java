package game.ui;

import game.logic.GameManager;

import java.util.Scanner;

public class GameUI {

    private Scanner scanner;
    private GameManager gameManager;

    public GameUI() {
        this.scanner = new Scanner(System.in);
        this.gameManager = new GameManager(scanner);
    }

    public void start() {
        System.out.println("Szöveges Kalandjáték");
        System.out.println();

        foMenu();
    }

    private void foMenu() {
        while (true) {
            System.out.println("1. Új jatek");
            System.out.println("2. Betöltés");
            System.out.println("3. Kilépés");
            System.out.print("> ");

            String valasz = scanner.nextLine().trim();

            if (valasz.equals("1")) {
                ujJatekMenu();
                break;
            } else if (valasz.equals("2")) {
                if (!gameManager.vanMentes()) {
                    System.out.println("Nincs mentett játék.\n");
                } else {
                    boolean ok = gameManager.betoltJatek();
                    if (ok) {
                        System.out.println("Játék betöltve.");
                        jatekHurok();
                    } else {
                        System.out.println("Hiba a betöltésnél.\n");
                    }
                    break;
                }
            } else if (valasz.equals("3")) {
                System.out.println("Viszlát!");
                return;
            } else {
                System.out.println("Érvénytelen válasz. Írj 1, 2 vagy 3-at.\n");
            }
        }
    }

    private void ujJatekMenu() {
        String nev = "";
        while (nev.isEmpty()) {
            System.out.print("Add meg a karaktered nevet: ");
            nev = scanner.nextLine().trim();
            if (nev.isEmpty()) {
                System.out.println("A név nem lehet üres.");
            }
        }

        String kaszt = "";
        while (!kaszt.equals("harcos") && !kaszt.equals("magus")) {
            System.out.print("Válassz kasztot (harcos / magus): ");
            kaszt = scanner.nextLine().trim().toLowerCase();
            if (!kaszt.equals("harcos") && !kaszt.equals("mágus")) {
                System.out.println("Érveénytelen kaszt. Írj: harcos vagy mágus");
            }
        }

        gameManager.ujJatek(nev, kaszt);
        System.out.println("\nKarakter létrehozva: " + nev + " (" + kaszt + ")");
        System.out.println("Írj 'help' a parancsokért.\n");

        gameManager.szobaMegjelenites();
        jatekHurok();
    }

    private void jatekHurok() {
        while (true) {
            if (gameManager.isJatekVege()) {
                System.out.println("\nJátek vége. Kösz a játékot!");
                break;
            }

            System.out.print("\n> ");
            String parancs = scanner.nextLine().trim();

            if (parancs.isEmpty()) {
                continue;
            }

            if (parancs.equalsIgnoreCase("exit")) {
                System.out.print("Mentesz kilépés előtt? (i/n): ");
                String v = scanner.nextLine().trim().toLowerCase();
                if (v.equals("i")) {
                    gameManager.ment();
                }
                System.out.println("Viszlát!");
                break;
            }

            gameManager.korokFeldolgozasa(parancs);
        }
    }
}
