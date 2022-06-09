package validator;

import validator.rules.AbstractRule;
import validator.rules.RuleInit;

import javax.swing.*;
import java.util.ArrayList;

public class ValidatorImplementacijaa implements ValidatorInterface{

    ArrayList<AbstractRule> pravila;
    RuleInit ruleInit;
    AbstractRule scvPravila;
    JsonInterface jsonInterface;
    String greska;

    public ValidatorImplementacijaa() {
        pravila = new ArrayList<>();
        ruleInit = new RuleInit();
        jsonInterface = new JsonImpl();
        pravila.addAll(ruleInit.inicijalizacija());
        scvPravila = ruleInit.inicijalizacijaCSV();
        greska = "";
    }

    @Override
    public boolean validacija(String upit) {
        String povratnaInformacija = "";
        int brojac=0;
        for(AbstractRule pravilo :pravila){
            if(pravilo.proveraPravila(upit) == null || pravilo.proveraPravila(upit) == ""){
                //tacno
            }
            else{
                greska = jsonInterface.doError(pravilo.getClass().toString());
                System.out.println(greska);
                //System.out.println(brojac++);
                povratnaInformacija += pravilo.proveraPravila(upit);
                //System.out.println(povratnaInformacija);
            }
        }
        if(povratnaInformacija == "" || povratnaInformacija == null){
            return true;
        }
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
