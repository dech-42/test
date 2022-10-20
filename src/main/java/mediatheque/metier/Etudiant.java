package mediatheque.metier;

import java.time.LocalDate;

public class Etudiant extends Adherent {

    private Campus campus;

    public Etudiant(String nom, String prenom, LocalDate dateNaissance, Campus campus) throws Exception {
        super(nom, prenom, dateNaissance);
        setCampus(campus);
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getAge() + " " + getCampus();
    }

    @Override
    public Location louer(Ressource res) {
        System.out.println("Location gratuite de " + res + " par " + this);
        return null;
    }

}
