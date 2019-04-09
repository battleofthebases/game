package com.example.botb;

import android.util.Base64;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Parser {

    public static String actionToString(Action action) throws IOException {
        return toString(action);
    }

    public static String boardToString(Board board) throws IOException {
        return toString(board);
    }

    public static Action stringToAction(String jsonData) throws IOException, ClassNotFoundException {
        return (Action) fromString(jsonData);
    }

    public static Board stringToBoard(String boardJson) throws IOException, ClassNotFoundException {
        return (Board) fromString(boardJson);
    }

    private static Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.decode(s, 0);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

}
