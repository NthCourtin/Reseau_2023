package main;

import objet.Data;

import java.util.ArrayList;
import java.util.Scanner;

public class Requete {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Data> listeData = new ArrayList<>();

    public Requete(){}

    public static void newRequete(){
        boolean stop = false;
        while (!stop){
            String requete = sc.nextLine();
            stop = identifyFunction(requete);
        }

    }

    public static boolean identifyFunction(String requete){
        String[] tabRequete = requete.split(" ");
        try{
            if (tabRequete[0].equals("SET")){
                set(tabRequete[1],tabRequete[2]);
            }
            if (tabRequete[0].equals("GET")){
                get(tabRequete[1]);
            }
            if (tabRequete[0].equals("STRLEN")){
                strlen(tabRequete[1]);
            }
            if (tabRequete[0].equals("APPEND")){
                append(tabRequete[1],tabRequete[2]);
            }
            if (tabRequete[0].equals("INCR")){
                incr(tabRequete[1]);
            }
            if (tabRequete[0].equals("INCR")){
                deincr(tabRequete[1]);
            }
            if (tabRequete[0].equals("EXIST")){
                exist(tabRequete[1]);
            }
            if (tabRequete[0].equals("DEL")){
                del(tabRequete[1]);
            }
        }catch (Exception e){
            System.out.println("WRONG COMMAND");
        }

        return requete.equals("STOP");
    }

    public static void set(String key, String value){
        try{
            listeData.add(new Data(key,value));
            System.out.println("OK");
        }catch (Exception e){
            System.out.println("FAIL");
        }
    }

    public static void get(String key){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                System.out.println(d.getKey()+" / "+d.getValue());
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    public static void strlen(String key){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                System.out.println(d.getValue().length());
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    public static void append(String key, String value){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                d.setValue(d.getValue()+value);
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    public static void incr(String key){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                try {
                    d.setValue(String.valueOf(Integer.parseInt(d.getValue())+1));
                }catch (Exception e){
                    System.out.println("Pas un chiffre");
                }
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    public static void deincr(String key){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                try {
                    d.setValue(String.valueOf(Integer.parseInt(d.getValue())-1));
                }catch (Exception e){
                    System.out.println("Pas un chiffre");
                }
                return;
            }
        }
        System.out.println("NOT FOUND");
    }

    public static void exist(String key){
        for (Data d : listeData){
            if (d.getKey().equals(key)){
                System.out.println(1);
                return;
            }
        }
        System.out.println("0");
    }

    public static void del(String key){
        listeData.removeIf(d -> d.getKey().equals(key));
        System.out.println("NOT FOUND");
    }

}
