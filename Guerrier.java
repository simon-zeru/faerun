package jeu;

/**
 * Classe abstraite représentant un guerrier
 * Un guerrier possède une force et des points de vie
 * Un guerrier peut attaquer un autre guerrier
 * Un guerrier peut subir des dégâts
 * Un guerrier peut être entraîné
 */
public abstract class Guerrier implements Comparable<Guerrier> {
    private int force;
    private int pointsDeVie;
    private static final int FORCE_BASE = 10;
    private static final int PV_MAX_BASE = 100;
    private static final int RESSOURCE_BASE = 1;

    private static final int COUT_BASE = 1;
    private Chateau chateau;

    public Guerrier(Chateau chateau) {
        setForce(FORCE_BASE);
        setPointsDeVie(PV_MAX_BASE);
        setChateau(chateau);
    }
    public int getForce() {
        return force;
    }

    public int getCout() {
        return COUT_BASE;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    private void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public boolean estVivant() {
        return pointsDeVie > 0;
    }

    public void attaquer(Guerrier guerrier) {
        guerrier.subirDegats(this.getForce());
    }

    public void subirDegats(int degats) {
        this.setPointsDeVie(this.getPointsDeVie() - degats);
    }

    public int getRessourcesPourEntrainement() {
        return RESSOURCE_BASE;
    }

    public Couleur getCouleur() {
        return chateau.getCouleur();
    }

    public void setChateau(Chateau chateau) {
        this.chateau = chateau;
    }

    public boolean estBleu() {
        // return chateau.estBleu();
        return chateau.getCouleur().estBleu();
    }

    public boolean estRouge() {
        return chateau.getCouleur().estRouge();
    }

    @Override
    public String toString() {
        return "[force=" + this.getForce() + ", pointsDeVie=" + this.getPointsDeVie() + "]";
    }

    @Override
    public int compareTo(Guerrier guerrier) {
        if (this.getCout() > guerrier.getCout()) {
            return 1;
        } else if (this.getCout() < guerrier.getCout()) {
            return -1;
        } else {
            return 0;
        }
    }
}
