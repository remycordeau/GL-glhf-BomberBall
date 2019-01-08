package com.glhf.bomberball;

import com.glhf.bomberball.utils.Constants;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Translator {

    private static HashMap<String, String> dictionary;

    public static void load(String file_name){
        try {
            String path = Constants.PATH_TRANSLATIONS +file_name+".csv";
            dictionary = new HashMap<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.matches("^\\s*$")) continue;
                String[] split = line.split("[ ]*:[ ]*");
                if(split.length != 2 ) throw new RuntimeException("File "+path+" wrongly formated");
                dictionary.put(split[0], split[1]);
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
          catch (IOException e) { e.printStackTrace(); }
    }

    public static String translate(String key){
        return dictionary.get(key);
    }

}
