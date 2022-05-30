package gui.controler;

import gui.ImportFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportActionListener implements ActionListener {

    private ImportFrame instante;//editFrame


    @Override
    public void actionPerformed(ActionEvent e) {
        //Otvori nov prozor za importovanje CSV fajla
        instante = ImportFrame.getInstance();
        instante.setVisible(true);


    }
}
