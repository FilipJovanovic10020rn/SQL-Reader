package validator.rules.rule;

import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import tree.TreeItem;
import validator.rules.AbstractRule;

import java.util.List;
//select job_id, max_salary from jobs

public class KolonePostoje extends AbstractRule {

    TreeItem root;
    String[] entitetiLista;
    String[] atributiLista;
    int brojacEntiteta;
    int brojacAtributa;
    int brojacAlijasa;
    int brojacAlijasaAtributa;
    boolean bilaZvezdica = false;
    String[] provereniAtributi = new String[10];
    int brojacProverenih;

    public KolonePostoje() {
        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
    }

    @Override
    public String proveraPravila(String upit) {
        //root = MainFrame.getInstance().getAppCore().getTree().getRoot();
        String greska = "";
        String[] reci = upit.split("[\r\n(), ]");

        atributiLista = new String[10];
        entitetiLista = new String[10];

        brojacEntiteta= 0;
        brojacAtributa=0;
        brojacAlijasaAtributa=0;
        brojacAlijasa=0;
        brojacProverenih = 0;



        boolean select = false;
        boolean from = false;
        boolean where = false;
        boolean into = false;
        boolean update = false;
        boolean set = false;
        boolean preskoci = false;

        boolean delete = false;
        boolean navodniciA = false;
        boolean navodnici = false;
        boolean as = false;
        boolean alijasNavodnici = false;
        boolean atributJestBag = false;
        boolean using = false;
        boolean on = false;
        boolean andor = false;
        boolean between = false;
        boolean alijass = false;
        boolean skipDokNijeSelect = false;
        boolean agregaci =false;
        boolean intoBio = false;
        boolean navo = false;
        for(String rec: reci) {
            if(select && rec.toLowerCase().equals("distinct")){
                continue;
            }
            if(rec.toLowerCase().startsWith("\"")){
                navo=true;
                continue;
            }
            if(navo && rec.toLowerCase().endsWith("\"")){
                navo=false;
                continue;
            }
            if(navo){
                continue;
            }
            if(rec.startsWith("\'") && rec.endsWith("\'")){
                continue;
            }
            if(rec.toLowerCase().equals("create")){
                return null;
            }
            if(!rec.toLowerCase().equals("select") && skipDokNijeSelect){
                continue;
            }
            if(rec.equals("") || rec.equals(" ") || rec.equals("\n")
                    || rec.equals(" \n")|| rec.equals("\n ") || rec.contains("\n")){
                //nista;
                //System.out.println("radnom rec");
                continue;
            }
            if(preskoci){
                preskoci = false;
                continue;
            }
            if(as){
                as = false;
                continue;
            }
            if(rec.equals("=") || rec.equals("<")|| rec.equals(">")|| rec.toLowerCase().equals("like")
                    || rec.equals("<=")|| rec.equals(">=")|| rec.equals("<>")
                    || rec.toLowerCase().equals("between")|| rec.toLowerCase().equals("in")){
                preskoci = true;
                between = true;
                continue;
            }
            if(rec.toLowerCase().equals("sum") || rec.toLowerCase().equals("avg")
                    || rec.toLowerCase().equals("count") || rec.toLowerCase().equals("min")
                    || rec.toLowerCase().equals("max")){
                //from = false;
                agregaci = true;
                continue;
            }
            if(rec.toLowerCase().equals("having") || rec.toLowerCase().equals("group") || rec.toLowerCase().equals("order")){
                from = false;
                continue;
            }
            if(rec.toLowerCase().equals("by")){
                where = true;
                continue;
            }
            if(rec.toLowerCase().equals("as")){
                as=true;
                continue;
            }
            if(rec.toLowerCase().equals("select")){
                select = true;
                continue;
            }
            if(rec.toLowerCase().equals("from")){
                select = false;
                from = true;
                continue;
            }
            if(rec.toLowerCase().equals("delete")){
                delete = true;
                continue;
            }
            if(rec.toLowerCase().equals("insert")){
                continue;
            }
            if(rec.toLowerCase().equals("create")){
                skipDokNijeSelect = true;
            }
            if(rec.toLowerCase().equals("where")){
                set = false;
                from = false;
                where = true;
                continue;
            }
            if(rec.toLowerCase().equals("values")){
                preskoci = true;
                continue;
            }
            if(rec.toLowerCase().equals("into")){
                into=true;
                intoBio = true;
                continue;
            }
            if(rec.toLowerCase().equals("update")){
                update=true;
                continue;
            }
            if(rec.toLowerCase().equals("set")){
                update = false;
                set = true;
                continue;
            }
            if(rec.toLowerCase().equals("left") || rec.toLowerCase().equals("right")){
                continue;
            }
            if(rec.toLowerCase().equals("join")){
                from = true;
                continue;
            }
            if(rec.toLowerCase().equals("using")){
                from = false;
                using = true;
                continue;
            }
            if(rec.toLowerCase().equals("on")){
                on = true;
                from = false;
                continue;
            }
            if(rec.toLowerCase().equals("and") || rec.toLowerCase().equals("or")){
                if(between){
                    preskoci = true;
                    continue;
                }
                set = false;
                from = false;
                andor = true;
                continue;
            }
            if(from || into || update) {
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteEntitet = false;
                boolean alijas = false;
                for (DBNode entiteti : children) {
                    //System.out.println(entiteti.getName());
                    if (entiteti.getName().toLowerCase().equals(rec)) {
                        jesteEntitet = true;
                        entitetiLista[brojacEntiteta] = rec;
                        //System.out.println(entitetiLista[0]);
                        brojacEntiteta++;
                        break;
                    }
                }if(into) {
                    select = true;
                    into = false;
                }
                if(brojacEntiteta != brojacAlijasa && !select){
                    alijas = true;
                    if(rec.contains("\"") && navodnici){
                        navodnici = false;
                        brojacAlijasa++;
                        continue;
                    }
                    else if(rec.contains("\"") && !navodnici){
                        navodnici = true;
                        continue;
                    }
                    brojacAlijasa++;
                    continue;

                }
                if(!jesteEntitet && !alijas && !navodnici){
                    greska = greska.concat("Entitet ");
                    greska = greska.concat(rec);
                    greska = greska.concat(" ne postoji.\n");
                    continue;
                }
                continue;
            }
            if(select || set || agregaci) {
                if(rec.equals("*")){
                    bilaZvezdica = true;
                    continue;
                }
//                String novaRec = rec;
//                if(rec.contains(".")){
//                    String[] noveReci = rec.split(".");
//                    novaRec = noveReci[1];
//                }
                boolean jeste = false;
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteAtribut = false;
                for (DBNode entiteti : children) {
                    //System.out.println(entiteti.getName());

                    List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                    for (DBNode atributi : childrenA) {
                        //System.out.println(atributi.getName());
                        if(atributi.getName().toLowerCase().equals(rec.toLowerCase())){
                            jeste =true;
                            jesteAtribut = true;
                            atributJestBag = true;
                            atributiLista[brojacAtributa]=rec;
                            System.out.println(atributiLista[brojacAtributa]);
                            brojacAtributa++;

                            break;
                            //break;
                        }
                    }
                    if(jeste){
                        break;
                    }
                }
                if(jeste){
                    continue;
                }
                if(agregaci){
                    greska = greska.concat("Atribut ");
                    greska = greska.concat(rec);
                    greska = greska.concat(" ne postoji.\n");
                    agregaci = false;
                    continue;
                }
                if(brojacAtributa != brojacAlijasaAtributa && !intoBio) {
                    alijass = true;
                    if(rec.endsWith("\"") && navodniciA) {
                        navodniciA = false;
                        brojacAlijasaAtributa++;
                        continue;
                    }else if(rec.startsWith("\"") && rec.endsWith(",")){
                        brojacAlijasaAtributa++;
                        continue;
                    }else if (rec.startsWith("\"") && !navodniciA) {
                        navodniciA = true;
                        continue;
                    }
                    brojacAlijasaAtributa++;
                }
                //System.out.println(brojacAlijasaAtributa + " " + brojacAtributa);
                //System.out.println(alijass);
                //System.out.println(rec);
                if(jesteAtribut || alijass){
                    alijass = false;
                    continue;
                }
                if(!jesteAtribut && !alijass){
                    greska = greska.concat("Atribut ");
                    greska = greska.concat(rec);
                    greska = greska.concat(" ne postoji.\n");
                    continue;
                }
            }
            if(where  || using || on || andor){
                String novaRec = rec;
                if(rec.contains(".")){
                    String[] noveReci = rec.split(".");
                    novaRec = noveReci[1];
                }
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteAtribut = false;
                for (DBNode entiteti : children) {
                    boolean jeste = false;
                    List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                    for (DBNode atributi : childrenA) {
                        if(atributi.getName().toLowerCase().equals(novaRec)){
                            jeste =true;
                            jesteAtribut = true;
                            continue;
                            //break;
                        }
                    }
                    if(jeste){
                        break;
                    }
                }
                if(jesteAtribut){
                    continue;
                }
                if(!jesteAtribut){
                    greska = greska.concat("Atribut ");
                    greska = greska.concat(rec);
                    greska = greska.concat(" ne postoji.\n");
                    continue;
                }
            }
            else if(delete){
                greska = greska.concat("Posle delete mora biti from a ne atributi");
            }
            else{
                greska = greska.concat("Nepoznata greska sa " + rec + ".\n");
                continue;
            }
        }
        if(!(AtributiIEntiteti() == null || AtributiIEntiteti()== "") && !delete){
            greska = greska.concat(AtributiIEntiteti());
        }
        if(greska == "" || greska == null){
            return null;
        }
        return  greska;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
    private String AtributiIEntiteti(){

        boolean g = false;
        String greska = "";
        if(brojacAtributa == 0 && bilaZvezdica && brojacEntiteta > 0){
            return null;
        }
        if(brojacAtributa == 0 && !bilaZvezdica){
            g = true;
            greska = greska.concat("Nema ni jednog atributa\n");
        }
        if(brojacEntiteta == 0){
            g=true;
            greska = greska.concat("Nema ni jednog entiteta pravilnog\n");
        }
        if(!g){
            for(int i = 0; i<brojacEntiteta;i++){
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                for(DBNode entiteti : children){
                    //System.out.println(entitetiLista[i]);
                    if(entiteti.getName().toLowerCase().equals(entitetiLista[i].toLowerCase())){
                        List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                        for(int j = 0;j<brojacAtributa;j++){
                            for(DBNode atributi : childrenA){
                                //System.out.println("moja lista " + atributiLista[j] + " + " + atributi.getName());
                                if((atributi.getName().toLowerCase().equals(atributiLista[j].toLowerCase())) &&
                                        (proveraMedjuProverenim(atributiLista[j].toLowerCase()))){
                                    provereniAtributi[brojacProverenih++]=atributiLista[j];
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println(brojacAtributa + " " + brojacProverenih);
        if(brojacAtributa != brojacProverenih){
            for(int i = 0;i<brojacAtributa;i++){
                boolean tacan = false;
                for(int j = 0;j<brojacProverenih;j++){
                    if(atributiLista[i].equals(provereniAtributi[j])){
                        tacan = true;
                    }
                }
                if(!tacan){
                    greska = greska.concat(atributiLista[i] + " nije atribut ni jednog entiteta\n");
                }
            }
        }

        return greska;
    }
    private boolean proveraMedjuProverenim(String rec){
        if(brojacProverenih == 0)
            return true;
        else{
            for(int i = 0;i<brojacProverenih;i++){

                    if(provereniAtributi[i].toLowerCase().equals(rec)){
                        return false;
                    }
            }
            return true;
        }
    }
}
