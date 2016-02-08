package git;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;


public class PanelBrowser extends Box {

    private static final Dimension SIZE = new Dimension(200, 300);
    private List<FilePanel> list = new ArrayList<FilePanel>();

    public PanelBrowser(File root) {
        super(BoxLayout.LINE_AXIS);
        setBackground(Color.red);
        FilePanel panel = new FilePanel(this, root);
        list.add(panel);
        this.add(panel);
    }

    private void update(FilePanel fp, File file) {
        int index = list.indexOf(fp);
        int i = list.size() - 1;
        while (i > index) {
            list.remove(i);
            this.remove(i);
            i--;
        }
        final FilePanel panel = new FilePanel(this, file);
        list.add(panel);
        this.add(panel);
        revalidate();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollRectToVisible(panel.getBounds());
            }
        });
    }

    private static class FilePanel extends Box {

        private static FileSystemView fsv = FileSystemView.getFileSystemView();
        private static DateFormat df = DateFormat.getDateTimeInstance(
            DateFormat.SHORT, DateFormat.DEFAULT);
        private PanelBrowser parent;
        private JList list;

        public FilePanel(PanelBrowser parent, File file) {
            super(BoxLayout.PAGE_AXIS);
            this.parent = parent;
            DefaultListModel model = new DefaultListModel();
            if (file.isFile()) {
                JLabel name = new JLabel(file.getName());
                name.setIcon(fsv.getSystemIcon(file));
                this.add(name);
                
                Date d = new Date(file.lastModified());
                JLabel mod = new JLabel("Date: " + df.format(d));
                this.add(mod);
                final String v = String.valueOf(file.length());
                JLabel length = new JLabel("Size: " + v);
                this.add(length);
            }
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    model.addElement(f);
                }
                list = new JList(model);
                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                list.setCellRenderer(new FileRenderer());
                list.addListSelectionListener(new SelectionHandler());
                this.add(new JScrollPane(list) {
                    @Override
                    public int getVerticalScrollBarPolicy() {
                        return JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
                    }
                });
            }
        }

        private static class FileRenderer extends DefaultListCellRenderer {

            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
                File f = (File) value;
                setText(f.getName());
                setIcon(fsv.getSystemIcon(f));
                return label;
            }
        }

        private class SelectionHandler implements ListSelectionListener {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    File f = (File) list.getSelectedValue();
                    parent.update(FilePanel.this, f);
                }
            }
        }

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(SIZE);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(SIZE);
        }

        @Override
        public Dimension getMaximumSize() {
            return new Dimension(SIZE.width, Short.MAX_VALUE);
        }
    }

    private static void display() {
        String path = "C:\\Users\\romain\\Documents\\GitHub\\interface-groupe2\\.git";
        //String path = System.getProperty("user.dir");
        PanelBrowser browser = new PanelBrowser(new File(path));
        JFrame f = new JFrame(path);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(browser) {
            @Override
            public int getVerticalScrollBarPolicy() {
                return JScrollPane.VERTICAL_SCROLLBAR_NEVER;
            }
        });
        f.pack();
        f.setSize(4 * SIZE.width, SIZE.height);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                display();
            }
        });
    }
}