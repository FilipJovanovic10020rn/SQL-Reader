package gui.controler;

import gui.ImportFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzaberiCsvActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser choser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("XLSX file", "csv", "xls","XLS","CSV","xlsx","XLSX");
        choser.setFileFilter(extensionFilter);
        int vrednost = choser.showOpenDialog(null);
        if(vrednost==JFileChooser.APPROVE_OPTION) {
            ImportFrame.getInstance().setUnosSlike(choser.getSelectedFile().getAbsolutePath());
        }

    }
}
