package mediatheque.metier;

public class Carte {

    private Personne proprietaire;
    private String identifiant;

    private static int compteur;

    public Carte(Personne proprietaire) {
        setProprietaire(proprietaire);
        //permet d'associer le propriétaire à la carte automatiquement
        //proprietaire.setCarte(this);
        //ou
        getProprietaire().setCarte(this);

        //autogénération id de la carte
        compteur++;
        setIdentifiant("CARTE" + compteur);
    }

    public Carte(Personne proprietaire, String identifiant) {
        setProprietaire(proprietaire);
        setIdentifiant(identifiant);
    }

    public Personne getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Personne proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    private void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

}
