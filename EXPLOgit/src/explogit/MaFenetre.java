package explogit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        JMenuItem item2 = new JMenuItem("extraire zip"); 
                      
        /*Definir taille fenetre*/
        this.setSize(500, 200);
        
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
                       
        //Termine le processus lorsqu'on clique sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*PANEL*/
        top.add(label);
        
        /*menu bar */
        menu1.add(item1);
        menu1.add(item2);
        menuBar.add(menu1);
        
        /*ajout panel top a celui de font en haut*/
        this.add(font,BorderLayout.CENTER);
        font.add(top,BorderLayout.CENTER);
        
        
        // evenement sur l'item charger le projet
        item1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue==JFileChooser.APPROVE_OPTION)
                {
                    final String path=fileChooser.getSelectedFile().getAbsolutePath();
                    EventQueue.invokeLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                                    
                            try 
                            {
                                PanelBrowser.display(path);
                            } 
                            catch (IOException ex) 
                            {
                                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
                else
                {
                    System.out.println("L'ouverture est annulée\n");
                }
            }
        });
        
        item2.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue==JFileChooser.APPROVE_OPTION)
                {
                    final String path=fileChooser.getSelectedFile().getAbsolutePath();
                    
                    EventQueue.invokeLater(new Runnable() 
                    {
                        @Override
                        public void run() 
                        {
                            
                            try 
                            {
                                zip.decompress(path,fileChooser.getSelectedFile().getParent(), false);
                                
                            } 
                            catch (IOException ex) 
                            {
                                Logger.getLogger(MaFenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
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