package mediatheque.tests;

import mediatheque.metier.Carte;
import mediatheque.metier.Personne;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class ScenarioPersonne {

    public static void main(String[] args) {

        try {

            Personne toto = new Personne("DECHAUD", "Nicolas");
            Personne titi = new Personne("TIti", "titi", LocalDate.parse("1989-08-29"));

            toto.setDateNaissance(LocalDate.parse("1989-08-29"));

            System.out.println("Age " + toto.getAge());

            toto.marcher();
            //calcul de nb d'années entre 2 dates
            LocalDate date1 = LocalDate.parse("1918-11-11");
            LocalDate date2 = LocalDate.now();

            //pour demander un appel en static, on va appeler le nom de la classe (ici Personne) et non toto ou titi (même si ca fonctionne aussi)
            int ecartDate = Personne.getAnnees(date1, date2);
            System.out.println(ecartDate + " années");

            //créer une carte pour toto
            Carte carte1 = new Carte(toto);
            //plus besoin de ca car on appel dans le constructeur de carte proprietaire.setCarte(this);
            //toto.setCarte(carte1);
            toto.mettrePoche();
            //afficher mois de naissance du propriétaire de la carte
            System.out.println(carte1.getProprietaire().getDateNaissance().getMonth());

            System.out.println(carte1.getIdentifiant());

            for (int i = 0; i < 5; i++) {
                Carte c = new Carte(toto);
                System.out.println(c.getIdentifiant());
            }

            //saisie de date par utilisateur avec controle
            boolean erreur = false;
            do
                //saisie  date-
                try {
                    System.out.println("Veuillez saisir une date");
                    Scanner scanner = new Scanner(System.in);
                    String saisie = scanner.nextLine();

                    LocalDate dateSaisie = LocalDate.parse(saisie);
                    erreur = false;

                } catch (DateTimeException dte) {
                    System.out.println("Date saisie KO");
                    erreur = true;
                }
            while (erreur);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
