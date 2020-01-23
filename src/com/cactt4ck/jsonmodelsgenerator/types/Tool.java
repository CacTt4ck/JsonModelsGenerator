package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Tool implements ModObject {

    private String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public void generateFiles(){
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
            System.out.println("\nJSON Object: " + blockstateJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("model.json")) {
            file.write(modelJson.toJSONString().replace("\\", ""));
            System.out.println("\nJSON Object: " + blockstateJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
