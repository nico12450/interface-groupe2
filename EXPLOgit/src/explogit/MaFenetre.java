package explogit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;


public class MaFenetre extends JFrame 
{
    public MaFenetre(String nomFenetre)
    {
    
        super(nomFenetre);
        /*creation des panel*/
        JPanel font = new JPanel();
        JPanel top = new JPanel();
               
        /*creation des boutons*/
        JButton bouton1 = new JButton("Charger le projet:");
        
        /*creation du menu bar*/
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Fichier"); 
        JMenuItem item1 = new JMenuItem("Ouvrir"); 
        
              
        /*Definir taille fenetre*/
        this.setSize(500, 500);
        
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
                
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*PANEL*/
        top.add(bouton1);
        
        /*menu bar */
        menu1.add(item1);
        menuBar.add(menu1);
        
        this.add(font,BorderLayout.CENTER);
        /*ajout panel top a celui de font en haut*/
        font.add(top,BorderLayout.CENTER);
        
        /*evenement bouton et ouvrir*/
        bouton1.addActionListener((ActionEvent e) -> 
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();
        });
       
        item1.addActionListener((ActionEvent e) -> 
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();
        });
        
                
        /*rendre la fenetre visible et non redimentionnable*/
        this.setJMenuBar(menuBar);
        this.setContentPane(font);
        this.setResizable(false);
        //this.setAlwaysOnTop(true);
    }
    
        
}
