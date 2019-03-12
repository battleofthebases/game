package com.example.botb;

import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.weapon.WeaponFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static Gson gson;
    private static final Parser instance = new Parser();

    private Parser(){
        gson = new Gson();
    }

    public static Parser getInstance(){
        return instance;
    }

    public static Action jsonToAction(String jsonData) {
        return gson.fromJson(jsonData,Action.class);
    }

    public static String actionToJson(Action action){
        return gson.toJson(action);
    }


    public static String boardToJson(Board board){
        return gson.toJson(board);
    }

    public static Board jsonToBoard(String boardJson){
        return gson.fromJson(boardJson,Board.class);
    }
}
