package game.model;

public class Character {
    private String name;
    private String kaszt;
    private int maxEletero;
    private int eletero;
    private int maxMana;
    private int mana;
    private int sebzes;
    private int gyogyital;

    public Character(String name, String kaszt) {
        this.name = name;
        this.kaszt = kaszt;

        if (kaszt.equals("harcos")) {
            this.maxEletero = 120;
            this.maxMana = 25;
            this.sebzes = 20;
        } else {
            this.maxEletero = 80;
            this.maxMana = 100;
            this.sebzes = 12;
        }

        this.eletero = this.maxEletero;
        this.mana = this.maxMana;
        this.gyogyital = 3;
    }

    public Character() {}

    public boolean elHarcban() {
        return this.eletero > 0;
    }

    public void tamad(Enemy ellenfel) {
        ellenfel.sebzodes(this.sebzes);
    }

    public boolean varazslat(Enemy ellenfel) {
        if (this.mana < 20) {
            return false;
        }
        this.mana -= 20;
        ellenfel.sebzodes(this.sebzes * 2);
        return true;
    }

    public boolean gyogyit() {
        if (this.gyogyital <= 0) {
            return false;
        }
        this.gyogyital--;
        int gyogyulas = 30;
        this.eletero = Math.min(this.eletero + gyogyulas, this.maxEletero);
        return true;
    }

    public void sebzodes(int mennyiseg) {
        this.eletero -= mennyiseg;
        if (this.eletero < 0) this.eletero = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getKaszt() { return kaszt; }
    public void setKaszt(String kaszt) { this.kaszt = kaszt; }
    public int getEletero() { return eletero; }
    public void setEletero(int eletero) { this.eletero = eletero; }
    public int getMaxEletero() { return maxEletero; }
    public void setMaxEletero(int maxEletero) { this.maxEletero = maxEletero; }
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public int getMaxMana() { return maxMana; }
    public void setMaxMana(int maxMana) { this.maxMana = maxMana; }
    public int getSebzes() { return sebzes; }
    public void setSebzes(int sebzes) { this.sebzes = sebzes; }
    public int getGyogyital() { return gyogyital; }
    public void setGyogyital(int gyogyital) { this.gyogyital = gyogyital; }
}
