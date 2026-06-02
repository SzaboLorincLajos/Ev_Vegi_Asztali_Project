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
        System.out.println("=== Szoveges Kalandjatok ===");
        System.out.println();

        foMenu();
    }

    private void foMenu() {
        while (true) {
            System.out.println("1. Uj jatek");
            System.out.println("2. Betoltes");
            System.out.println("3. Kilepes");
            System.out.print("> ");

            String valasz = scanner.nextLine().trim();

            if (valasz.equals("1")) {
                ujJatekMenu();
                break;
            } else if (valasz.equals("2")) {
                if (!gameManager.vanMentes()) {
                    System.out.println("Nincs mentett jatek.\n");
                } else {
                    boolean ok = gameManager.betoltJatek();
                    if (ok) {
                        System.out.println("Jatek betoltve.");
                        jatekHurok();
                    } else {
                        System.out.println("Hiba a betoltesnel.\n");
                    }
                    break;
                }
            } else if (valasz.equals("3")) {
                System.out.println("Viszlat!");
                return;
            } else {
                System.out.println("Ervenytelen valasz. Irj 1, 2 vagy 3-at.\n");
            }
        }
    }

    private void ujJatekMenu() {
        String nev = "";
        while (nev.isEmpty()) {
            System.out.print("Add meg a karaktered nevet: ");
            nev = scanner.nextLine().trim();
            if (nev.isEmpty()) {
                System.out.println("A nev nem lehet ures.");
            }
        }

        String kaszt = "";
        while (!kaszt.equals("harcos") && !kaszt.equals("magus")) {
            System.out.print("Valassz kasztot (harcos / magus): ");
            kaszt = scanner.nextLine().trim().toLowerCase();
            if (!kaszt.equals("harcos") && !kaszt.equals("magus")) {
                System.out.println("Ervenytelen kaszt. Irj: harcos vagy magus");
            }
        }

        gameManager.ujJatek(nev, kaszt);
        System.out.println("\nKarakter letrehozva: " + nev + " (" + kaszt + ")");
        System.out.println("Irj 'help' a parancsokert.\n");

        gameManager.szobaMegjelenites();
        jatekHurok();
    }

    private void jatekHurok() {
        while (true) {
            if (gameManager.isJatekVege()) {
                System.out.println("\nJatek vege. Kosz a jatekot!");
                break;
            }

            System.out.print("\n> ");
            String parancs = scanner.nextLine().trim();

            if (parancs.isEmpty()) {
                continue;
            }

            if (parancs.equalsIgnoreCase("exit")) {
                System.out.print("Mentesz kilepes elott? (i/n): ");
                String v = scanner.nextLine().trim().toLowerCase();
                if (v.equals("i")) {
                    gameManager.ment();
                }
                System.out.println("Viszlat!");
                break;
            }

            gameManager.korokFeldolgozasa(parancs);
        }
    }
}
