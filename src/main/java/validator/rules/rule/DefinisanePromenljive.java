package validator.rules.rule;

import validator.rules.AbstractRule;

// unutar zagrada proemnljiva mora da se javi negde unutar koda

public class DefinisanePromenljive extends AbstractRule {
    @Override
    public String proveraPravila(String upit) {
        String greska = "";
        String[] reci = upit.split("[\n, ]");

        boolean prviUlaz = false;
        boolean zagrade = false;
        for(String rec: reci) {
            if(rec.startsWith("(") && !prviUlaz){

            }
        }

        return null;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }

}
