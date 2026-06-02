package game.model;

public class GameState {
    private Character karakter;
    private String jelenlegiSzobaId;
    private boolean jatekVege;

    public GameState() {}

    public GameState(Character karakter, String jelenlegiSzobaId) {
        this.karakter = karakter;
        this.jelenlegiSzobaId = jelenlegiSzobaId;
        this.jatekVege = false;
    }

    public Character getKarakter() { return karakter; }
    public void setKarakter(Character karakter) { this.karakter = karakter; }
    public String getJelenlegiSzobaId() { return jelenlegiSzobaId; }
    public void setJelenlegiSzobaId(String jelenlegiSzobaId) { this.jelenlegiSzobaId = jelenlegiSzobaId; }
    public boolean isJatekVege() { return jatekVege; }
    public void setJatekVege(boolean jatekVege) { this.jatekVege = jatekVege; }
}
