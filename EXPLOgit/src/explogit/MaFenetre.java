package explogit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 *
 * @author romain
 */
public class MaFenetre extends JFrame
{
      
    public  MaFenetre(String nomFenetre)
    {
        super(nomFenetre);
        /*creation des panel*/
        JPanel font = new JPanel();
        JPanel top = new JPanel();
               
        /*creation des boutons*/
        JLabel label = new JLabel("Bienvenue dans le projet Git");
        
        /*creation du menu bar*/
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Fichier"); 
        JMenuItem item1 = new JMenuItem("Ouvrir"); 
        
              
        /*Definir taille fenetre*/
        this.setSize(500, 200);
        
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
                
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*PANEL*/
        top.add(label);
        
        /*menu bar */
        menu1.add(item1);
        menuBar.add(menu1);
        
        this.add(font,BorderLayout.CENTER);
        /*ajout panel top a celui de font en haut*/
        font.add(top,BorderLayout.CENTER);
        
        
        // evenement sur l'item charger le projet
        item1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue==JFileChooser.APPROVE_OPTION)
                {
                   
                    System.out.println(fileChooser.getSelectedFile().getName());
                    System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                    final String path=fileChooser.getSelectedFile().getAbsolutePath();
                    EventQueue.invokeLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                                    
                            PanelBrowser.display(path);
                        }
                    });
                }
               
                else
                {
                    System.out.println("L'ouverture est annulée\n");
                }
            }
        });
        
        /*rendre la fenetre visible et non redimentionnable*/
        this.setJMenuBar(menuBar);
        this.setContentPane(font);
        this.setResizable(false);
    }    
}