package validator;

import app.AppCore;
import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
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


        for(String rec : reci){
            if((rec.toLowerCase().equals("select"))){
                //System.out.println("select");
                select=true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                //System.out.println("from");
                select=false;
                from=true;
                continue;
            }
            if(rec.toLowerCase().equals("where")){
                from=false;
                //proveraAtributa();
            }
            if(select){
                //System.out.println("nesto");
                if(proveraAtributa(rec)){
                    atributi[brojacAtributa++]=rec;
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
                    if(proveraValidnostiEntitetaIAtributa()){
                        System.out.println("Dobar upit");
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
        }
        return true;
    }
    private boolean proveraValidnostiEntitetaIAtributa(){

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
            if(entitet.toLowerCase().equals("countries")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("departments")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("departments")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("employees")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("job_history")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("jobs")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("locations")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else if(entitet.toLowerCase().equals("regions")){
                MainFrame.getInstance().getAppCore().readDataFromTable(entitet);
            }
            else{
                //pogresniEntiteti[brojacPogresnihEntiteta++] = entitet;
                return false;
            }
        return true;
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
