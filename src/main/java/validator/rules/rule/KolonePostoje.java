package validator.rules.rule;

import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import tree.TreeItem;
import validator.rules.AbstractRule;

import java.util.List;

public class KolonePostoje extends AbstractRule {

    TreeItem root;


    public KolonePostoje() {
        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
    }

    @Override
    public boolean proveraPravila(String upit) {
        String[] reci = upit.split("[\n, ]");
        for(String rec: reci) {
            boolean select = false;
            boolean from = false;
            boolean where = false;

            if(rec.toLowerCase().equals("select")){
                select = true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                select = false;
                from = true;
                continue;
            }
            if(rec.toLowerCase().equals("where")){
                from = false;
                where = true;
                continue;
            }
            if(from) {
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteEntitet = false;
                for (DBNode entiteti : children) {
                    if (entiteti.getName().toLowerCase().equals(rec)) {
                        jesteEntitet = true;
                        break;
                    }
                }
                if(!jesteEntitet){
                    //greska
                }
            }
            if(select) {
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteAtribut = false;
                for (DBNode entiteti : children) {
                    boolean jeste = false;
                    List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                    for (DBNode atributi : childrenA) {
                        if(atributi.getName().toLowerCase().equals(rec)){
                            jeste =true;
                            jesteAtribut = true;
                            break;
                        }
                        //greska.add(rec);
                    }
                    if(jeste){
                        break;
                    }
                }
                if(!jesteAtribut){
                    //greska
                }
            }
            if(where){
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteAtribut = false;
                for (DBNode entiteti : children) {
                    boolean jeste = false;
                    List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                    for (DBNode atributi : childrenA) {
                        if(atributi.getName().toLowerCase().equals(rec)){
                            jeste =true;
                            jesteAtribut = true;
                            break;
                        }
                        //greska.add(rec);
                    }
                    if(jeste){
                        break;
                    }
                }
                if(!jesteAtribut){
                    //greska
                }
            }
        }

        return false;
    }
}
