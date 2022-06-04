package validator.rules.rule;

import validator.rules.AbstractRule;

public class AlijasiSaNavodnicima extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {

        String[] reci = upit.split("[\n ]");
        boolean as = false;
        boolean navodnici = false;
        boolean imaNavodnike = false;
        String greska = "";
        int brojacReci = 0;
        for(String rec :reci){
//            if(rec.contains(",") && rec.contains("\"") && !navodnici){
//                as = false;
//                continue;
//            }
            if(rec.contains(",") || rec.toLowerCase().equals("from")){
                as = false;
            }
            if(rec.toLowerCase().equals("as")){
                as = true;
                continue;
            }
            if(as && brojacReci > 1 && imaNavodnike){

            }
            if(as){
                //rec.endsWith(",");
                //brojacReci++;
                String slovo = String.valueOf(rec.charAt(rec.length()-1));
                if(rec.startsWith("\"")){
                    brojacReci++;
                }

//                if(rec.contains("\"") && !navodnici){
//                    navodnici = true;
//                    continue;
//                }
//                else if(rec.contains("\"") && navodnici){
//                    navodnici = false;
//                    continue;
//                }
            }
        }
        //Select nesto as nesto, nesto as nesto nesto from nesto

        return null;

    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
