package validator;

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

    public Validator(String upit) {
        this.upit = upit;
        this.reci = this.upit.split("[\n, ]");
        this.greska = false;
        proveri();
    }

    private void proveri(){
        boolean select = false;
        boolean from = false;


        for(String rec : reci){
            if((rec.toLowerCase().equals("select") || select)){
                select=true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                select=false;
                from=true;
                continue;
            }
            if(rec.toLowerCase().equals("where")){
                from=false;
                proveraAtributa();
            }
            if(select){
                atributi[brojacAtributa++]=rec;
                continue;
            }
            if(from){
                entiteti[brojacEntiteta++]=rec;
                continue;
            }
        }
    }
    private boolean proveraAtributa(){

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
}
