package lambda;

import com.sun.org.apache.regexp.internal.RE;
import sauvegarde.Recordable;
import sauvegarde.SystemeDeSauvegarde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestLambda {

    public static void main(String[] args) {
        //creation d'un runnable sous forme de classe anonyme
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Il est " + LocalTime.now().format(DateTimeFormatter.ISO_TIME));
            }
        };

        //sous forme de lambda
        //pour le début, on peut supprimer tt ce qui se trouve entre le = et l'accolade {
        Runnable runnable2 = () -> System.out.println("Il est " + LocalTime.now().format(DateTimeFormatter.ISO_TIME));

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        thread2.interrupt();

        //recordable sans lambda
        Recordable recordable = new Recordable() {
            @Override
            public boolean save() throws Exception {
                SystemeDeSauvegarde.getInstance().addRecordable(this);
                System.out.println("sauvegarde effectuée");
                return true;
            }
        };
        //on fait une expression lambda sur notre interface Recordable
        Recordable recordable1 = () -> {
            //ca ne marche pas car on a pas le droit dans le main de faire ca
            //SystemeDeSauvegarde.getInstance().addRecordable((Recordable) this);
            System.out.println("sauvegarde effectuée");
            return true;
        };

        try {
            recordable1.save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //creation d'un gestionnaire de clic sur un bouton
        ActionListener gestionnaire1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("hello world");

            }
        };

        //en lambda
        ActionListener gestionnaire2 = event -> System.out.println("hello world");

        //interface avec un bouton
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Essai gestionnaire de clic");
        fenetre.setSize(200,200);

        //on associe un gestionnaire d'affichage à la fenetre
        fenetre.setLayout(new FlowLayout(FlowLayout.CENTER));

        //un bouton
        JButton bouton = new JButton();
        fenetre.add(bouton);

        //abonnement d'un gestionnaire de clique aux services rendus par le bouton
        bouton.addActionListener(gestionnaire2);


        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setVisible(true);

    }

}
