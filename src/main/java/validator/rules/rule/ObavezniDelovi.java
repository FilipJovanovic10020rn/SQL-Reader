package validator.rules.rule;

import gui.MainFrame;
import tree.TreeItem;
import validator.rules.AbstractRule;

public class ObavezniDelovi extends AbstractRule {

    TreeItem root;


    public ObavezniDelovi() {
//        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
    }

    @Override
    public String proveraPravila(String upit) {
        root = MainFrame.getInstance().getAppCore().getTree().getRoot();
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
        boolean join = false;
        boolean lr = false;
        boolean using = false;
        boolean lrBio = false;
        boolean on = false;
        boolean where = false;
        boolean groupOrder = false;
        boolean whereBio = false;
        boolean joinPosleWhereGreska = false;

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
                update = true;
                continue;
            }
            if(rec.toLowerCase().equals("set") && update && !set){
                set = true;
                continue;
            }
            if(rec.toLowerCase().equals("exec")){
                exec = true;
                continue;
            }
            if(rec.toLowerCase().equals("left") || rec.toLowerCase().equals("right") && (select || delete) && from){
                if(!join){
                    lr = true;
                }
                lrBio = true;
                continue;
            }
            if(rec.toLowerCase().equals("join") && (select || delete) && from){
                if(where){
                    joinPosleWhereGreska = true;
                }
                join = true;
                continue;
            }
            if(rec.toLowerCase().equals("using") && join){
                using = true;
                continue;
            }
            if(rec.toLowerCase().equals("on") && join){
                on = true;
                continue;
            }
            if(rec.toLowerCase().equals("where") ){
                if(on || using){
                    where = true;
                }
                if((select || delete) && from && (!on || !using)){
                    where = true;
                }
                if(set){
                    where = true;
                }
                whereBio = true;
                continue;
            }
        }
        String greska="";
        if((select && from) || (delete && from) || (insert && into && values) || (update && set) || (exec)){

        }
        else if((select && !from) || (delete && !from)){
            greska += "From nije na dobrom mestu\n";
        }
        else if(insert && !into){
            greska += "Into nije na dobrom mestu\n";
        }
        else if(into && !values){
            greska += "Values nije na dobrom mestu\n";
        }
        else if(update && !set){
            greska += "Set nije na dobrom mestu\n";
        }
        else{
            greska += "Nije dobar redosled\n";
        }
        if((lr && join && (on || using)) || (join && (on || using)) || (!join && !lrBio && (!on && !using))){

        }
        else if(lrBio && join){
            greska += "left i right mora biti ispred joina\n";
        }
        else if(lrBio && !join){
            greska += "posle left i right mora biti join\n";
        }
        if(lr && join && (!on || !using)){
            greska += "on ili using mora biti posle joina\n";
        }
        if(where){

        }
        if(whereBio && !where){
            greska += "where mora biti posle froma ili seta ako nema join\n";
        }
        if(joinPosleWhereGreska){
            greska += "where mora biti posle joina\n";
        }
        return greska;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
