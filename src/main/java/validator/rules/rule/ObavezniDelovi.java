package validator.rules.rule;

import gui.MainFrame;
import tree.TreeItem;
import validator.rules.AbstractRule;

public class ObavezniDelovi extends AbstractRule {

    TreeItem root;


    public ObavezniDelovi() {
        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
    }

    @Override
    public boolean proveraPravila(String upit) {
        String[] reci = upit.split("[\n, ]");
        boolean select = false;
        boolean delete = false;
        boolean from = false;
        boolean insert = false;
        boolean into = false;
        boolean prethodnoInsetr = false;
        boolean values = false;
        boolean update = false;
        boolean prethodnoUpdate = false;
        boolean set = false;
        boolean exec = false;

        for(String rec: reci) {
            if(rec.toLowerCase().equals("select") && !delete && !insert && !select && !update){
                select = true;
                continue;
            }
            if(rec.toLowerCase().equals("delete") && !select && !insert && !delete && !update){
                delete = true;
                continue;
            }
            if(rec.toLowerCase().equals("from") && (select || delete) && !from){
                from = true;
                continue;
            }
            if(rec.toLowerCase().equals("insert") && !select && !delete && !insert && !update) {
                insert = true;
                prethodnoInsetr = true;
                continue;
            }
            if(rec.toLowerCase().equals("into") && insert && prethodnoInsetr && !into){
                into = true;
                prethodnoInsetr = false;
                continue;
            }
            if(rec.toLowerCase().equals("values") && into && !values){
                values = true;
                continue;
            }
            if(prethodnoInsetr){
                if(into == false){
                    //greska(into nije na pravom mestu)
                }
                prethodnoInsetr = false;
            }
            if(rec.toLowerCase().equals("update") && !select && !delete && !insert && !update){
                insert = true;
                continue;
            }
            if(rec.toLowerCase().equals("set") && insert && !set){
                set = true;
                continue;
            }
            if(rec.toLowerCase().equals("exec")){
                exec = true;
                continue;
            }
        }
        return false;
    }
}
