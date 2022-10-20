package mediatheque.tests;

import mediatheque.metier.*;
import sauvegarde.SystemeDeSauvegarde;
import utilitaire.Toolbox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class ScenarioAdherent {

    public static void main(String[] args) {
        try {
            //création d'un adhérent
            Adherent adherent1 = new Etudiant("DECHAUD", "Nicolas", LocalDate.parse("1989-08-29"),new Campus("STE"));

            //lui demander le numéro de sa carte
            if (adherent1.getCarte() != null) {
                System.out.println(adherent1.getCarte().getIdentifiant());
            }

            //on supprime la date de naissance de adherent1
//            try {
//                adherent1.setDateNaissance(null);
//                //lui demander le nom du jour de sa naissance
//                System.out.println(adherent1
//                        .getDateNaissance()
//                        .getDayOfWeek()
//                        .getDisplayName(TextStyle.FULL, Locale.FRENCH));
//            } catch (NullPointerException npe) {
//                System.out.println("L'adherent n'a pas de date de naissance définie");
//            }

            //lui demander de louer "Harry Potter"
            adherent1.louer(new Ressource("Harry Potter"));

            //création d'un actif
            Actif actif1 = new Actif("DECH", "Nicolas", LocalDate.parse("1989-08-29"), new Entreprise("EDF"));
            Etudiant etudiant1 = new Etudiant("TOTO", "TUTU", LocalDate.parse("2010-10-11"), new Campus("Telecom St Etienne"));

            //leur demander de louer une ressource
            actif1.louer(new Ressource("Ressource 1"));
            etudiant1.louer(new Ressource("Disque 1"));

            Carte carte1 = new Carte(actif1);
            etudiant1.setCarte(carte1);
            System.out.println(etudiant1.getCarte().getIdentifiant());
            System.out.println(etudiant1);

            //tableau d'adhérent
            Adherent[] annuaire = new Adherent[10];
            annuaire[0] = adherent1;
            annuaire[1] = actif1;
            annuaire[2] = etudiant1;

            //on demande au tableau de fournir un des éléments
            Adherent annuaireRang2 = annuaire[2];
            annuaireRang2.louer(null);

            if (annuaireRang2.getClass().getSimpleName().equals("Etudiant")) {
                Etudiant etudiantRang2 = (Etudiant) annuaireRang2;
                System.out.println(etudiantRang2.getCampus());
            }
            //si on a fait un cast de Etudiant sur annuaire[1] alors on aura une ClassCastException

            //meme chose mais avec un tableau d'objet
            Object[] elements = new Object[10];
            elements[0] = adherent1;
            elements[1] = actif1.getEntreprise();
            elements[2] = etudiant1;

            //sur elements[1] cela n'aurait pas marché
            if (elements[2] instanceof Personne) {
                Personne personne1 = (Personne) elements[2];
                System.out.println(personne1.getNom() + " " + personne1.getPrenom());
            }

            //annuaireRang2 = etudiant 1 au final
            //tant que l'on ne fait pas un new, on pointe au meme endroit quelque soit l'objet
            System.out.println(annuaireRang2.equals(etudiant1));


            //on a toujours le droit de regarder un objet comme s'il était issu d'une de ses classes parentes
            //ttes ces déclarations sont bonnes
            //par contre si c'est un objet, il faudra caster pour utiliser la fonction louer par exemple
            Personne obj1 = new Actif("test2", "test2", LocalDate.parse("1997-02-02"), new Entreprise("EDF"));
            Object obj2 = new Actif("test2", "test2", LocalDate.parse("1997-02-02"), new Entreprise("EDF"));
            Actif obj3 = new Actif("test2", "test2", LocalDate.parse("1997-02-02"), new Entreprise("EDF"));

            //généricité : transformer un actif en entreprise via un transformateur
            TransformateurActifEntreprise transformateurActifEntreprise = new TransformateurActifEntreprise();
            Entreprise entreprise = transformateurActifEntreprise.transformer(obj3);

            System.out.println(entreprise.getRaisonSociale());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //récupération d'un adhérent aléatoire
        try {
            Adherent adherentAleatoire = Adherent.getAdherentAleatoire();
            System.out.println("Adhérent aléatoire " + adherentAleatoire.toString());

            //la sauvegarde fonctionne car dans la génération de l'adhérent aléatoire, on a déclaré un actif qui
            //lui est sauvegardable
            //SystemeDeSauvegarde.getInstance().sauvegarder();

            //créer une classe anonyme qui implémente l'interface runnable
            //objectif : enregistrer 10 000 adhérents
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        Actif actif = null;
                        try {
                            actif = (Actif) Adherent.getAdherentAleatoire();
                            //actif.save();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            };

            Thread thread1 = new Thread(runnable1);
            thread1.start();

            //création d'un type d'adhérent spécifique pour noel 2022 : promo 1/4 du tarif
            //au final meme sur un abstract on peut faire un traitement spec
            Adherent adherentNoel2022 = new Adherent("noel","noel1",LocalDate.parse("1989-01-01")) {
                @Override
                public Location louer(Ressource res) {
                    System.out.println("Quart du tarif pour " + this);

                    return null;
                }
            };

            adherentNoel2022.louer(null);
            System.out.println();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
