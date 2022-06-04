package utils;

import org.json.simple.JSONObject;
import validator.rules.rule.KolonePostoje;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Json {
    private void initRules() {
        JSONObject jo=new JSONObject();
        jo.put(KolonePostoje.class,initRuleAlias());



        File f=new File("src/main/java/utils/rules.json");
        FileWriter fw=null;
        try {
            fw=new FileWriter(f);
            fw.write(jo.toJSONString());


            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*
       //DECODING OF JSON-s
       Object o= JSONValue.parse(jo.toJSONString());
        JSONObject ar=(JSONObject)o;
        System.out.println();
     JSONObject jow=   (JSONObject)(ar.get("rule1"));
        System.out.println(jow.get("hint"));*/
    }
    private JSONObject initRuleAlias(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.RULE_NAME, "Incorrect Alias");
        jsonObject.put(Constants.HINT, "put quotes(') around %s ");
        jsonObject.put(Constants.DESC,"Aliases with multiple words need to be between quotation marks");

        return jsonObject;


    }
}
