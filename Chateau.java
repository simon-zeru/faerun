package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Classe Chateau
 * Un chateau est composé de guerriers novices
 * Un chateau a des ressources
 * Un chateau a une couleur
 * Un chateau est lié à un plateau
 */
public class Chateau {
    private static final int RESSOURCES_INITIAL = 3;
    private int ressources;
    private static final int RESSOURCE_AJOUTEE_PAR_TOUR = 1;
    private Couleur couleur;
    private LinkedList<Guerrier> guerriersNovices;
    private static final int COUT_BASE = 1;

    private static final int COUT_CHEF_NAIN = 3;
    private static final int COUT_CHEF_ELFE = 4;

    private Plateau plateau;

    /**
     * Constructeur de la classe Chateau
     * @param couleur couleur du chateau
     * @param plateau plateau auquel est lié le chateau
     * initialisation des ressources
     */
    public Chateau(Couleur couleur, Plateau plateau) {
        this.ressources = RESSOURCES_INITIAL;
        this.couleur = couleur;
        this.guerriersNovices = new LinkedList<>();
        this.plateau = plateau;
    }

    /**
     * ajout d'un guerrier novice dans la liste des guerriers novices
     */
    public void ajoutGuerrierNovice(Guerrier guerrier) {
        // tableau ordonné
        guerriersNovices.add(guerrier);
    }

    /**
     * @return guerriers novices du chateau
     */
    public LinkedList<Guerrier> getGuerriersNovices() {
        return guerriersNovices;
    }

    /**
     *  Ajout des guerriers sur le plateau et retrait des ressources en fonction du chateau
     */
    public ArrayList<Guerrier> entrainer() {
        ArrayList<Guerrier> guerriersEntraines = new ArrayList<>();
        // Ajout des guerriers sur le plateau et retrait des ressources en fonction du chateau
        // Jusqu'à ce qu'il n'y ait plus de ressources suffisante ou de guerriers novices, on achète des guerriers
        while (ressources > 0 && !guerriersNovices.isEmpty() && guerriersNovices.getFirst().getCout() <= ressources) {

            ressources -= guerriersNovices.getFirst().getCout(); // Retrait des ressources
            guerriersEntraines.add(guerriersNovices.poll());

        }

        // Si le chateau est bleu, on ajoute les guerriers novices au premier carreau (départ bleu)
        if (this.couleur == Couleur.BLEU) {
            plateau.getCarreaux().get(0).ajoutGuerriersBleus(guerriersEntraines);
        }
        // Sinon, on les ajoute au dernier carreau (départ rouge)
        else {
            plateau.getCarreaux().get(plateau.getCarreaux().size()-1).ajoutGuerriersRouges(guerriersEntraines);
        }
        return guerriersEntraines;
    }

    /**
     * Ressources du chateau incrémente de 1
     */
    public void incrementerRessources() {
        this.ressources += RESSOURCE_AJOUTEE_PAR_TOUR;
    }

    /**
     * @return Couleur du chateau
     */
    public Couleur getCouleur() {
        return couleur;
    }

    public int getRessources() {
        return ressources;
    }
}
