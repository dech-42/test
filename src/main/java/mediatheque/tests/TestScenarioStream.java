package mediatheque.tests;

import mediatheque.metier.Adherent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestScenarioStream {

    public static void main(String[] args) throws Exception {

        //récupération des adhérents de la base
        ArrayList<Adherent> annuaire = Adherent.load();
        System.out.println(annuaire.size());

        //afficher le nombre d'adhérent avec un stream
        System.out.println(annuaire.stream().count());

        //afficher le nombre d'adhérent né en 1985
        Predicate<Adherent> predicate = unAdherent -> {
            return unAdherent.getDateNaissance().getYear() == 1989;
        };
        System.out.println(annuaire.stream().filter(predicate).count());

        //meme chose mais avec une lambda
        System.out.println(annuaire.stream()
                .filter(adherent -> adherent.getDateNaissance().getYear() == 1989)
                .count());

        //afficher l'adhérent le plus agé
        Optional<Adherent> adherent = annuaire.stream().max((date1, date2) -> {
            if (date1.getDateNaissance().isBefore(date2.getDateNaissance())) return 1;
            if (date2.getDateNaissance().isBefore(date1.getDateNaissance())) return -1;
            return 0;
        });

        System.out.println(adherent.get().getDateNaissance().toString() + adherent.get());

        //afficher tous les adhérents nés en 1985 en les ordonnants par date de naissance
        //on limit a 15 l'affichage
        annuaire.stream()
                .filter(predicate)
                .sorted((date1, date2) -> {
                    if (date1.getDateNaissance().isBefore(date2.getDateNaissance())) return 1;
                    if (date2.getDateNaissance().isBefore(date1.getDateNaissance())) return -1;
                    return 0;
                })
                .limit(15)
                .forEach(System.out::println)
        ;

        //trouver un adhérent qui est né le 05/01/1937
        System.out.println("user né le " +
                annuaire.stream()
                        .filter(adherent1 -> adherent1.getDateNaissance().isEqual(LocalDate.parse("1937-01-05")))
                        .findFirst());
    }
}
