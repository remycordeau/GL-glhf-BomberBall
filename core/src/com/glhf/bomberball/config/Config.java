package com.glhf.bomberball.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public abstract class Config {

    protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Imports a config
     * @param name Config file name
     * @param c Class to serialize
     * @return config class from config file
     */
    public static <T> T importConfig(String name, Class<T> c) {
        try {
            return gson.fromJson(new FileReader("core/assets/configs/" + name + ".json"), c);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot import config : " + e.getMessage());
        }
    }

    /**
     * Exports a config
     * @param name Config file name
     */
    public void export(String name) {
        try {
            Writer writer = new FileWriter(new File("core/assets/configs/" + name + ".json"));
            writer.write(this.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
