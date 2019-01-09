package com.glhf.bomberball;

import com.badlogic.gdx.Input;
import com.glhf.bomberball.utils.Constants;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {

    private static HashMap<String, String> dictionary;

    public static void load(String file_name){
        try {
            String path = Constants.PATH_TRANSLATIONS +file_name+".txt";
            dictionary = new HashMap<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
            Pattern pattern = Pattern.compile("^\\s*\"(.*)\"\\s*:\\s*\"(.*)\"\\s*");
            String line;
            while ((line = br.readLine()) != null) {
                if(line.matches("^\\s*$") || line.matches("^#.*")) continue;
                Matcher m = pattern.matcher(line);
                if(m.find())
                    dictionary.put(m.group(1), m.group(2));
                else
                    throw new RuntimeException("File "+path+" wrongly formated");
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
          catch (IOException e) { e.printStackTrace(); }
    }

    public static String translate(String key, Object... objects){
        //TODO enlever la condition d'écriture
        if(!dictionary.containsKey(key)){
            dictionary.put(key,key);
            try {
                FileWriter fw = new FileWriter(Constants.PATH_TRANSLATIONS +"en.txt");
                for(String k : dictionary.keySet())
                    fw.write("\""+k+"\"\t\t:\t\""+dictionary.get(k)+"\"\n");
                fw.close();
            } catch (IOException e) { e.printStackTrace(); }
        }

        return String.format(dictionary.get(key), objects);
    }

    public static String getInputName(String code){
        if(code==null)return "";
        int code_i = Integer.parseInt(code.substring(1));
        if(code.charAt(0)=='M'){
            if(code_i==0)return "Left Click";
            else if(code_i==1)return "Right Click";
            else if(code_i==2)return "Middle Click";
            else return "Mouse Button "+code_i;
        }else if (code.charAt(0)=='K'){
            return Input.Keys.toString(code_i);
        }else{
            assert false;
            return null;
        }
    }

}
