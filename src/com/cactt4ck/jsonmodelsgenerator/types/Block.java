package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Block {

    private File blockState, model;
    private String name;

    public Block(String name) {
        this.name = name;
    }

    public boolean generateFiles(){
        JSONObject json = new JSONObject();
        JSONObject variants = new JSONObject();
        JSONObject doubleQuote = new JSONObject();

        doubleQuote.put("model", "block/" + name);
        variants.put("", doubleQuote);
        json.put("variants", variants);

        try (FileWriter file = new FileWriter("D:\\blockstate.json")) {
            file.write(json.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json.toJSONString());

        return true;
    }

}
