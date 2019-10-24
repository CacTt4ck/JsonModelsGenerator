package com.cactt4ck.jsonmodelsgenerator.types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;

public class Block {

    private File blockState, model;
    private String name;

    public Block(String name) {
        this.name = name;
    }

    public boolean generateFiles(){
        blockState = new File("D:\\blockstate.json");
        JSONObject json = new JSONObject();
        JSONObject variants = new JSONObject();
        JSONObject doubleQuote = new JSONObject();

        doubleQuote.put("model", "block/" + name);
        variants.put("", doubleQuote);
        json.put("variants", variants);

        System.out.println(json.toJSONString());

        return true;
    }

}
