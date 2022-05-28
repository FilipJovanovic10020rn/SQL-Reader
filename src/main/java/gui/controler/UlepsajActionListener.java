package gui.controler;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class UlepsajActionListener implements ActionListener {

    JTextPane jtx;
    String text;

    public UlepsajActionListener(JTextPane jtx) {
        this.jtx = jtx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //AppCore.getInstance().Validacija?

        text = jtx.getText().toString();

        jtx.setText("");

        appendToPane(jtx," ",Color.BLACK,false);
        provera();
//        appendToPane(jtx,text, Color.BLUE,true);
//        appendToPane(jtx,"",Color.BLACK,false);
    }
    private void appendToPane(JTextPane tp, String msg, Color c,boolean t)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

//        if(t){
//            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
//            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
//        }

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
    private void provera(){
        String[] words = text.split(" ");
        for (String word : words) {
            if(word.toLowerCase().equals("select") || word.toLowerCase().equals("from") || word.toLowerCase().equals("where")
                    || word.toLowerCase().equals("having") || word.toLowerCase().equals("or") || word.toLowerCase().equals("join")
                    || word.toLowerCase().equals("and") || word.toLowerCase().equals("rightjoin") || word.toLowerCase().equals("leftjoin")
            ){
                appendToPane(jtx,word.toUpperCase(), Color.BLUE,true);
                appendToPane(jtx," ",Color.BLACK,false);
            }
            else{
                appendToPane(jtx,word, Color.BLACK,false);
                appendToPane(jtx," ",Color.BLACK,false);
            }
        }
        appendToPane(jtx,"",Color.BLACK,false);
    }


//Select * from hr where nesto > 0 and nesto < nesto or nesto!= nesto
}
