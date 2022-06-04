package utils;

import gui.MainFrame;
import resource.DBNode;
import resource.DBNodeComposite;
import tree.TreeItem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileUtils {
    public static void exportFile(String imeFajla){


        java.io.File csvOutputFile = new java.io.File(imeFajla);
        try {
            FileWriter pw = new FileWriter(csvOutputFile);
            String str="";
            String ime=", ";

            for (int i = 0; i < MainFrame.getInstance().getJTable().getColumnCount(); i++) {
                ime+=MainFrame.getInstance().getJTable().getColumnName(i)+", ";
            }
            ime= (String) ime.subSequence(0,ime.length());
            pw.write(ime+"\n");
            for (int i = 0; i < MainFrame.getInstance().getJTable().getRowCount(); i++) {
                for (int j = 0; j < MainFrame.getInstance().getJTable().getColumnCount(); j++) {

                    if(MainFrame.getInstance().getJTable().getValueAt(i, j)!=null  && !Pattern.matches("[0-9]+",MainFrame.getInstance().getJTable().getValueAt(i, j).toString())){
                        str+="'"+MainFrame.getInstance().getJTable().getValueAt(i, j)+"'"+", ";
                    } else if(MainFrame.getInstance().getJTable().getValueAt(i, j)==null ){

                        str+=MainFrame.getInstance().getJTable().getValueAt(i, j)+", ";
                    }
                    else {
                        str+=MainFrame.getInstance().getJTable().getValueAt(i, j)+", ";
                    }


                }
                str= (String) str.subSequence(0,str.length()-2);
                str+="\n";

            }
            pw.write(str);
            pw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    public  static  void importFile(String imeFajla){



        try {
            Scanner scanner=new Scanner(new FileReader(imeFajla));
                    String podaci="";
            if(scanner.hasNextLine()){
                podaci+=scanner.nextLine()+"\n";

            }

            while (scanner.hasNextLine()){

                podaci+= scanner.nextLine()+"\n";

            }
            System.out.println(podaci);
            String p[]=podaci.split("\n");
            String s1 =null;
            String s2="";
            for(int i=0;i<p.length;i++){

                if(i==0){
                    s1=p[i];
                    System.out.println(s1);
                }
                else {
                    s2+=p[i]+"\n";
                }
            }

            //MainFrame.getInstance().getChecker().getBulkImportChecker().setImeKolone(s1);
            //MainFrame.getInstance().getChecker().getBulkImportChecker().setDataFromTableCSV(s2);
            String query="insert into ";
            var treePath = MainFrame.getInstance().getJTree().getSelectionPath();
            if (treePath != null) {
                Object obj;
                obj = treePath.getLastPathComponent();
                if (obj instanceof TreeItem) {
                    DBNode dbNode = (DBNode) ((TreeItem<?>) obj).getDbNode();
                    query+=dbNode.getName();
                    String entitet = dbNode.getName();
                    String[] atributi = s1.split(", ");
                    query+=" (";
                    for(int i = 1;i< atributi.length;i++){
                        query+=atributi[i];
                        if(i+1 == atributi.length){
                            break;
                        }
                        query+=", ";
                    }
                    query+=") values ";
                    //query+=" ("+s1+") values ";

                    String[] ah=s2.split("\n");
                    for(String sx:ah){
                        query+="("+sx+") ,";
                    }
                    query=query.substring(0,query.length()-1);
                    System.out.println(query);
                    if(s1 != null) {
                        if(MainFrame.getInstance().getValidator().validacijaCSV(entitet,s1)){
                        //if (MainFrame.getInstance().provera(entitet, s1)) {
                            System.out.println("Izvrseno");
                            //MainFrame.getInstance().getAppCore().readDataFromTable(query);
                        } else {
                            System.out.println("Redovi ne postoje u tabeli");
                        }
                    }
                    //if(MainFrame.getInstance().getChecker().check(query))
                        //MainFrame.getInstance().getAppCore().execute(query);
                }
            }


        } catch (FileNotFoundException ex) {
            System.out.println("greska");
            ex.printStackTrace();
        }
    }
}
