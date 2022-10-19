package mediatheque.tests;

import mediatheque.metier.*;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class ScenarioAdherent {

    public static void main(String[] args) {
        //création d'un adhérent
        Adherent adherent1 = new Adherent("DECHAUD", "Nicolas", LocalDate.parse("1989-08-29"));

        //lui demander le numéro de sa carte
        if (adherent1.getCarte() != null) {
            System.out.println(adherent1.getCarte().getIdentifiant());
        }
        //lui demander le nom du jour de sa naissance
        System.out.println(adherent1
                .getDateNaissance()
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.FRENCH));
        //lui demander de louer "Harry Potter"
        adherent1.louer(new Ressource("Harry Potter"));

        //création d'un actif
        Actif actif1 = new Actif("DECH", "Nicolas", LocalDate.parse("1989-08-29"),new Entreprise("EDF"));
        Etudiant etudiant1 = new Etudiant("TOTO","TUTU",LocalDate.parse("2010-10-11"), new Campus("Telecom St Etienne"));

        //leur demander de louer une ressource
        actif1.louer(new Ressource("Ressource 1"));
        etudiant1.louer(new Ressource("Disque 1"));

        Carte carte1 = new Carte(actif1);
        etudiant1.setCarte(carte1);
        System.out.println(etudiant1.getCarte().getIdentifiant());
        System.out.println(etudiant1);

        //tableau d'adhérent
        Adherent[] annuaire = new Adherent [10] ;
        annuaire[0] = adherent1;
        annuaire[1] =  actif1;
        annuaire[2] = etudiant1;

        //on demande au tableau de fournir un des éléments
        Adherent annuaireRang2 = annuaire[2];
        annuaireRang2.louer(null);

        if(annuaireRang2.getClass().getSimpleName().equals("Etudiant")){
            Etudiant etudiantRang2 = (Etudiant) annuaireRang2;
            System.out.println(etudiantRang2.getCampus());
        }
        //si on a fait un cast de Etudiant sur annuaire[1] alors on aura une ClassCastException

        //meme chose mais avec un tableau d'objet
        Object[] elements = new Object[10];
        elements[0] = adherent1;
        elements[1] =  actif1.getEntreprise();
        elements[2] = etudiant1;

        //sur elements[1] cela n'aurait pas marché
        if(elements[2] instanceof Personne){
            Personne personne1 = (Personne) elements[2];
            System.out.println(personne1.getNom() +  " " + personne1.getPrenom());
        }

        //annuaireRang2 = etudiant 1 au final
        //tant que l'on ne fait pas un new, on pointe au meme endroit quelque soit l'objet
        System.out.println(annuaireRang2.equals(etudiant1));


        //on a toujours le droit de regarder un objet comme s'il était issu d'une de ses classes parentes
        //ttes ces déclarations sont bonnes
        //par contre si c'est un objet, il faudra caster pour utiliser la fonction louer par exemple
        Personne obj1 = new  Actif("test2","test2",LocalDate.parse("1997-02-02"), new Entreprise("EDF"));
        Object obj2 = new  Actif("test2","test2",LocalDate.parse("1997-02-02"), new Entreprise("EDF"));
        Actif obj3 = new  Actif("test2","test2",LocalDate.parse("1997-02-02"), new Entreprise("EDF"));

    }
}
