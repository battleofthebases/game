package com.example.botb;

import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.ExamplePlaceable;
import com.example.botb.model.placeable.Placeable;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class InputManager {

    private final String TAG = "InputManger";
    private static final InputManager instance = new InputManager();
    private GameController gameController;
    private ConnectionHandler connectionHandler;
    private Board tempLocalBoard;

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        gameController = new GameController();
        //creating the connection
        connectionHandler = new ConnectionHandler(this);

        tempLocalBoard = new Board(10, 8);
        tempLocalBoard.addPlaceable(new ExamplePlaceable(), 0, 0);
        tempLocalBoard.addPlaceable(new ExamplePlaceable(), 1, 1);
        tempLocalBoard.addPlaceable(new ExamplePlaceable(), 2, 2);
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void handleRemoteAction(Action action) {
        gameController.applyAction(false, action);
    }

    public void handleLocalAction(Action action) throws IOException {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage("Action:" + Parser.actionToString(action));
    }
    
    public void setInitialRemoteBoard(Board board) {
        gameController.setInitialRemoteBoard(board.getWidth(), board.getHeight(), board.getPlaceables());
    }
    
    public void setInitialLocalBoard() throws IOException {
        gameController.setInitialLocalBoard(tempLocalBoard);
        connectionHandler.sendMessage("InitialGameBoard:" + Parser.boardToString(tempLocalBoard));
    }

    public Board getLocalBoard() {
        if (gameController.getGame() == null) {
            return tempLocalBoard;
        } else {
            return gameController.getLocalBoard();
        }
    }

    public Board getRemoteBoard() {
        if (gameController.getGame() != null) {
            return gameController.getRemoteBoard();
        }
        return null;
    }

    public Game getGame() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(gameController.getGame());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return (Game) objInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}