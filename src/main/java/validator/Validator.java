package validator;

import gui.MainFrame;

public class Validator {

    // Neka for petlja koja ce ici kroz listu rulova
    // i ako naidjemo na gresku mozda neki stek kako je predlozila a moze i neki niz
    //  greske takodje da se napravi lista i klasa sa atributima tip greske, predlog i jos nesto?
    //  Entitet - Atribut - AtributConstraint

    //nizGreske
    boolean greska;
    String upit;
    String[] reci;
    String[] atributi;
    String[] entiteti;
    int brojacAtributa = 0;
    int brojacEntiteta = 0;

    String[] pogresniEntiteti;
    int brojacPogresnihEntiteta = 0;

    public Validator(String upit) {
        this.upit = upit;
        this.reci = this.upit.split("[\n, ]");
        this.greska = false;
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
                System.out.println("select");
                select=true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                System.out.println("from");
                select=false;
                from=true;
                continue;
            }
            if(rec.toLowerCase().equals("where")){
                from=false;
                proveraAtributa();
            }
            if(select){
                System.out.println("nesto");
                //atributi[brojacAtributa++]=rec;
                continue;
            }
            if(from){
                System.out.println("from proso");
//                entiteti[brojacEntiteta++]=rec;
                proveraEntiteta(rec);
                continue;
            }
        }
        return true;
    }

    private void proveraEntiteta(String entitet){
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
            }

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
    public boolean proveraAtributa(){

        //Entity.get();
        //foreach(Entity){
        //if entity.equals(entitet)
        //ide dalje ili false kao greska

//        foreach(atribut.in.entity){
//            if(atributi.equals(atribut))
//        }
//        else greska



        return true;
        //imace return neku gresku ili true ako nema greske
    }
    public void run(){
        //OVO RADI
        //MainFrame.getInstance().getAppCore().readDataFromTable("departments");
    }
}
