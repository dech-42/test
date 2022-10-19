package mediatheque.tests;

import java.time.LocalTime;
import java.time.Year;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TestSynthaxe {

    public static void saluer() {
        System.out.println("bonjour Mr");
        System.out.println("il est " + LocalTime.now());
    }

    public static double calculerPerimetreCercle(double rayon) {
        rayon = Math.PI * (rayon * 2);
        return rayon;
    }

    /**
     * cette méthode affiche une salutation personnalisée
     * @param genre : valeur 0 ou 1
     * @param nom : nom de famille de la personne saluée
     */
    public static void saluer(boolean genre, String nom) {
        String type;
        if (genre == TRUE) {
            type = "Mr";
        } else {
            type = "Mme";
        }
        System.out.println("Bonjour " + type + " " + nom);
    }

    public static void main(String[] args) {

        //déclaration variable
        int compteur = 20;

        System.out.println("Le compteur vaut: " + compteur);

        //déclaration et valorisation d'une constante
        //on ne pourra jamais modifier cette valeur
        final boolean SUCCES = true;

        if (compteur >= 100 && SUCCES) {
            System.out.println("Le compteur à une valeur importante");
        } else {
            System.out.println("Le compteur à une valeur faible");
        }

        //traitement itératif
        //on connait le nombre d'itération
        for (int i = 1; i < 10; i++) {
            System.out.println("7 * " + i + " = " + (i * 7));
        }

        //on ne connait pas le nb d'itération
        //afficher les 4 premières années bissextiles après 1857
        Year annee = Year.of(1857);
        int nbAnneeBissextilesTrouvees = 0;

        while (nbAnneeBissextilesTrouvees < 4) {
            if (annee.isLeap()) {
                System.out.println("annee " + annee);
                nbAnneeBissextilesTrouvees++;
            }
            annee = annee.plusYears(1);
        }
        System.out.println("test annee " + annee);

        //on ne connait pas le nombre d'itération
        double valeur = 1234567.0;
        do {
            System.out.println(valeur);
            valeur = valeur / Math.PI;
        } while (valeur >= 3);

        //afficher le nombre de jours d'un mois précis
        int moisCourant = 10;
        switch (moisCourant) {
            case 1:
                System.out.println("31 jours");
                break;
            case 4:
                System.out.println("30 jours");
                break;
            case 10:
                System.out.println("31 jours");
                break;
            default:
                System.out.println("Nombre de jours inconnu");
        }

        //Tableaux
        String joursDeLaSemaine[] = {"Lundi", "Mardi", "Mercredi"};
        System.out.println(joursDeLaSemaine[1]);

        String codePostaux[] = new String[40000];
        for (int i = 0; i < 40000; i++) {
            //codePostaux[i] = i+10000+"";
            codePostaux[i] = String.valueOf(i + 10000);
            if (i % 1000 == 0) System.out.println(codePostaux[i]);
        }

        //tableau a double entrée
        String[][] annuaire = new String[21][4];
        annuaire[1][3] = "2011-01-02";
        annuaire[20][0] = "N21";
        System.out.println(annuaire[1][3] + " " + annuaire[1][2]);

        //affichage de tous les jours de la semaine
        for (String unjour : joursDeLaSemaine) {
            System.out.println(unjour);
        }

        //saluer
        saluer();

        //calcul périmètre
        double perimetre = calculerPerimetreCercle(12);
        System.out.println(perimetre);

        saluer(TRUE, "Dechaud");
    }
}
