package jeu;

/**
 * Classe représentant un chef elfe (guerrier)
 * Un Chef Elfe a 2 fois la force d'un elfe, et coûte 2 fois plus cher
 */
public class ChefElfe extends Elfe {

    public ChefElfe(Chateau chateau) {
        super(chateau);
    }

    public int getForce() {
        return super.getForce()*2;
    }


    @Override
    public int getCout() {
        return super.getCout()*2;
    }

    @Override
    public String toString() {
        return "Chef"+super.toString();
    }
}
