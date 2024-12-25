package jeu;

import java.util.ArrayList;

/**
 * Classe représentant un carreau du plateau de jeu
 * Un carreau peut contenir des guerriers bleus et/ou rouges
 * Le premier carreau est le départ des guerriers bleus
 * Le dernier carreau est le départ des guerriers rouges
 * Les carreaux contenant des guerriers des deux couleurs sont des champs de bataille
 */
public class Carreau implements Comparable<Carreau> {
    private ArrayList<Guerrier> GuerriersBleus;
    private ArrayList<Guerrier> GuerriersRouges;

    /**
     * GuerriersBleus Liste des guerriers bleus sur le carreau
     *                       (peut être vide)
     * GuerriersRouges Liste des guerriers rouges sur le carreau
     *                        (peut être vide)
     */
    public Carreau() {
        GuerriersBleus = new ArrayList<>();
        GuerriersRouges = new ArrayList<>();
    }

    /**
     * @return Liste des guerriers bleus sur le carreau
     */
    public ArrayList<Guerrier> getGuerriersBleus() {
        return GuerriersBleus;
    }

    /**
     * @return Liste des guerriers rouges sur le carreau
     */
    public ArrayList<Guerrier> getGuerriersRouges() {
        return GuerriersRouges;
    }

    /**
     * Ajoute des guerriers bleus sur le carreau
     * @param guerriers Liste des guerriers à ajouter
     */
    public void ajoutGuerriersBleus(ArrayList<Guerrier> guerriers) {
        GuerriersBleus.addAll(guerriers);
    }

    /**
     * Ajoute des guerriers rouges sur le carreau
     * @param guerriers Liste des guerriers à ajouter
     */
    public void ajoutGuerriersRouges(ArrayList<Guerrier> guerriers) {
        GuerriersRouges.addAll(guerriers);
    }

    /**
     * Retire les guerriers bleus du carreau
     * @return Liste des guerriers bleus retirés
     */
    public ArrayList<Guerrier> retirerGuerriersBleus() {
        ArrayList<Guerrier> guerriersBleus = new ArrayList<>(GuerriersBleus);
        GuerriersBleus.clear();
        return guerriersBleus;
    }

    /**
     * Retire les guerriers rouges du carreau
     * @return Liste des guerriers rouges retirés
     */
    public ArrayList<Guerrier> retirerGuerriersRouges() {
        ArrayList<Guerrier> guerriersRouges = new ArrayList<>(GuerriersRouges);
        GuerriersRouges.clear();
        return guerriersRouges;
    }

    /**
     * Supprime un guerrier du carreau
     * @param guerrier Guerrier à supprimer
     */
    public void supprimerGuerrier(Guerrier guerrier) {
        GuerriersBleus.remove(guerrier);
        GuerriersRouges.remove(guerrier);
    }

    public Couleur getCouleur() {
        if (estBleu()) {
            return Couleur.BLEU;
        } else if (estRouge()) {
            return Couleur.ROUGE;
        } else {
            return null;
        }
    }

    /**
     * @return true si le carreau est occupé par des guerriers rouge
     */
    public boolean estRouge() {
        return !GuerriersRouges.isEmpty() && GuerriersBleus.isEmpty();
    }

    /**
     * @return true si le carreau est occupé par des guerriers bleus
     */
    public boolean estBleu() {
        return !GuerriersBleus.isEmpty() && GuerriersRouges.isEmpty();
    }

    /**
     * @return true si le carreau est un champ de bataille
     */
    public boolean estChampDeBataille() {
        return !GuerriersBleus.isEmpty() && !GuerriersRouges.isEmpty();
    }

    /**
     * Lance les combats sur le carreau selon les règles suivantes :
     * - Les guerriers bleus attaquent les guerriers rouges en premier
     *   Chaque guerrier bleu attaque une fois le premier guerrier rouge vivant.
     *   Quand un guerrier rouge meurt,	c’est le suivant, s’il existe, qui	prend les coups.
     * - Quand tous les guerriers bleus ont attaqué, c’est le camp rouge qui attaque les bleus suivant.
     */
    public void lanceCombat() {
        // Attaque des guerriers rouges par les guerriers bleus en file indienne
        int j = 0;
        for (int i = 0; i < GuerriersRouges.size() && j < GuerriersBleus.size(); i++) {
            Guerrier guerrierCibleRouge = GuerriersRouges.get(i);

            while (guerrierCibleRouge.estVivant() && j < GuerriersBleus.size()) {
                GuerriersBleus.get(j).attaquer(guerrierCibleRouge);
                j++;
            }
            if (!guerrierCibleRouge.estVivant()) {
                supprimerGuerrier(guerrierCibleRouge);
                i--;
            }
        }
        // Attaque des guerriers bleus par les guerriers rouges en file indienne
        j = 0;
        for (int i = 0; i < GuerriersBleus.size() && j < GuerriersRouges.size(); i++) {
            Guerrier guerrierCibleBleu = GuerriersBleus.get(i);

            while (guerrierCibleBleu.estVivant() && j < GuerriersRouges.size()) {

                GuerriersRouges.get(j).attaquer(guerrierCibleBleu);
                j++;

            }
            if (!guerrierCibleBleu.estVivant()) {
                supprimerGuerrier(guerrierCibleBleu);
                i--;
            }
        }

        // Affichage du résultat du combat
        if (GuerriersBleus.isEmpty()) {
            System.out.println("Les guerriers bleus ont perdu le combat");
        } else if (GuerriersRouges.isEmpty()) {
            System.out.println("Les guerriers rouges ont perdu le combat");
        }


    }

    @Override
    public String toString() {
        return "Carreau{" +
                "GuerriersBleus=" + GuerriersBleus +
                ", GuerriersRouges=" + GuerriersRouges +
                '}';
    }

    @Override
    public int compareTo(Carreau o) {
        if (this.estBleu() && !o.estBleu()) {
            return 1;
        } else if (this.estRouge() && !o.estRouge()){
            return -1;
        } else {
            return 0;
        }
    }
}
