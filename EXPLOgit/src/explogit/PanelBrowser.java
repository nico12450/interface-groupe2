package explogit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;


public class PanelBrowser extends Box
{
    
    private static final Dimension SIZE = new Dimension(200, 300);
    private List<FilePanel> list = new ArrayList<>();

    public PanelBrowser(File root) throws IOException
    {
        super(BoxLayout.LINE_AXIS);
        setBackground(Color.red);
        FilePanel panel = new FilePanel(this, root);
        list.add(panel);
        this.add(panel);
    }

    /**
     * @param fp
     * @param file
     * @throws IOException
     */
    public void update(FilePanel fp, File file) throws IOException 
    {
        int index = list.indexOf(fp);
        int i = list.size() - 1;
        while (i > index) 
        {
            list.remove(i);
            this.remove(i);
            i--;
        }
        final FilePanel panel = new FilePanel(this, file);
        list.add(panel);
        this.add(panel);
        revalidate();
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                scrollRectToVisible(panel.getBounds());
            }
        });
    }

    private static class FilePanel extends Box 
    {
        private static FileSystemView fsv = FileSystemView.getFileSystemView();
        private static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.DEFAULT);
        private PanelBrowser parent;
        private JList list;

        public FilePanel(PanelBrowser parent, File file) throws IOException 
        {
            super(BoxLayout.PAGE_AXIS);
            this.parent = parent;
            DefaultListModel model = new DefaultListModel();
            if (file.isFile()) 
            {
                JLabel name = new JLabel(file.getName());
                name.setIcon(fsv.getSystemIcon(file));
                this.add(name);
                
                Date d = new Date(file.lastModified());
                JLabel mod = new JLabel("Date: " + df.format(d));
                this.add(mod);
               
                final String v = String.valueOf(file.length());
                JLabel length = new JLabel("Size: " + v);
                this.add(length);
                      
                File dossier=new File("C:\\Users\\romain\\Documents\\GitHub\\interface-groupe2\\.git\\objects");
                if(dossier.compareTo(file.getParentFile().getParentFile())==0)
                {
                    unzip type1 = new  unzip();
                    String type = type1.Decompresser(file.getAbsolutePath());
                    JLabel typeObject = new JLabel("type: " +type);
                    this.add(typeObject);
                    
                }
                else
                {
                    String ext=file.getName().substring(file.getName().lastIndexOf("."));
                    System.out.println(ext);
                    JLabel extension = new JLabel("ext: " +ext);
                    this.add(extension);       
                }
                                
            }
            if (file.isDirectory()) 
            {
                for (File f : file.listFiles()) 
                {
                    model.addElement(f);
                }
                list = new JList(model);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setCellRenderer(new FileRenderer());
                list.addListSelectionListener(new SelectionHandler());
                this.add(new JScrollPane(list) 
                {
                    @Override
                    public int getVerticalScrollBarPolicy() 
                    {
                        return JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
                    }
                });
            }
        }

        public static class FileRenderer extends DefaultListCellRenderer 
        {

            @Override
            public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) 
            {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                File f = (File) value;
                setText(f.getName());
                setIcon(fsv.getSystemIcon(f));
                return label;
            }
        }

        private class SelectionHandler implements ListSelectionListener 
        {
            private Object FilePanel;
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                if (!e.getValueIsAdjusting()) 
                {
                    File f = (File) list.getSelectedValue();
                    try 
                    {
                        parent.update(FilePanel.this, f);
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(PanelBrowser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        @Override
        public Dimension getMinimumSize() 
        {
            return new Dimension(SIZE);
        }

        @Override
        public Dimension getPreferredSize() 
        {
            return new Dimension(SIZE);
        }

        @Override
        public Dimension getMaximumSize() 
        {
            return new Dimension(SIZE.width, Short.MAX_VALUE);
        }
    }

    public static void display(String path) throws IOException 
    {
        PanelBrowser browser = new PanelBrowser(new File(path));
        JFrame f = new JFrame("Explorateur fichier git");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.add(new JScrollPane(browser) 
        {
            @Override
            public int getVerticalScrollBarPolicy() 
            {
                return JScrollPane.VERTICAL_SCROLLBAR_NEVER;
            }
        });
        f.pack();
        f.setSize(4 * SIZE.width, SIZE.height);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}