package gui.controler;

import app.AppCore;
import compiler.Compiler;
import gui.MainFrame;
import validator.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunActionListener implements ActionListener {

    JTextPane jtx;
    Validator validator;

    public RunActionListener(JTextPane jtx) {

        this.jtx = jtx;
        //validator = new Validator(this.jtx.getText().toString());
        //Compiler compiler = Compiler();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //AppCore.getInstance().Validacija
        //if(TRUE) AppCore.getInstance().Run?
//        if(validator.proveri()){
//            //ispis
//        }
//        else{
//            //upit
//        }
        //validator.run();

        String upit = jtx.getText();

        if(!upit.isEmpty()) {
            if(MainFrame.getInstance().getValidator().validacija(upit)){
//                System.out.println("proso sam");
                MainFrame.getInstance().getAppCore().readDataFromTable(this.jtx.getText());
            }
        }


        //MainFrame.getInstance().getAppCore().readDataFromTable(this.jtx.getText().toString());




//        validator = new Validator(this.jtx.getText().toString());
//        System.out.println(jtx.getText().toString());
//        if(validator.proveri()){
//            System.out.println("Proso");
//            //MainFrame.getInstance().getAppCore().readDataFromTable(query);
//        }
//        else{
//            System.out.println("nije");
//        }
//        System.out.println(validator.getReci());
       // validator.proveraEntiteta();
    }
}
