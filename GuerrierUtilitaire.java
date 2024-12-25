package jeu;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuerrierUtilitaire {
    private static final Logger LOGGER = Logger.getLogger(GuerrierUtilitaire.class.getName());

    /**
     * Lance un dé à 4 faces
     * @return un entier entre 1 et 4
     */
    public static int de4() {
        return (int) (Math.random() * 4 + 1);
    }

    /**
     * Lance un dé à 4 faces nombreLance fois
     * @param nombreLance nombre de lancers
     * @return la somme des lancers
     */
    public static int de4(int nombreLance) {
        int somme = 0;
        for (int i = 0; i < nombreLance; i++) {
            somme += de4();
        }
        return somme;
    }

    /**
     * Initialise les guerriers du chateau
     * @param chateau chateau pour lequel on initialise les guerriers
     */
    public static void initialisationGuerriers(Chateau chateau) {
        LOGGER.log(Level.INFO, "Initialisation des guerriers "+ chateau.getCouleur());
        Scanner scanner = new Scanner(System.in);
        int choix;
        // Demande si l'utilisateur veut un set de guerriers prédéfini ou non
        do {
            System.out.println("Voulez-vous un set de guerriers prédéfini ? (1: Oui, 2: Non)");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix < 1 || choix > 2) {
                LOGGER.warning("Choix invalide, veuillez choisir un choix valide");
            }
        } while (choix < 1 || choix > 2);

        // Demande le nombre de guerriers à ajouter au chateau
        int nbGuerriers;
        do {
            System.out.println("Nombre de guerriers à ajouter (entre 1 et 20) : ");
            nbGuerriers = scanner.nextInt();
            scanner.nextLine();
            if (nbGuerriers < 1 || nbGuerriers > 20) {
                LOGGER.warning("Choix invalide, veuillez choisir un nombre de guerriers valide");
            }
        } while (nbGuerriers < 1 || nbGuerriers > 20);

        if (choix == 1) {
            LOGGER.info("Initialisation des guerriers prédéfinis");
            for (int i = 0; i < nbGuerriers; i++) {
                int choixGuerrierRandom = de4();
                choixGuerrier(chateau, choixGuerrierRandom);
            }
        } else {
            LOGGER.info("Initialisation des guerriers personnalisés");
            for (int i = 0; i < nbGuerriers; i++) {
                LOGGER.info("Il vous reste " + (nbGuerriers - i) + " guerriers à ajouter");
                do {
                    System.out.println("Quel guerrier voulez-vous ajouter ? (1: Nain, 2: ChefNain, 3:Elfe, 4: ChefElfe)");
                    choix = scanner.nextInt();
                    scanner.nextLine();
                    choixGuerrier(chateau, choix);
                } while (choix < 1 || choix > 4);
            }
        }
        LOGGER.log(Level.INFO, "Fin de l'initialisation des guerriers "+ chateau.getCouleur()+", "+nbGuerriers+" guerriers ajoutés");
    }

    /**
     * Ajoute un guerrier au chateau
     * @param chateau chateau auquel on ajoute le guerrier
     * @param choix {1|2|3|4] du guerrier à ajouter
     */
    public static void choixGuerrier(Chateau chateau, int choix) {
        switch (choix) {
            case 1:
                chateau.ajoutGuerrierNovice(new Nain(chateau));
                LOGGER.info("Nain ajouté au chateau");
                break;
            case 2:
                chateau.ajoutGuerrierNovice(new ChefNain(chateau));
                LOGGER.info("ChefNain ajouté au chateau");
                break;
            case 3:
                chateau.ajoutGuerrierNovice(new Elfe(chateau));
                LOGGER.info("Elfe ajouté au chateau");
                break;
            case 4:
                chateau.ajoutGuerrierNovice(new ChefElfe(chateau));
                LOGGER.info("ChefElfe ajouté au chateau");
                break;
            default:
                LOGGER.warning("Choix invalide, veuillez choisir un guerrier valide");
                break;
        }
    }

    /**
     * Affiche les guerriers novices du chateau
     * @param chateau chateau pour lequel on affiche les guerriers
     */
    public static void afficheGuerriersNovices(Chateau chateau) {
        LOGGER.log(Level.INFO, "Guerriers du chateau "+ chateau.getCouleur() + " : " + chateau.getGuerriersNovices());
    }
    
}
