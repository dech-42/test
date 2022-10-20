package mediatheque.tests;

import mediatheque.metier.Actif;
import mediatheque.metier.Entreprise;
import mediatheque.metier.Retraite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

public class ScenarioConnexion {

    public static void main(String[] args) throws Exception {
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

            Retraite retraite = new Retraite(nom,prenom,date);
            System.out.println(uuid + " " + nom + " " + prenom + " " + date);

        }
        //fermeture connexion
        connection.close();


        //ajout insertion d'un actif en base de données
        Actif actif = new Actif("toto","titi",LocalDate.parse("1989-08-08"),new Entreprise("test"));
        actif.save();
    }
}
