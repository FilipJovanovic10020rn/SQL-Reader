package utils;

import org.json.simple.JSONObject;
import validator.rules.rule.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Json {
    public Json() {
        initRules();
    }

    private void initRules() {
        JSONObject jo=new JSONObject();
        jo.put(AlijasiSaNavodnicima.class,initRuleAlias());
        jo.put(CSVProvera.class,initRuleCSVProvera());
        jo.put(GroupBy.class,initRuleGroupBy());
        jo.put(KolonePostoje.class,initRuleKolonePostojie());
        jo.put(ObavezniDelovi.class,initRuleObavezniDelovi());
        jo.put(PravilanRedosled.class,initRulePravilanRedosled());
        jo.put(WhereAgregacija.class,initRuleWhereAgregacija());




        File f=new File("src/main/java/utils/rules.json");
        FileWriter fw=null;
        try {
            fw=new FileWriter(f);
            fw.write(jo.toJSONString());


            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private JSONObject initRuleAlias(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect Alias");
        jsonObject.put(Constants.HINT, "put quotes(\") around the alias");
        jsonObject.put(Constants.DESC,"Aliases with multiple words need to be between quotation marks");

        return jsonObject;
    }
    private JSONObject initRuleKolonePostojie(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect entity or attribute");
        jsonObject.put(Constants.HINT, "Change or delete colomns");
        jsonObject.put(Constants.DESC,"An entity or a attribute must exist for correct use");

        return jsonObject;
    }
    private JSONObject initRuleCSVProvera(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect file components");
        jsonObject.put(Constants.HINT, "Select another entity or change the file");
        jsonObject.put(Constants.DESC,"The file chosen must have the same attributes as the selected entity");

        return jsonObject;
    }
    private JSONObject initRuleGroupBy(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Missing group by");
        jsonObject.put(Constants.HINT, "Add group by statement");
        jsonObject.put(Constants.DESC,"If there is agregation and another atribute in select there must be a group by statement");

        return jsonObject;
    }
    private JSONObject initRuleObavezniDelovi(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Missing keywords");
        jsonObject.put(Constants.HINT, "Try using: Select-From");
        jsonObject.put(Constants.DESC,"All compulsury keywords must be in use");

        return jsonObject;
    }
    private JSONObject initRulePravilanRedosled(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect order");
        jsonObject.put(Constants.HINT, "Try this order: Select-From-Join-On/Using-Where-GroupBy");
        jsonObject.put(Constants.DESC,"The order must be put in a correct way");

        return jsonObject;
    }
    private JSONObject initRuleWhereAgregacija(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Where agregation error");
        jsonObject.put(Constants.HINT, "Delete agregation from while");
        jsonObject.put(Constants.DESC,"Where cant have agregation inside it");

        return jsonObject;
    }
}
