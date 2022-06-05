package validator.rules.rule;

import validator.rules.AbstractRule;

// unutar zagrada proemnljiva mora da se javi negde unutar koda

public class DefinisanePromenljive extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {
        String greska = "";

        if(!(upit.startsWith("create or replace function") || upit.startsWith("create or replace procedure"))){
            return null;
        }

        String[] reci = upit.split("()");



        String novaRec = null;
        boolean prviUlaz = false;
        boolean zagrade = false;
        boolean create = false;
        boolean jeste = false;

        String[] glupost = reci[1].split(" ");
        String promenljiva = glupost[0];
        //create or replace function brojDrzava(region number) return number as b number
        // begin select count(country_id) into b from  countries where region_id = region return b end

        for(int i = 2;i<reci.length;i++) {
            if(reci[i].contains(promenljiva)){
                jeste = true;
            }

        }
        if(jeste){
            return null;
        }
        return "Mora se iskoristiti " + novaRec + " u funkciji/proceduri";
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }

}
