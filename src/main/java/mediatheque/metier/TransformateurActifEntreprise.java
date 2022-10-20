package mediatheque.metier;

public class TransformateurActifEntreprise extends Transformateur<Actif,Entreprise>{
    @Override
    public Entreprise transformer(Actif obj) {
        return obj.getEntreprise();
    }
}
