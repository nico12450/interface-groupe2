/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;
import java.awt.FlowLayout;
import javax.swing.*;
/**
 *
 * @author nico
 */
public class Accueil extends JFrame{
    
    public Accueil() {
        
        setTitle("interface git"); //titre de la fenêtre
        setBounds(200, 80, 1200, 700); //position et taille de la fenêtre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //choix du comportement lors de la fermeture de la fenêtre
        JButton boutoncharger = new JButton("Charger un projet"); //création du bouton charger
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(boutoncharger); //ajout du bouton à la fenêtre
        
}
    
}
