package mediatheque.metier;

import java.time.LocalDate;

public class Retraite extends Adherent{

    public Retraite(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        super(nom, prenom, dateNaissance);
    }

    @Override
    public Location louer(Ressource res) {
        System.out.println("Location demi tarif de" + res + "par " + this);
        return null;
    }
}
