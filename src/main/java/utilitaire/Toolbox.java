package utilitaire;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Toolbox {

    public static LocalDate getRandomLocalDate(int anneeBasse, int anneeHaute) throws Exception {

        int jour = getRandomValue(1, 31);
        int mois = getRandomValue(1, 12);
        int annee = getRandomValue(anneeBasse, anneeHaute);

        LocalDate dateAleatoire;
        try {
            dateAleatoire = LocalDate.of(annee, mois, jour);
        } catch (DateTimeException dte) {
            dateAleatoire = getRandomLocalDate(anneeBasse, anneeHaute);
        }
        return dateAleatoire;
    }

    public static int getRandomValue(int limiteBasse, int limiteHaute) throws Exception {

        //permutation si limite basse est en fait limite haute
        if (limiteBasse > limiteHaute) {
            int tampon = limiteBasse;
            limiteBasse = limiteHaute;
            limiteHaute = tampon;
        }

        //on évalue l'écart entre les 2 limites
        int nbValeursPossibles = limiteHaute - limiteBasse + 1;

        //on fait un tirage aléatoire entre 0 et nbValeursPossibles exclue
        int tirageAleatoire = (int) (Math.random() * nbValeursPossibles);

        return limiteBasse + tirageAleatoire;
    }
}
