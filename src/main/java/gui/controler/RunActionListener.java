package gui.controler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunActionListener implements ActionListener {

    JTextPane jtx;

    public RunActionListener(JTextPane jtx) {
        this.jtx = jtx;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //AppCore.getInstance().Validacija
        //if(TRUE) AppCore.getInstance().Run?
        jtx.setText("KKK");
    }
}
