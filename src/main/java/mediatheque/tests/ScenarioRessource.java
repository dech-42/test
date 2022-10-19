package mediatheque.tests;

import mediatheque.metier.Ressource;

public class ScenarioRessource {

    public static void main(String[] args) {
        Ressource ressource = new Ressource("test");

        System.out.println(ressource.getTitre());
    }
}
