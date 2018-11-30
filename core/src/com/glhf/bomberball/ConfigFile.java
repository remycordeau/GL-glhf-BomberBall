package com.glhf.bomberball;

import java.io.FileNotFoundException;
import java.io.File;
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

    @Override
    public void finalize() {
        this.scanner.close();
    }
}
