package jeu;

public class TestGuerrier {
    public static void main(String[] args) {

        Plateau plateau = new Plateau(10);
        Chateau chateauBleu = new Chateau(Couleur.BLEU, plateau);
        Chateau chateauRouge = new Chateau(Couleur.ROUGE, plateau);


        chateauBleu.ajoutGuerrierNovice(new ChefNain(chateauBleu));
        chateauRouge.ajoutGuerrierNovice(new ChefElfe(chateauRouge));

        chateauRouge.entrainer();

        plateau.afficheCarreaux();

        plateau.deplaceGuerriers();

        plateau.afficheCarreaux();


    }
}
