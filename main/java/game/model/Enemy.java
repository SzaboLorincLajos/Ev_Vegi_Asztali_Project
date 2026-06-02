package game.model;

import java.util.Random;

public class Enemy {
    private String nev;
    private int eletero;
    private int sebzes;

    private static final String[] NEVEK = {"Goblin", "Troll", "Csontváz", "Sárkány", "Pók", "Veszett farkas", "Zombi", "Démon", "Óriás", "Boszorkány", "Vampír", "Kísértet", "Gonosz varázsló", "Sötét lovag", "Kobold", "Óriás kígyó", "Lángoló szellem", "Fagyos óriás", "Mocsári szörny", "Vérfarkas"};

    public Enemy() {
        Random rand = new Random();
        this.nev = NEVEK[rand.nextInt(NEVEK.length)];
        this.eletero = 30 + rand.nextInt(41);
        this.sebzes = 8 + rand.nextInt(10);
    }

    public boolean elHarcban() {
        return this.eletero > 0;
    }

    public void sebzodes(int mennyiseg) {
        this.eletero -= mennyiseg;
        if (this.eletero < 0) this.eletero = 0;
    }

    public int tamad() {
        return this.sebzes;
    }

    public String getNev() { return nev; }
    public int getEletero() { return eletero; }
    public int getSebzes() { return sebzes; }
}
