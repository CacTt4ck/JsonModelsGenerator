package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Items {

    private String name;

    public Items(String name) {
        this.name = name;
    }

    public void generateFiles(){
        JSONObject modelJson = new JSONObject(), layer0 = new JSONObject();

        layer0.put("layer0", "item/" + name);
        modelJson.put("textures", layer0);
        modelJson.put("parent", "item/handheld");

        try (FileWriter file = new FileWriter("model.json")) {
            file.write(modelJson.toJSONString().replace("\\", ""));
            System.out.println("\nJSON Object: " + modelJson.toJSONString().replace("\\", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
