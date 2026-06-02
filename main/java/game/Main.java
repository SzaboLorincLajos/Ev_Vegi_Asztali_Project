package game;

// TODO: A 2.0-s verzióban bevezetni a pénz (arany) és bolt rendszert
// TODO: Átültetni a projektet konzolos felületről JavaFX ablakba
// TODO: Bővíteni a SaveManager-t több mentési slot támogatására

import game.ui.GameUI;

public class Main {
    public static void main(String[] args) {
        GameUI ui = new GameUI();
        ui.start();
    }
}
