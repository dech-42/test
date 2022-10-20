package InterfaceFonctionnel;

import mediatheque.metier.Entreprise;
import mediatheque.metier.Livre;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestInterfaceFonctionnelle {

    public static void main(String[] args) {

        //créer une fonction qui a partir d'un code postal renvoie un nom de ville
        Function<String, String> fonction = codePostal -> {
            if (codePostal.equals("42000")) return "SAINT ETIENNE";
            if (codePostal.equals("75000")) return "PARIS";
            else return null;
        };

        //utilisation de la fonction
        String nomVille = fonction.apply("42000");
        //System.out.println(nomVille);

        String[] tableau = {"42000", "43000", "44000", "26000"};
        List<String> liste = Arrays.asList(tableau);

        //définition d'un consommateur de chaine
        //consommation : afficher la chaine en majuscule
        Consumer<String> consumer1 = chaine -> {
            if (chaine != null)
                System.out.println(chaine.toUpperCase());
            else
                System.out.println("nom de ville inconnu");
        };
        //on soumet la liste à la fonction
        //il faut pour cela la transformer en stream
        liste.stream().map(fonction).forEach(consumer1);

        //créer un prédicat qui affirme qu'une date correspond à un lundi
        Predicate<LocalDate> predicate1 = dateQuelconque -> dateQuelconque.getDayOfWeek().equals(DayOfWeek.THURSDAY);

        System.out.println(predicate1.test(LocalDate.now()));

        //création d'un fournisseur de livre rattaché à des entreprises
        Supplier<Livre> supplier = () -> new Livre("Un Livre");

        Livre livre = supplier.get();
        System.out.println(livre);


    }
}
