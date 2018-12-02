package com.glhf.bomberball.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public abstract class ConfigTmp {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigTmp() {}

    public static ConfigTmp importConfig(String name) {
        try {
            return gson.fromJson(new FileReader("core/assets/configs/" + name + ".json"), ConfigTmp.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot import config : " + e.getMessage());
        }
    }

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
