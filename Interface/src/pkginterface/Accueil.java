/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Accueil extends JFrame implements ActionListener{
    
  private final JMenuBar menuBar = new JMenuBar();
  private final JMenu test1 = new JMenu("Fichier");

  private final JMenuItem item1 = new JMenuItem("Nouveau");
  private final JMenuItem item2 = new JMenuItem("Fermer");
    
    public Accueil() {
        
        setTitle("interface git"); //titre de la fenêtre
        setBounds(200, 80, 1200, 700); //position et taille de la fenêtre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //choix du comportement lors de la fermeture de la fenêtre
        
        JButton boutoncharger = new JButton("Charger un projet"); //création du bouton charger
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(boutoncharger); //ajout du bouton à la fenêtre
        boutoncharger.addActionListener(this);
        
        this.test1.add(item1);

    
        //On met nos radios dans un ButtonGroup
        ButtonGroup bg = new ButtonGroup();



        //Ajout d'un séparateur
        this.test1.addSeparator();
        item2.addActionListener((ActionEvent arg0) -> {
            System.exit(0);
        });
        this.test1.add(item2);  


        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        this.menuBar.add(test1);
        this.setJMenuBar(menuBar);
        
}

    @Override
    public void actionPerformed(ActionEvent ae) {
            javax.swing.JOptionPane.showMessageDialog(null,"L'évennement n'a pas encore été implémenté");
    }
    
}
