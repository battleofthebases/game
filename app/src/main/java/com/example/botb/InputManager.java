package com.example.botb;

import android.util.Log;
import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.placeable.Nexus;
import com.example.botb.model.placeable.Shield;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputManager {

    private static final InputManager instance = new InputManager();

    private final String TAG = "InputManger";

    private ConnectionHandler connectionHandler;

    private GameController gameController;

    private Board tempLocalBoard;

    public static InputManager getInstance() {
        return instance;
    }

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        gameController = new GameController();
        //creating the connection
        connectionHandler = new ConnectionHandler(this);

        tempLocalBoard = new Board(10, 8);
        tempLocalBoard.addPlaceable(new Nexus(), 0, 0);
        tempLocalBoard.addPlaceable(new Shield(), 0, 1);
        tempLocalBoard.addPlaceable(new Shield(), 0, 2);
        tempLocalBoard.addPlaceable(new Shield(), 0, 3);
        tempLocalBoard.addPlaceable(new Shield(), 0, 4);
    }

    public Game getGame() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(gameController.getGame());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return (Game) objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Board getLocalBoard() {
        if (gameController.getGame() == null) {
            return tempLocalBoard;
        } else {
            return gameController.getLocalBoard();
        }
    }

    public Board getRemoteBoard() {
        if (gameController.getGame() == null) {
            return tempLocalBoard;
        } else {
            return gameController.getRemoteBoard();
        }
    }

    public void handleLocalAction(Action action) throws IOException {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage("Action:" + Parser.actionToString(action));
    }

    public void handleRemoteAction(String actionJson) throws IOException, ClassNotFoundException {
        Log.e(TAG, "handleRemoteAction: " + Parser.stringToAction(actionJson).getWeapon().toString());
        gameController.applyAction(false, Parser.stringToAction(actionJson));
    }

    public void setInitialLocalBoard() throws IOException {
        gameController.setInitialLocalBoard(tempLocalBoard);
        connectionHandler.sendMessage("InitialGameBoard:" + Parser.boardToString(tempLocalBoard));
    }

    public void setInitialRemoteBoard(String boardJson) throws IOException, ClassNotFoundException {
        Board remoteBoard = Parser.stringToBoard(boardJson);
        gameController
                .setInitialRemoteBoard(remoteBoard.getWidth(), remoteBoard.getHeight(), remoteBoard.getPlaceables());
    }


}