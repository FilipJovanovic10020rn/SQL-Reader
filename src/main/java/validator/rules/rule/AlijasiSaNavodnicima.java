package validator.rules.rule;

import validator.rules.AbstractRule;

import java.util.Locale;

public class AlijasiSaNavodnicima extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {

        String greska = "";
        //String[] reci = upit.split("[\r\n ]");
        if(upit.toLowerCase().startsWith("delete") || upit.toLowerCase().startsWith("insert")
        || upit.toLowerCase().startsWith("exec") || upit.toLowerCase().startsWith("create") || upit.toLowerCase().startsWith("update")){
            return null;
        }
        String[] reciBezSelect = upit.toLowerCase().split("select ");
        String[] reciBezFrom = reciBezSelect[1].toLowerCase().split(" from");
        String[] reci = reciBezFrom[0].split(", ");
        for(String nes : reci){
            //System.out.println(nes);
            String[] rec = nes.split(" ");
            if(rec.length == 1){
                continue;
            }
            boolean as = false;
            for(int i=1;i<rec.length;i++){
                if(rec[i].toLowerCase().equals("as")){
                    continue;
                }
                if(i+1==rec.length){
                        break;
                }
                else{
                    if(rec[i].startsWith("\"") && rec[rec.length-1].endsWith("\"")){
                            System.out.println("ovde sam");
                            break;
                    }
                    else{
                        for(int j=i;j<rec.length;j++) {
                            greska = greska.concat(rec[j]);
                            greska = greska.concat(" ");
                        }
                        greska = greska.concat("mora da bude obavijeno navodnicima\n");
                    }
                }
            }
        }
//        boolean as = false;
//        boolean navodnici = false;
//        boolean imaNavodnike = false;
//        String greska = "";
//        int brojacReci = 0;
//        for(String rec :reci){
////            if(rec.contains(",") && rec.contains("\"") && !navodnici){
////                as = false;
////                continue;
////            }
//            if(rec.contains(",") || rec.toLowerCase().equals("from")){
//                as = false;
//            }
//            if(rec.toLowerCase().equals("as")){
//                as = true;
//                continue;
//            }
//            if(as && brojacReci > 1 && imaNavodnike){
//
//            }
//            if(as){
//                //rec.endsWith(",");
//                //brojacReci++;
//                String slovo = String.valueOf(rec.charAt(rec.length()-1));
//                if(rec.startsWith("\"")){
//                    brojacReci++;
//                }
//
////                if(rec.contains("\"") && !navodnici){
////                    navodnici = true;
////                    continue;
////                }
////                else if(rec.contains("\"") && navodnici){
////                    navodnici = false;
////                    continue;
////                }
//            }
//        }
//        //Select nesto as nesto, nesto as nesto nesto from nesto
//
//        return null;
        if(greska == null || greska == ""){
            return null;
        }
        return greska;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
