package jeu;

/**
 * Classe représentant un nain (guerrier)
 * Un nain est un guerrier qui subit la moitié des dégâts qu'il reçoit
 */
public class Nain extends Guerrier {
    public Nain(Chateau chateau) {
        super(chateau);
    }
    public void subirDegats(int degats) {
        super.subirDegats(degats / 2);
    }

    @Override
    public int getCout() {
        return super.getCout();
    }

    @Override
    public String toString() {
        return "Nain"+super.toString();
    }
}
