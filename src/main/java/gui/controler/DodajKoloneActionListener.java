package gui.controler;

import gui.ImportFrame;
import utils.FileUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajKoloneActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
            FileUtils.importFile(ImportFrame.getInstance().getUnosSlike());

    }
}
