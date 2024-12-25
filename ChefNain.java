package jeu;

/**
 * Classe représentant un chef nain (guerrier)
 * Un Chef Nain a 2 fois la force d'un nain, et coûte 3 fois plus cher
 */
public class ChefNain extends Nain {
    public ChefNain(Chateau chateau) {
        super(chateau);
    }

    public void subirDegats(int degats) {
        super.subirDegats(degats / 2);
    }

    @Override
    public int getCout() {
        return super.getCout()*3;
    }

    @Override
    public String toString() {
        return "Chef"+super.toString();
    }

}
