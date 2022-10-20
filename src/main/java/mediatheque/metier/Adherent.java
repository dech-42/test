package mediatheque.metier;

import java.time.LocalDate;

public abstract class Adherent extends Personne {

    private LocalDate dateAdhesion;

    public Adherent(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        //appel du constructeur parent
        //on lui passe les infos reçues. Il sait comment les traiter
        //la commande super doit etre la tte première chose à faire
        super(nom, prenom, dateNaissance);

    }

    public LocalDate getDateAdhesion() {
        return dateAdhesion;
    }

    public void setDateAdhesion(LocalDate dateAdhesion) {
        this.dateAdhesion = dateAdhesion;
    }

    public abstract Location louer(Ressource res);

}
