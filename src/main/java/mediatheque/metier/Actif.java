package mediatheque.metier;

import mediatheque.tests.ScenarioSauvegarde;
import sauvegarde.Recordable;
import sauvegarde.SystemeDeSauvegarde;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

public class Actif extends Adherent implements Recordable {

    private Entreprise entreprise;

    public Actif(String nom, String prenom, LocalDate dateNaissance, Entreprise entreprise) throws Exception {
        super(nom, prenom, dateNaissance);
        setEntreprise(entreprise);

        //autoabonnement système de sauvegarde
        SystemeDeSauvegarde.getInstance().addRecordable(this);
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public Location louer(Ressource res) {
            System.out.println("Location plein tarif de " + res + "par" + this);
            return null;
    }

    @Override
    public boolean save() throws Exception {
        //charger le pilote de SGBD en mémoire vive
        Class.forName("org.postgresql.Driver");
        //obtenir connexion a partir de url et param d'authentification
        String urlJDBC = "jdbc:postgresql://localhost:5432/mediatheque";
        String user = "postgres";
        String pwd = "kis";
        Connection connection = DriverManager.getConnection(urlJDBC, user, pwd);
        //création d'un statement
        Statement st = connection.createStatement();
        UUID uuid = UUID.randomUUID();
        //execution d'une requete en lecture
        String requete = "insert into adherent values (\'" + uuid +"\',\'" + getNom() +"\',\'"+ getPrenom() +"\',\'" +getDateNaissance().toString() +"\')";

        //System.out.println(requete);
        st.execute(requete);

        //fermeture connexion
        connection.close();
        return true;
    }
}
