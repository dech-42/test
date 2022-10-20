package mediatheque.metier;

import sauvegarde.Recordable;
import sauvegarde.SystemeDeSauvegarde;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Livre extends Ressource implements Recordable {

    public Livre(String titre) {
        super(titre);
        //autoabonnement système de sauvegarde
        SystemeDeSauvegarde.getInstance().addRecordable(this);
    }

    @Override
    public boolean save() throws Exception {
        System.out.println("Sauvegarde du livre " + this + " dans livre.csv");

        //définir le fichier cible
        File fichier =  new File("C://Users//ndechaud//Desktop//livres.csv");

        //création d'un outil d'écriture
        FileWriter writer = new FileWriter(fichier,true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        //ecrire la chaine à placer dans le fichier
        String chaine = getTitre();
        bufferedWriter.write(chaine);
        bufferedWriter.newLine();

        //fermer la connexion
        bufferedWriter.close();

        return true;
    }
}
