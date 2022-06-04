package validator.rules;

import validator.rules.rule.KolonePostoje;
import validator.rules.rule.ObavezniDelovi;

import java.util.ArrayList;

public class RuleInit {

    private ArrayList<AbstractRule> pravila = new ArrayList<>();
    private String poruka;

    public ArrayList inicijalizacija(){
        pravila.add(new KolonePostoje());
        pravila.add(new ObavezniDelovi());

        return pravila;
    }
}
