package validator.rules.rule;

import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import tree.TreeItem;
import validator.rules.AbstractRule;

import java.util.List;

public class CSVProvera extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {
        return null;
    }
    public boolean provera(String ent, String atr){
        TreeItem root = MainFrame.getInstance().getAppCore().getTree().getRoot();
        List<DBNode> children = ((DBNodeComposite)root.getDbNode()).getChildren();
        for (DBNode entitet : children){
            if(entitet.getName().toLowerCase().equals(ent.toLowerCase())){
                List<DBNode> dete = ((DBNodeComposite) entitet).getChildren();
//                if (atr.contains(" ")){
//                    atr.replace(" ", "");
//                }
                String[] atributi = atr.split(", ");
                for(int i= 0;i<atributi.length;i++) {
                    System.out.println(atributi[i] + "a");

                }
                //IZ NEKOG RAZLOGA ODBIJA DA VRATI TRUE ZA DVA ISTA STRINGA
                System.out.println("\n" + atributi.length);
                int brojacZaProveru = 0;
                for(int i = 1; i< atributi.length;i++) {
                    for (DBNode atribut : dete) {
                        System.out.println(atribut.getName() + " +" + atributi[i]);
                        if(atribut.getName().toLowerCase().equals(atributi[i])){
                            System.out.println("uso sam" + atribut.getName());
                            brojacZaProveru++;
                        }
                    }
                }
                if(brojacZaProveru == atributi.length-1){
                    return true;
                }
            }
        }
        return false;
    }
}
