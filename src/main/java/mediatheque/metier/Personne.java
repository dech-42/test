package mediatheque.metier;

import java.time.LocalDate;
import java.time.Period;

public class Personne {

    //Propriétés
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Carte carte;

    //constructeurs
    public Personne(String nom, String prenom) throws Exception {
        setNom(nom);
        setPrenom(prenom);
        setCarte(new Carte(this));

    }
    public Personne(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        this(nom,prenom);
        setDateNaissance(dateNaissance);
    }

    //Méthodes
    public boolean marcher() {
        System.out.println(getPrenom() + " " + getNom() + " marche.");
        return true;
    }

    public String getNom() {
        return nom.toUpperCase();
    }

    public void setNom(String propositionNom) throws Exception {
        if (propositionNom.length() >= 3) {
            nom = propositionNom;
        }
        else{
            throw new Exception("Le nom " + propositionNom + " est invalide");
        }
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public static int getAnnees(LocalDate date1, LocalDate date2){
        return Period.between(date1,date2).getYears();
    }

    public int getAge(){
        return getAnnees(getDateNaissance(),LocalDate.now());
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void mettrePoche(){
        System.out.println("Carte mise dans poche de " + getPrenom() + " " + getNom());
    }

    @Override
    public String toString() {
        return getPrenom() + " " + getNom();
    }
}
