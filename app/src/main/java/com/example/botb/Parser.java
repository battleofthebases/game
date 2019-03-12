package com.example.botb;

import android.os.Build;
import android.support.annotation.RequiresApi;

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

    /** Read the object from Base64 string. */
    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String toString(Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static Action stringToAction(String jsonData) throws IOException, ClassNotFoundException {
        return (Action) fromString(jsonData);
    }

    public static String actionToString(Action action) throws IOException {
        return toString(action);
    }

    public static Board stringToBoard(String boardJson) throws IOException, ClassNotFoundException {
        return (Board) fromString(boardJson);
    }

    public static String boardToString(Board board) throws IOException {
        return toString(board);
    }

}
