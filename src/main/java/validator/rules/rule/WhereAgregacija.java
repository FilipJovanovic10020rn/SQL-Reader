package validator.rules.rule;

import validator.rules.AbstractRule;

//sum count min max avg(atribut)

public class WhereAgregacija  extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {

        String greska = "";
        String[] reci = upit.split("[\r\n(), ]");
        boolean where = false;
        for(String rec : reci){
            if(rec.toLowerCase().equals("where")){
                where = true;
                continue;
            }
            if(rec.toLowerCase().equals("sum") || rec.toLowerCase().equals("avg")
            || rec.toLowerCase().equals("count") || rec.toLowerCase().equals("min")
            || rec.toLowerCase().equals("max")){
                if(where){
                    greska += rec + " ne moze biti u sklopu where\n";
                }
                continue;
            }
            if(rec.toLowerCase().equals("order") || rec.toLowerCase().equals("group") || rec.toLowerCase().equals("having")){
                where = false;
            }
        }

        if(greska == ""){
            return null;
        }
        return greska;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }


}
