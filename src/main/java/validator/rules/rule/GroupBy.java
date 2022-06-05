package validator.rules.rule;

import validator.rules.AbstractRule;


//mora da ima agregaciju kao i jos jedan atribut da bi mogo group by
public class GroupBy extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {
        String[] reci = upit.split("[\r\n(), ]");
        boolean agregacija = false;
        boolean group = false;
        boolean select = false;
        String[] atributZaAgregaciju = new String[100];
        String[] atributi = new String[100];
        int brojAtributaZaAgregaiju = 0;
        int brojAtributa = 0;
        boolean agregacijaSledece = false;
        boolean moraGroup = false;

        for(String rec : reci){
            if(rec.equals("") || rec.equals(" ") || rec.equals("\n")
                    || rec.equals(" \n")|| rec.equals("\n ") || rec.contains("\n")){
                //nista;
                //System.out.println("radnom rec");
                continue;
            }
            if(rec.toLowerCase().equals("create")){
                return null;
            }
            if(rec.toLowerCase().equals("from")){
                if(brojAtributa == 0){
                    return null;
                }
                else if(brojAtributaZaAgregaiju > 0 && brojAtributa > 0){
                    moraGroup = true;
                }
                else if(brojAtributaZaAgregaiju ==0){
                    return null;
                }
            }
            if(rec.toLowerCase().equals("select")){
                select = true;
                continue;
            }
            if(rec.toLowerCase().equals("sum") || rec.toLowerCase().equals("avg")
                    || rec.toLowerCase().equals("count") || rec.toLowerCase().equals("min")
                    || rec.toLowerCase().equals("max")){
                agregacija = true;
                agregacijaSledece = true;
                continue;
            }
            if(rec.toLowerCase().equals("group") && agregacija){
                group = true;
            }
            if(select && agregacijaSledece){
                agregacijaSledece = false;
                atributZaAgregaciju[brojAtributaZaAgregaiju++]=rec;
                continue;
            }
            if(select){
                atributi[brojAtributa++] = rec;
            }

        }

        if(moraGroup && !group){
            return "Mora imati group ako koristite agregaciju i dodatne atribute u pozivu\n";
        }
        return null;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
