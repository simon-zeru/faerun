package jeu;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale du jeu représentant le moteur du jeu
 * (itération	comportant	les	 tours	de	jeu	jusqu’à	la victoire d’un des joueurs)
 */
public class Main {
    private Chateau chateauBleu;
    private Chateau chateauRouge;
    private Plateau plateau;
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Constructeur de la classe Moteur
     * plateau plateau de jeu de longueur 10 (par défaut)
     */
    public Main() {
        this.plateau = new Plateau(5);
        this.chateauBleu = new Chateau(Couleur.BLEU, plateau);
        this.chateauRouge = new Chateau(Couleur.ROUGE, plateau);

        // Lancement du jeu
        jouer();
    }

    /**
     * Constructeur de la classe Moteur
     * plateau plateau de jeu de longueur longueurPlateau
     */
    public Main(int longueurPlateau) {
        this.plateau = new Plateau(longueurPlateau);
        this.chateauBleu = new Chateau(Couleur.BLEU, plateau);
        this.chateauRouge = new Chateau(Couleur.ROUGE, plateau);
    }

    /**
     * Méthode principale du jeu
     * Itère les tours de jeu jusqu'à la victoire d'un des joueurs
     */
    public void jouer() {
        LOGGER.info("Début de partie");
        ArrayList<Guerrier> guerriersBleusTrained = new ArrayList<>();
        ArrayList<Guerrier> guerriersRougesTrained = new ArrayList<>();
        // Initialisation des guerriers
        GuerrierUtilitaire.initialisationGuerriers(chateauBleu);
        System.out.println( "Guerriers du chateau bleu après initialisation : " + chateauBleu.getGuerriersNovices());
        GuerrierUtilitaire.initialisationGuerriers(chateauRouge);
        System.out.println( "Guerriers du chateau rouge après initialisation : " + chateauRouge.getGuerriersNovices());

        System.out.println( "Début des tours de jeu");
        // Tours de jeu
        while (!plateau.estPartieTerminee()) {
            System.out.println("Début du tour");
            // Incrémentation des ressources
            System.out.println( "Ressources du chateau bleu après incrémentation : " + chateauBleu.getRessources());
            System.out.println( "Ressources du chateau rouge après incrémentation : " + chateauRouge.getRessources());

            plateau.deplaceGuerriers();
            System.out.println("Guerriers déplacés");
            // Entraînement des guerriers
            guerriersBleusTrained = chateauBleu.entrainer();
            guerriersRougesTrained = chateauRouge.entrainer();
            System.out.println("Guerriers entrainés bleus : " + guerriersBleusTrained);
            System.out.println("Guerriers entrainés rouges : " + guerriersRougesTrained);

            // Affichage du plateau après les déplacements
            System.out.println("Affichage du plateau après les déplacements");
            plateau.afficheCarreaux();

            // Lancement des combats sanglants sur les champs de bataille
            plateau.lanceCombat();
            System.out.println("Combats lancés");
            // Affichage du plateau après les combats
            System.out.println( "Affichage du plateau après les combats");
            plateau.afficheCarreaux();

            // Incrémentation des ressources en fin de tour
            chateauRouge.incrementerRessources();
            chateauBleu.incrementerRessources();
            System.out.println("Fin du tour");
        }
        if (plateau.getGagnant().estRouge()) {
            System.out.println("Le chateau rouge a gagné !");
        } else if (plateau.getGagnant().estBleu()) {
            System.out.println("Le chateau bleu a gagné !");
        } else {
            System.out.println("Egalité !");
        }
    }

    public static void main(String[] args) {

        Main moteur = new Main(); // lance une partie de jeu
    }
}
