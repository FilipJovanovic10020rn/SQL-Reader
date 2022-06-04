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


    public KolonePostoje() {
        this.root = MainFrame.getInstance().getAppCore().getTree().getRoot();
    }

    @Override
    public String proveraPravila(String upit) {
        //root = MainFrame.getInstance().getAppCore().getTree().getRoot();
        String greska = "";
        String[] reci = upit.split("[\n(), ]");
        boolean select = false;
        boolean from = false;
        boolean where = false;
        boolean into = false;
        boolean update = false;
        boolean set = false;
        boolean preskoci = false;
        int brojacEntiteta = 0;
        int brojacAlijasa = 0;
        boolean navodnici = false;
        boolean as = false;
        boolean alijasNavodnici = false;
        boolean atributJestBag = false;
        for(String rec: reci) {
//            if(preskoci){
//                preskoci = false;
//                continue;
//            }
            if(rec.equals("") || rec.equals(" ") || rec.equals("\n") || rec.equals(" \n")|| rec.equals("\n ") || rec.contains("\n")){
                //nista;
                System.out.println("radnom rec");
                continue;
            }
            if(as){
                as = false;
                continue;
            }
            if(rec.equals("=")){
                preskoci = true;
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
            if(rec.toLowerCase().equals("where")){
                set = false;
                from = false;
                where = true;
                continue;
            }
            if(rec.toLowerCase().equals("into")){
                into=true;
                continue;
            }
            if(rec.toLowerCase().equals("update")){
                update=true;
                continue;
            }
            if(rec.toLowerCase().equals("set")){
                set = true;
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
                        brojacEntiteta++;
                        break;
                    }
                }
                if(brojacEntiteta != brojacAlijasa){
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
            }
            if(select) {
                if(rec.equals("*")){
                    continue;
                }
                String novaRec = rec;
                if(rec.contains(".")){
                    String[] noveReci = rec.split(".");
                    novaRec = noveReci[1];
                }
                List<DBNode> children = ((DBNodeComposite) root.getDbNode()).getChildren();
                boolean jesteAtribut = false;
                for (DBNode entiteti : children) {
                    //System.out.println(entiteti.getName());
                    boolean jeste = false;
                    List<DBNode> childrenA = ((DBNodeComposite) entiteti).getChildren();
                    for (DBNode atributi : childrenA) {
                        //System.out.println(atributi.getName());
                        if(atributi.getName().toLowerCase().equals(rec.toLowerCase())){
                            jeste =true;
                            jesteAtribut = true;
                            atributJestBag = true;
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
                if(jesteAtribut == false){
                    greska = greska.concat("Atribut ");
                    greska = greska.concat(rec);
                    greska = greska.concat(" ne postoji.\n");
                    continue;
                }
            }
            if(where || set){
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
            else{
                greska = greska.concat("Nepoznata greska sa " + rec + ".\n");
                continue;
            }
        }
        if(greska != ""){
            return greska;
        }
        return  null;
    }

    @Override
    public boolean provera(String entitet, String atribut) {
        return false;
    }
}
