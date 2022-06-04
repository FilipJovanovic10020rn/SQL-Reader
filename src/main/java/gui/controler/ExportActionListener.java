package gui.controler;

import gui.MainFrame;
import utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter("csv","csv"));
        var treePath = MainFrame.getInstance().getJTree().getSelectionPath();
        //if (treePath != null) {
        if(MainFrame.getInstance().getJTable().getColumnCount() > 0){
            if (chooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {

                //System.out.println(chooser.getSelectedFile().getAbsolutePath());
                FileUtils.exportFile(chooser.getSelectedFile().getAbsolutePath());
            } else {
                return;

            }
        }else return;


    }
}
