package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Block {

    private String name;

    public Block(String name) {
        this.name = name;
    }

    public boolean generateFiles(){
        JSONObject blockstateJson = new JSONObject(), variants = new JSONObject(), doubleQuote = new JSONObject();
        JSONObject modelJson = new JSONObject(), all = new JSONObject();

        doubleQuote.put("model", "block/" + name);
        variants.put("", doubleQuote);
        blockstateJson.put("variants", variants);

        all.put("all", "block/" + name);
        modelJson.put("textures", all);
        modelJson.put("parent", "block/cube_all");


        try (FileWriter file = new FileWriter("blockstate.json")) {
            file.write(blockstateJson.toJSONString().replace("\\", ""));
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + blockstateJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("model.json")) {
            file.write(modelJson.toJSONString().replace("\\", ""));
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + blockstateJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
