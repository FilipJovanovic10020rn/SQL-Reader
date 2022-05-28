//package gui;
//
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.Label;
//import java.awt.Toolkit;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.net.URL;
//
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextArea;
//import javax.swing.WindowConstants;
//
//
//
//public class ImportFrame extends JDialog{
//
//    public static ImportFrame instante;
//    JButton dugme1;
//    JButton dugme2;
//    JTextArea unosTeksta;
//    JTextArea unosSlike;
//    JButton primeniSliku;
//    JButton close;
//
//    private ImportFrame(){
//        setLayout(new GridLayout(2, 2));
//
//    }
//
//
//    private void addWindowAdapter() {
//        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
//        this.addWindowListener( new WindowAdapter()
//        {
//            public void windowClosing(WindowEvent e)
//            {
//                //EditFrame frame = EditFrame.getInstance();
//
//                setUnosSlike(" ");
//                setUnosTeksta("Unesi ime autora");
//                instante.setVisible(false);
//
//            }
//        });
//    }
//
//
//
//
//    private void initialiseGUI() {
//        setModal(true);
//        addWindowAdapter();
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
//        setSize(screenWidth / 3, screenHeight / 3);
//        // autor = new JLabel("Unesi ime autora");
//        unosTeksta = new JTextArea("Unesi ime autora");
//        dugme1 = new JButton("Izvrsi promenu");
//        // JLabel slika = new JLabel("");
//        //dugme2 = new JButton("promena");
//        unosSlike = new JTextArea();
//        unosSlike.setEditable(false);
//        primeniSliku = new JButton();
//        close = new JButton("OK");
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(6,1));
//
//        //  add(slika);
//        add(unosSlike);
//        //dugme2.setAction(MainFrame.getInstance().getActionManager().getEditAutorAction());
//        dugme2 = new JButton("promena");
//        // dugme2.setAction(MainFrame.getInstance().getActionManager().getEditAutorAction());
//        add(dugme2);
//        //dugme2.setAction(MainFrame.getInstance().getActionManager().getEditAutorAction());
//        //  add(autor);
//        add(primeniSliku);
//        add(unosTeksta);
//
//        add(dugme1);
//        add(close);
//        primeniSliku.setAction(MainFrame.getInstance().getActionManager().getEditPozadinaAction());
//        dugme1.setAction(MainFrame.getInstance().getActionManager().getEditAutorAction());
//        dugme2.setAction(MainFrame.getInstance().getActionManager().getIzaberiSlikuAkcija());
//        close.setAction(MainFrame.getInstance().getActionManager().getCloseEdit());
//    }
//
//
//    public String getUnosTeksta() {
//        return unosTeksta.getText();
//    }
//
//
//
//    public void setUnosTeksta(String S) {
//        unosTeksta.setText(S);
//    }
//
//    public String getUnosSlike() {
//        return unosSlike.getText();
//    }
//
//
//    public void setUnosSlike(String s) {
//        this.unosSlike.setText(s);
//    }
//
//
//    public static EditFrame getInstance(){
//        if(instante == null){
//            instante = new EditFrame();
//            instante.initialiseGUI();
//        }
//
//        return instante;
//    }
//
//    public Icon loadIcon(String fileName){
//
//        URL imageURL = getClass().getResource(fileName);
//        Icon icon = null;
//
//        if (imageURL != null) {
//            icon = new ImageIcon(imageURL);
//        }
//        else {
//            System.err.println("Resource not found: " + fileName);
//        }
//        return icon;
//    }
//
//    public static EditFrame getInstante() {
//        return instante;
//    }
//
//    public static void setInstante(EditFrame instante) {
//        EditFrame.instante = instante;
//    }
//}
