package com.glhf.bomberball;

import com.badlogic.gdx.Input;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;

public class Config {
    private static Config config;
    private static Gson gson;
    private HashMap<Integer, String> inputs;
    private HashMap<String, String> variables;

    public Config() {
        inputs = new HashMap<Integer, String>();
        variables = new HashMap<String, String>();
    }

    /**
     * If the config file doesn't exist this function is called.
     * This function create the default config file
     */
    private void init(File f) throws IOException {
        inputs.put(Input.Keys.UP, "moveUp");
        inputs.put(Input.Keys.RIGHT, "moveRight");
        inputs.put(Input.Keys.DOWN, "moveDown");
        inputs.put(Input.Keys.LEFT, "moveLeft");

        variables.put("DIFFICULTY", "0");
        variables.put("SOLO_LEVEL_NUMBER", "1");
        variables.put("SKIN_PLAYER1", "KnightRed");
        variables.put("SKIN_PLAYER2", "KnightBlue");
        variables.put("SKIN_PLAYER3", "KnightYellow");
        variables.put("SKIN_PLAYER4", "KnightGreen");

        FileWriter file = new FileWriter(f, false);
        file.write(gson.toJson(config));
        file.close();
    }

    public static void load(){
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File f = new File(Constants.PATH_CONFIG_FILE);
            if(!f.exists()){
                config = new Config();
                config.init(f);
            }
            config = gson.fromJson(new FileReader(f),Config.class);

        } catch (FileNotFoundException e)   { e.printStackTrace();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static HashMap<Integer, String> getInputs(){
        return config.inputs;
    }

    public static void setInput(int k, String m){
        config.inputs.put(k, m);
        try {
            FileWriter file = new FileWriter(new File(Constants.PATH_CONFIG_FILE), false);
            file.write(gson.toJson(config));
            file.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
