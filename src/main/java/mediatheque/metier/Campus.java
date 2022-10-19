package mediatheque.metier;

public class Campus {
    private String nom;

    public Campus(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return getNom();
    }
}
