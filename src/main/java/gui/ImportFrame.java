package gui;

import gui.controler.IzaberiCsvActionListener;
import gui.controler.RunActionListener;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;



// 1. DEO
// TREBA DUGME JEDNO ZA OTVARANJE PROZORA ZA FAJLOVIMA
// TREBA TEXTAREA ZA DA PRIKAZE NAZIV DATOTEKE
// 2. DEO
// TREBA DUGME KOJE CE DA GASI I DA UBACI TE PODATKE



public class ImportFrame extends JDialog{

    public static ImportFrame instante;
    JButton dugme2;
    JTextArea unosSlike;
    JButton primeniSliku;
    JButton close;

    private ImportFrame(){
        setLayout(new GridLayout(4, 1));

    }


    private void addWindowAdapter() {
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //EditFrame frame = EditFrame.getInstance();

                setUnosSlike(" ");
                instante.setVisible(false);

            }
        });
    }




    private void initialiseGUI() {
        setModal(true);
        addWindowAdapter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 3);

        unosSlike = new JTextArea();
        unosSlike.setEditable(false);
        primeniSliku = new JButton("Izvrsi promenu");
        close = new JButton("OK");
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,1));

        add(unosSlike);
        dugme2 = new JButton("promena");
        add(dugme2);
        // ova dva dugmeta spojiti u jedno
        add(primeniSliku);
        add(close);

        //primeniSliku.setAction(MainFrame.getInstance().getActionManager().getEditPozadinaAction());
        dugme2.addActionListener(new IzaberiCsvActionListener());
        //close.setAction(MainFrame.getInstance().getActionManager().getCloseEdit());
    }

    public String getUnosSlike() {
        return unosSlike.getText();
    }


    public void setUnosSlike(String s) {
        this.unosSlike.setText(s);
    }


    public static ImportFrame getInstance(){
        if(instante == null){
            instante = new ImportFrame();
            instante.initialiseGUI();
        }

        return instante;
    }

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

}
