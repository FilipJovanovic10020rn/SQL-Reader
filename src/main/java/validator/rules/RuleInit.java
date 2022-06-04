package validator.rules;

import validator.rules.rule.CSVProvera;
import validator.rules.rule.KolonePostoje;
import validator.rules.rule.ObavezniDelovi;
import validator.rules.rule.PravilanRedosled;

import java.util.ArrayList;

public class RuleInit {

    private ArrayList<AbstractRule> pravila = new ArrayList<>();
    private String poruka;
    private ArrayList<AbstractRule> pravilo = new ArrayList<>();

    public ArrayList inicijalizacija(){
        pravila.add(new KolonePostoje());
        pravila.add(new PravilanRedosled()); // Zamenio sam nazive pravilanredosled i obavezniDelovi
        pravila.add(new ObavezniDelovi());


        return pravila;
    }
    public AbstractRule inicijalizacijaCSV(){
        return new CSVProvera();
    }
}
