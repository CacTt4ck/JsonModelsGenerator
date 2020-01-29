package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Block {

    private String name;
    private JSONObject blockstateJson, modelJson;

    public Block(String name) {
        this.name = name;
    }

    public void generateFiles(){

        this.constructBlockstateJson();
        this.constructModelJson();

        try (FileWriter file = new FileWriter("blockstate.json")) {
            file.write(this.blockstateJson.toJSONString().replace("\\", ""));
            System.out.println("\nJSON Object: " + this.blockstateJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("model.json")) {
            file.write(this.modelJson.toJSONString().replace("\\", ""));
            System.out.println("\nJSON Object: " + this.modelJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void constructBlockstateJson() {
        JSONObject blockstateJson = new JSONObject(), variants = new JSONObject(), doubleQuote = new JSONObject();
        doubleQuote.put("model", "block/" + name);
        variants.put("", doubleQuote);
        blockstateJson.put("variants", variants);
        this.blockstateJson = blockstateJson;
    }

    private void constructModelJson() {
        JSONObject modelJson = new JSONObject(), all = new JSONObject();
        all.put("all", "block/" + name);
        modelJson.put("textures", all);
        modelJson.put("parent", "block/cube_all");
        this.modelJson = modelJson;
    }

}
