package mediatheque.metier;

import utilitaire.Toolbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

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

    public static Adherent getAdherentAleatoire() throws Exception {

        LocalDate dateAleatoire = Toolbox.getRandomLocalDate(1937, 2022);
        String suffixe = dateAleatoire.toString().replace("-", "");
        Adherent adherentAleatoire = new Actif("ACT" + suffixe, "Prenom" + suffixe, dateAleatoire,
                new Entreprise("ent1")
        );
        return adherentAleatoire;
    }

    public static ArrayList<Adherent> load() throws Exception {

        ArrayList<Adherent> annuaire = new ArrayList<>();

        //charger le pilote de SGBD en mémoire vive
        Class.forName("org.postgresql.Driver");
        //obtenir connexion a partir de url et param d'authentification
        String urlJDBC = "jdbc:postgresql://localhost:5432/mediatheque";
        String user = "postgres";
        String pwd = "kis";
        Connection connection = DriverManager.getConnection(urlJDBC, user, pwd);
        //création d'un statement
        Statement st = connection.createStatement();
        //execution d'une requete en lecture
        String requete = "select * from adherent";

        ResultSet jeu = st.executeQuery(requete);

        //traitement des données
        while (jeu.next()) {
            UUID uuid = UUID.fromString(jeu.getString("id"));
            String nom = jeu.getString("nom");
            String prenom = jeu.getString("prenom");
            LocalDate date = jeu.getDate("date_naissance").toLocalDate();

            Retraite retraite = new Retraite(nom, prenom, date);

            annuaire.add(retraite);
            //System.out.println(uuid + " " + nom + " " + prenom + " " + date);

        }
        //fermeture connexion
        connection.close();
        return annuaire;
    }
}
