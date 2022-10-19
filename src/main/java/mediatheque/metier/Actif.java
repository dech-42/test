package mediatheque.metier;

import java.time.LocalDate;

public class Actif extends Adherent {

    private Entreprise entreprise;

    public Actif(String nom, String prenom, LocalDate dateNaissance, Entreprise entreprise) {
        super(nom, prenom, dateNaissance);
        setEntreprise(entreprise);
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
