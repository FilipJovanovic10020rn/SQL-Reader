package validator.rules.rule;

import validator.rules.AbstractRule;

public class PravilanRedosled extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {
        String[] reci = upit.split("[\n ]");
        boolean insert = false;
        boolean into = false;
        boolean select = false;
        boolean delete = false;
        boolean from = false;
        boolean exec = false;
        boolean update = false;
        boolean set = false;
        for(String rec: reci) {
            if(rec.toLowerCase().equals("create")){
                return null;
            }
            if(rec.toLowerCase().equals("insert")){
                insert = true;
            }
            if(rec.toLowerCase().equals("into")){
                into = true;
            }
            if(rec.toLowerCase().equals("select")){
                select = true;
            }
            if(rec.toLowerCase().equals("delete")){
                delete = true;
            }
            if(rec.toLowerCase().equals("from")){
                from = true;
            }
            if(rec.toLowerCase().equals("exec")){
                exec = true;
            }
            if(rec.toLowerCase().equals("update")){
                update = true;
            }
            if(rec.toLowerCase().equals("set")){
                set = true;
            }
        }
        if((insert && into) || (select && from) || (delete && from) || (update && set) || exec){
            return null;
        }
        else if(insert && !into){
            return "Fali into\n";
        }
        else if(!insert && into){
            return  "Fali insert\n";
        }
        else if(!select && !delete && from){
            return "Fali select ili delete\n";
        }
        else if((select || delete) && !from){
            return "Fali from\n";
        }
        else if(update && !set){
            return "Fali set\n";
        }
        else if(set && !update){
            return "Fali update\n";
        }
        else{
            return "Greksa nema ni jedna kljucna rec\n";
        }
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
