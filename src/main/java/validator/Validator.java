package validator;

import app.AppCore;
import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import tree.TreeItem;

import java.util.List;

public class Validator {
    //TREBA DA SE IZMENI MYSQLREPOSITORY GET FUNKCIJA DA NE PRIMA FROM NEGO DA PRIMA SVE PODATKE
    // Neka for petlja koja ce ici kroz listu rulova
    // i ako naidjemo na gresku mozda neki stek kako je predlozila a moze i neki niz
    //  greske takodje da se napravi lista i klasa sa atributima tip greske, predlog i jos nesto?
    //  Entitet - Atribut - AtributConstraint

    //nizGreske
//    String[] listaEntiteta = {"countries","","","","",""}
    boolean greska;
    String upit;
    String[] reci;
    String[] atributi;
    String[] entiteti;
    int brojacAtributa = 0;
    int brojacEntiteta = 0;
    TreeItem root;

    String query;

    String[] pogresniEntiteti;
    int brojacPogresnihEntiteta = 0;

    public Validator(String upit) {
        this.upit = upit;
        this.reci = this.upit.split("[\n, ]");
        this.greska = false;
        this.atributi = new String[10];
        this.entiteti = new String[10];
        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
        //proveri();
    }

    public String getUpit(){
        return upit;
    }
    public String getReci(){
        return reci[0];
    }

