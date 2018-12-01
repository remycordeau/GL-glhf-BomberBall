package com.glhf.bomberball;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class ConfigFile {
    private File file = null;
    private Scanner scanner = null;

    public ConfigFile(String pathname) {
        this.file = new File(pathname);
    }

    public int getIntAttribute(String attributeName) {
        try {
            this.scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = "";
        while (scanner.hasNextLine()) {
            s = scanner.findInLine(attributeName);
            if (s != null) {
                int value = scanner.nextInt();
                scanner.close();
                return value;
            }
            scanner.nextLine();
        }
        System.err.println("Erreur, l'attribut ["+attributeName+"] n'est pas dans le fichier config");
        scanner.close();
        return 0;
    }

    public String getStringAttribute(String attributeName) {
        try {
            this.scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = "";
        while (scanner.hasNextLine()) {
            s = scanner.findInLine(attributeName);
            if (s != null) {
                String value = scanner.next();
                scanner.close();
                return value;
            }
            scanner.nextLine();
        }
        System.err.println("Erreur, l'attribut ["+attributeName+"] n'est pas dans le fichier config");
        scanner.close();
        return null;
    }

    public void setIntAttribute(String attributeName, int newInt){
        //Writing from config.txt to configtmp.txt with new settings
        try{
            this.scanner = new Scanner(this.file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        FileWriter fw = null;

        String s = "";
        String stmp = "";
        while(scanner.hasNextLine()){
            try{
                fw = new FileWriter(Constants.PATH_CONFIG_TMP, true);
            } catch (IOException e){
                e.printStackTrace();
            }
            stmp = scanner.findInLine(attributeName);
            if(stmp != null){
                stmp += " " + newInt;
                scanner.nextInt();
            }
            s = scanner.nextLine();
            try {
                if(stmp != null) {
                    fw.write(stmp + s + "\n");
                    fw.close();
                }else {
                    fw.write(s + "\n");
                    fw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        scanner.close();

        //Writing from configtmp.txt to config.txt (copy)
        Scanner scannercopy = null;
        try {
            scannercopy = new Scanner(new File(Constants.PATH_CONFIG_TMP));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //Borrow old file config.txt
        try{
            fw = new FileWriter(this.file, false);
            fw.write("");
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Copying
        s = "";
        while(scannercopy.hasNextLine()){
            try{
                fw = new FileWriter(this.file, true);
            } catch (IOException e){
                e.printStackTrace();
            }
            s = scannercopy.nextLine();
            try {
                fw.write(s + "\n");
                fw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        scannercopy.close();

        //Reset configtmp.txt
        try{
            fw = new FileWriter(Constants.PATH_CONFIG_TMP, false);
            fw.write("");
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setStringAttribute(String attributeName, String newString){
        //Writing from config.txt to configtmp.txt with new settings
        try{
            this.scanner = new Scanner(this.file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        FileWriter fw = null;

        String s = "";
        String stmp = "";
        while(scanner.hasNextLine()){
            try{
                fw = new FileWriter(Constants.PATH_CONFIG_TMP, true);
            } catch (IOException e){
                e.printStackTrace();
            }
            stmp = scanner.findInLine(attributeName);
            if(stmp != null){
                stmp += " " + newString;
                scanner.next();
            }
            s = scanner.nextLine();
            try {
                if(stmp != null) {
                    fw.write(stmp + s + "\n");
                    fw.close();
                }else {
                    fw.write(s + "\n");
                    fw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        scanner.close();

        //Writing from configtmp.txt to config.txt (copy)
        Scanner scannercopy = null;
        try {
            scannercopy = new Scanner(new File(Constants.PATH_CONFIG_TMP));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //Borrow old file config.txt
        try{
            fw = new FileWriter(this.file, false);
            fw.write("");
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Copying
        s = "";
        while(scannercopy.hasNextLine()){
            try{
                fw = new FileWriter(this.file, true);
            } catch (IOException e){
                e.printStackTrace();
            }
            s = scannercopy.nextLine();
            try {
                fw.write(s + "\n");
                fw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        scannercopy.close();

        //Reset configtmp.txt
        try{
            fw = new FileWriter(Constants.PATH_CONFIG_TMP, false);
            fw.write("");
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        this.scanner.close();
    }
}
