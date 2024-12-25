package jeu;

import java.util.ArrayList;

/**
 * Classe Plateau (plateau de jeu)
 * Un plateau est composé de n carreaux
 * Un carreau peut contenir des guerriers bleus et/ou rouges
 * Un plateau est composé de deux chateaux (un chateau bleu et un chateau rouge)
 */
public class Plateau {
    private ArrayList<Carreau> carreaux;
    /**
     * Constructeur de la classe Plateau
     * @param longueur longueur du plateau
     *                 (nombre de carreaux)
     * initialisation des carreaux
     */
    public Plateau(int longueur) {
        carreaux = new ArrayList<>(longueur);
        for (int i = 0; i < longueur; i++) {
            carreaux.add(new Carreau());
        }
    }


    /**
     * Ajout des guerriers dans les chateaux
     * @param chateau chateau dans lequel on ajoute les guerriers
     * @param guerriers liste des guerriers à ajouter
     */
    public void ajoutGuerriersChateau(Chateau chateau, ArrayList<Guerrier> guerriers) {
        for (Guerrier guerrier : guerriers) {
            chateau.ajoutGuerrierNovice(guerrier);
        }
    }

    /**
     * Déplace les guerriers sur le plateau
     * Solution	1	:	dans	le	sens	du	déplacement
     * – Algorithme	parcourant	le	plateau	de	gauche à droite
     * – Nécessite	de	conserver des unités bleues en attente pour éviter de les déplacer plusieurs fois
     */
    public void deplaceGuerriers() {
        Carreau carreau;
        // Liste des guerriers en attente
        ArrayList<Guerrier> attente = new ArrayList<>();

        // Parcours du plateau de gauche à droite
        for (int i = 0; i < carreaux.size(); i++) {

            carreau = carreaux.get(i); // Récupération du carreau courant

            // Si le carreau est rouge, on déplace les guerriers rouges sur d'un carreau à gauche (cas facile)
            if (carreau.estRouge()) {
                if (i > 0) {
                    carreaux.get(i-1).ajoutGuerriersRouges(carreau.retirerGuerriersRouges());
                }
            } else if (carreau.estBleu() || !attente.isEmpty()) {
                // Sinon si le carreau est bleu ou si des guerriers sont en attente, on déplace les guerriers bleus
                if (i < carreaux.size()) {
                    // Si on est pas sur le dernier carreau,
                    // on retire les guerriers bleus du carreau courant pour les ajouter a la liste d'attente après l'avoir vidée
                    ArrayList<Guerrier> temp = carreau.retirerGuerriersBleus();
                    // on ajoute les guerriers en file d'attente (guerriers du carreau précédent) sur le carreau courant
                    carreau.ajoutGuerriersBleus(attente);
                    // on vide la liste d'attente pour la remplir avec les guerriers bleus retirés du carreau courant
                    attente = temp;

                }
        }

        }
    }
    /**
     * Lance les combats sur les champs de bataille du plateau
     */
    public void lanceCombat() {
        for (Carreau carreau : carreaux) {
            if (carreau.estChampDeBataille()) {
                carreau.lanceCombat();
            }
        }
    }
    /**
     * Vérifie si la partie est terminée
     * @return true si la partie est terminée, false sinon
     */
    public boolean estPartieTerminee() {
        return getDepartBleu().estRouge() || getDepartRouge().estBleu();
    }
    /**
     * Récupère les carreaux du plateau
     * @return les carreaux du plateau sous forme de liste
     */
    public ArrayList<Carreau> getCarreaux() {
        return carreaux;
    }
    /**
     * Récupère le gagnant de la partie
     * @return le gagnant de la partie
     */
    public Couleur getGagnant() {
        if (getDepartBleu().estRouge()) {
            return Couleur.ROUGE;
        } else if (getDepartRouge().estBleu()) {
            return Couleur.BLEU;
        } else {
            return null;
        }
    }
    /**
     * Récupère le premier carreau du plateau
     * @return le premier carreau du plateau
     */
    private Carreau getDepartBleu() {
        return carreaux.get(0);
    }
    /**
     * Récupère le dernier carreau du plateau
     * @return le dernier carreau du plateau
     */
    private Carreau getDepartRouge() {
        return carreaux.get(carreaux.size()-1);
    }
    /**
     * Affiche les carreaux du plateau
     */
    public void afficheCarreaux() {
        for (Carreau carreau : carreaux) {
            System.out.println(carreau);
        }
    }
}
