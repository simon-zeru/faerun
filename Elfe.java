package jeu;

/**
 * Classe représentant un elfe (guerrier)
 * Un elfe est un guerrier qui a une force doublée
 */
public class Elfe extends Guerrier {

    private static final int COUT_ELFE = 2;
    public Elfe(Chateau chateau) {
        super(chateau);
    }

    public int getForce() {
        return super.getForce()*2;
    }

    @Override
    public int getCout() {
        return COUT_ELFE;
    }

    @Override
    public String toString() {
        return "Elfe"+super.toString();
    }
}
