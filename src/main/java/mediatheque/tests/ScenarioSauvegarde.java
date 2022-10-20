package mediatheque.tests;

import mediatheque.metier.Actif;
import mediatheque.metier.Entreprise;
import mediatheque.metier.Livre;
import sauvegarde.Recordable;
import sauvegarde.SystemeDeSauvegarde;

import java.time.LocalDate;

public class ScenarioSauvegarde {

    public static void main(String[] args) {

        //créer 5 actifs
        for (int i = 0; i < 5; i++) {
            try {
                Actif actif = new Actif("toto"+i,"titi"+i,LocalDate.parse("1989-01-01").plusYears(i), new Entreprise("EDF"));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //créer 5 livres
        for (int i = 0; i < 5; i++) {
            try {
                Livre livre = new Livre("Titre Livre "+i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //abonner les livres et les actifs
        //lancer la sauvegarde
        try {
            SystemeDeSauvegarde.getInstance().sauvegarder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
