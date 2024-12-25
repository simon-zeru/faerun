package jeu;

/**
 * Enumération des couleurs possibles pour les chateaux (bleu et rouge)
 */
public enum Couleur {
    BLEU("bleu"),
    ROUGE("rouge");

    private String couleur;


    Couleur(String couleur) {
        this.couleur = couleur;
    }

    public boolean estRouge() {
        return this == ROUGE;
    }

    public boolean estBleu() {
        return this == BLEU;
    }

    @Override
    public String toString() {
        return couleur;
    }
}
