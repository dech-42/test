package sauvegarde;

import java.util.ArrayList;

public class SystemeDeSauvegarde {

    private ArrayList<Recordable> liste;
    private static SystemeDeSauvegarde instance;

    //seule la classe peut maitriser la création d'une instance
    private SystemeDeSauvegarde() {
        liste = new ArrayList<>();
    }

    public boolean sauvegarder() throws Exception {
        //on parcourt la liste des éléments enregistrables
        //on invoque la méthode "save()" sur chacun d'eux
        for (Recordable rec : liste) {
            rec.save();
        }
        return true;
    }

    public boolean addRecordable(Recordable recordable) {
        return liste.add(recordable);
    }

    public static SystemeDeSauvegarde getInstance(){
        //on créé le système de sauvegarde s'il n'a pas déja été créé
        //sinon on renvoie l'instance créée précédemment
        //singleton car unique instance de classe possible
        if(instance == null){
            instance = new SystemeDeSauvegarde();
        }
        return instance;
    }
}