    public boolean proveri(){
        boolean select = false;
        boolean from = false;
        boolean where = false;
        boolean where1 = false;
        boolean where2 = false;
        String tipWhere = null;


        for(String rec : reci){
            if((rec.toLowerCase().equals("select"))){
                query = "SELECT ";
                //System.out.println("select");
                select=true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                //System.out.println("from");
                query = query.concat(" FROM ");
                select=false;
                from=true;
                continue;
            }
            if(rec.toLowerCase().equals("where")){
                query = query.concat(" WHERE ");
                where=true;
                from=false;
                continue;
                //proveraAtributa();
            }
            if(select){
                //System.out.println("nesto");
                if(proveraAtributa(rec)){
                    atributi[brojacAtributa++]=rec;
                    if(brojacAtributa == 1) {
                        query = query.concat(rec);
                    }
                    if(brojacAtributa > 1){
                        query = query.concat(" , ");
                        query = query.concat(rec);
                    }
                }
                else{
                    System.out.println("greska validnosti atributa");
                }
                continue;
            }
            if(from){
                //System.out.println("from proso");

                if(proveraEntiteta(rec)){
                    entiteti[brojacEntiteta++]=rec;
                    if(brojacEntiteta == 1){
                        query = query.concat(rec);
                    }
                    if(brojacEntiteta > 1){
                        query = query.concat(" , ");
                        query = query.concat(rec);
                    }
                    if(proveraValidnostiEntitetaIAtributa()){
                        System.out.println("Dobar upit");
                        System.out.println(query);
                        System.out.println("ispisao je ceo query");
                        MainFrame.getInstance().getAppCore().readDataFromTable(query);
                    }
                    else {
                        System.out.println("los upit");
                    }
                }
                else{
                    System.out.println("greska validnosti entiteta");
                }
                continue;
            }
            if(where){

                if(proveraAtributa(rec)){
                    for(int i = 0;i<brojacAtributa;i++){
                        if(atributi[i].toLowerCase().equals("*") || atributi[i].toLowerCase().equals(rec)){
                            for(int j=0;j<brojacEntiteta;j++){
                                List<DBNode> children  = ((DBNodeComposite) root.getDbNode()).getChildren();
                                for (DBNode entiti : children) {
                                    if(entiti.getName().toLowerCase().equals(entiteti[j].toLowerCase())) {
                                        List<DBNode> deca = ((DBNodeComposite) entiti).getChildren();
                                        for(DBNode dete : deca){
                                            if(dete.getName().toLowerCase().equals(rec)){
                                                String tip = String.valueOf(((Attribute) dete).getAttributeType());
                                                if(tip.equals(AttributeType.CHAR)||tip.equals(AttributeType.VARCHAR)||
                                                        tip.equals(AttributeType.TEXT)||tip.equals(AttributeType.NVARCHAR)){
                                                    tipWhere = "rec";
                                                    where1 = true;
                                                    where = false;
                                                    continue;
                                                }
                                                else{
                                                    tipWhere = "broj";
                                                    where1 = true;
                                                    where=false;
                                                    query = query.concat(rec);
                                                    query = query.concat(" ");
                                                    continue;
                                                }
//                                                if(proveraWhere(rec)){
//
//                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            //greska
                        }
                    }
                }
            }
            if(where1){
                if(tipWhere.equals("rec")){
                    //nesto sa lajk i tako nesto
                    where1= false;
                    continue;
                }
                else if(tipWhere.equals("broj")){
                    if(rec.equals("<") || rec.equals(">") || rec.equals("=")){
                        query = query.concat(rec);
                        query = query.concat(" ");
                        where2 = true;
                        where1 = false;
                        continue;
                    }
                }
                else{
                    where1 =false;
                    //greska
                }

            }
            if(where2){
                if(tipWhere.equals("broj")){
                    try {
                        int p = Integer.parseInt(rec);
                        System.out.println("Pravilan broj");
                        query = query.concat(rec);
                        MainFrame.getInstance().getAppCore().readDataFromTable(query);
                    }catch (Exception e){
                        System.out.println("Niste uneli broj");
                    }
                }
                else if(tipWhere.equals("rec")){
                    where2 = false;
                }
                else{
                    where2=false;
                    //greska
                }
            }
        }
        return true;
    }
    private boolean proveraWhere(String rec){
        List<DBNode> children  = ((DBNodeComposite) root.getDbNode()).getChildren();
        for (DBNode entiti : children) {
            List<DBNode> deca = ((DBNodeComposite) entiti).getChildren();
            for (DBNode atributiDBNode : deca) {
                if(atributiDBNode.getName().toLowerCase().equals(rec)) {
                    String tip = String.valueOf(((Attribute) atributiDBNode).getAttributeType());
                    System.out.println(tip);
                }
            }
        }






        return false;
    }
    private boolean proveraValidnostiEntitetaIAtributa(){

        if(brojacAtributa == 0){{
            return false;
        }}
        for(int i = 0;i<brojacAtributa;i++){
            if(atributi[i].equals("*")){
                return true;
            }
        }
        List<DBNode> children  = ((DBNodeComposite) root.getDbNode()).getChildren();
        int tacnoAtribut = 0;
        for(int i = 0; i<brojacEntiteta; i++) {
            for (DBNode entiti : children) {
                if (entiti.getName().toLowerCase().equals(entiteti[i].toLowerCase())){
                    List<DBNode> deca = ((DBNodeComposite) entiti).getChildren();
                    for(int j = 0;j<brojacAtributa;j++) {
                        for (DBNode atributiDBNode : deca) {
                            if(atributiDBNode.getName().toLowerCase().equals(atributi[j])){
                                tacnoAtribut++;
                            }
                        }
                    }
                }
            }
        }
        if(tacnoAtribut != brojacAtributa){
            System.out.println("greska atributa u ");
            return false;
        }
        return true;
    }



    private boolean proveraEntiteta(String entitet){
        List<DBNode> children  = ((DBNodeComposite) root.getDbNode()).getChildren();
        for (DBNode entiti : children) {
            if (entiti.getName().toLowerCase().equals(entitet.toLowerCase())){
                return true;
            }
        }
        return false;

//
//        if(brojacPogresnihEntiteta == 0) {
////            MainFrame.getInstance().getAppCore().readDataFromTable(entiteti[0]);
//            return true;
//        }
//        else{
////            brojacPogresnihEntiteta = 0;
//            return false;
//        }
    }
    //ovo je samo provera da li je validan atribut
    public boolean proveraAtributa(String rec){

        if(rec.equals("*")){
            return true;
        }
        List<DBNode> children  = ((DBNodeComposite) root.getDbNode()).getChildren();
        for (DBNode entiti: children) {
            if(entiti instanceof Entity) {
                //System.out.println(entiti.getName());
                List<DBNode> deca = ((DBNodeComposite) entiti).getChildren();
                for (DBNode atribut: deca) {
                    if(atribut instanceof Attribute){
                        //System.out.println(atribut.getName());
                        if(rec.toLowerCase().equals(atribut.getName().toLowerCase())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;

        //Entity.get();
        //foreach(Entity){
        //if entity.equals(entitet)
        //ide dalje ili false kao greska

//        foreach(atribut.in.entity){
//            if(atributi.equals(atribut))
//        }
//        else greska




        //imace return neku gresku ili true ako nema greske
    }
    public void run(){
        //OVO RADI
        //MainFrame.getInstance().getAppCore().readDataFromTable("departments");
    }
}
