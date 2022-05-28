package gui;

import app.AppCore;
import gui.controler.ExportActionListener;
import gui.controler.ImportActionListener;
import gui.controler.RunActionListener;
import gui.controler.UlepsajActionListener;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import tree.implementation.SelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JScrollPane jsp2;
    private JTree jTree;
    private JPanel left;
    private JPanel right;
    private JTextPane textBox;
    private JButton importButton;
    private JButton exportButton;
    private JButton ulepsajButton;
    private JButton runButton;
    private JPanel unutarDesnog;
    private JPanel unutarDesnogDesnog;


    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {
        importButton = new JButton("import");
        exportButton = new JButton("export");
        ulepsajButton = new JButton("ulepsaj");
        runButton = new JButton("run");

        textBox = new JTextPane();
        textBox.setVisible(true);
        textBox.setPreferredSize(new Dimension(500,100));


        importButton.addActionListener(new ImportActionListener());//uzimamo input i parsiramo ga
        exportButton.addActionListener(new ExportActionListener());//uzimamo input i parsiramo ga
        ulepsajButton.addActionListener(new UlepsajActionListener(textBox));//uzimamo input i parsiramo ga
        runButton.addActionListener(new RunActionListener(textBox));//uzimamo input i parsiramo ga







        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



//        right.add(importButton,BorderLayout.NORTH);
//        right.add(exportButton,BorderLayout.NORTH);
//        right.add(ulepsajButton,BorderLayout.NORTH);
//        right.add(runButton,BorderLayout.NORTH);
//        right.add(textBox, BorderLayout.NORTH);

        unutarDesnog = new JPanel(new FlowLayout());
        unutarDesnog.add(importButton);
        unutarDesnog.add(exportButton);
        unutarDesnog.add(ulepsajButton);
        unutarDesnog.add(runButton);


        unutarDesnogDesnog = new JPanel(new BorderLayout());
        unutarDesnogDesnog.add(unutarDesnog,BorderLayout.NORTH);
        unutarDesnogDesnog.add(textBox,BorderLayout.SOUTH);



        right = new JPanel(new BorderLayout());
        right.add(unutarDesnogDesnog, BorderLayout.NORTH);

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        jTable.setFillsViewportHeight(true);

        right.add(new JScrollPane(jTable), BorderLayout.SOUTH);
        this.add(right);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
        initialiseTree();
    }

    private void initialiseTree() {
        DefaultTreeModel defaultTreeModel = appCore.loadResource();
        jTree = new JTree(defaultTreeModel);
        jTree.addTreeSelectionListener(new SelectionListener());
        jsp = new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        left = new JPanel(new BorderLayout());
        left.add(jsp, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        pack();
    }


    @Override
    public void update(Notification notification) {


    }
}
