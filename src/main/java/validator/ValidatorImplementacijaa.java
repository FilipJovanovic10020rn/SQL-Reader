package validator;

import validator.rules.AbstractRule;
import validator.rules.RuleInit;

import java.util.ArrayList;

public class ValidatorImplementacijaa implements ValidatorInterface{

    ArrayList<AbstractRule> pravila;
    RuleInit ruleInit;

    public ValidatorImplementacijaa(ArrayList<AbstractRule> pravila) {
        pravila = new ArrayList<>();
        ruleInit = new RuleInit();
        pravila.addAll(ruleInit.inicijalizacija());
    }

    @Override
    public void validacija(String upit) {

        for(AbstractRule pravilo :pravila){
            if(pravilo.proveraPravila(upit)){
                //tacno
            }
            else{
                //netacno
            }
        }

    }
}
