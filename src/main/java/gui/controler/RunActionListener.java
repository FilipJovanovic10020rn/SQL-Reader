package gui.controler;

import app.AppCore;
import compiler.Compiler;
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
        validator = new Validator(this.jtx.getText().toString());
        System.out.println(jtx.getText().toString());
        if(validator.proveri()){
            System.out.println("Proso");
        }
        else{
            System.out.println("nije");
        }
        System.out.println(validator.getReci());
       // validator.proveraEntiteta();
    }
}
