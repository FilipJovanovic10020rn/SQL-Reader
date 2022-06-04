package validator;

import validator.rules.AbstractRule;
import validator.rules.RuleInit;

import java.util.ArrayList;

public class ValidatorImplementacijaa implements ValidatorInterface{

    ArrayList<AbstractRule> pravila;
    RuleInit ruleInit;
    AbstractRule scvPravila;

    public ValidatorImplementacijaa() {
        pravila = new ArrayList<>();
        ruleInit = new RuleInit();
        pravila.addAll(ruleInit.inicijalizacija());
        scvPravila = ruleInit.inicijalizacijaCSV();
    }

    @Override
    public boolean validacija(String upit) {
        String povratnaInformacija = "";
        for(AbstractRule pravilo :pravila){
            if(pravilo.proveraPravila(upit) == null){
                //tacno
            }
            else{
                povratnaInformacija += pravilo.proveraPravila(upit);
            }
        }
        if(povratnaInformacija == ""){
            return true;
        }
        //sout
        System.out.println(povratnaInformacija);
        return false;

    }
    public boolean validacijaCSV(String entitet, String atribut) {
        String povratnaInformacija = "";
        if(scvPravila.provera(entitet,atribut)){
            return true;
        }else{
            povratnaInformacija += "Tabela nije pravilno napisana, entiteti fali ili nisu tacni";
        }
        if(povratnaInformacija == ""){
            return true;
        }
        return false;
    }
}
