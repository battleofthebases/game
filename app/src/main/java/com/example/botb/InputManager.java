package com.example.botb;

import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;


import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class InputManager {

    private final String TAG = "InputManger";
    private static final InputManager instance = new InputManager();
    private GameController gameController;
    private ConnectionHandler connectionHandler;

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        gameController = new GameController();
        //creating the connection
        connectionHandler = new ConnectionHandler(this);
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void handleRemoteAction(String actionJson) throws IOException, ClassNotFoundException {
        Log.e(TAG, "handleRemoteAction: "+Parser.stringToAction(actionJson).getWeapon().toString());
        gameController.applyAction(false, Parser.stringToAction(actionJson));
    }

    public void handleLocalAction(Action action) throws IOException {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage("Action:"+Parser.actionToString(action));
    }
    
    public void setRemoteBoard(String boardJson) throws IOException, ClassNotFoundException {
        Board remoteBoard = Parser.stringToBoard(boardJson);
        gameController.setRemoteBoard(remoteBoard.getWidth(), remoteBoard.getHeight(), remoteBoard.getPlaceables());
    }
    
    public void setLocalBoard(int width, int height, Map<Location, Placeable> map) throws IOException {
        gameController.setLocalBoard(width,height,map);
        connectionHandler.sendMessage("InitialGameBoard:"+Parser.boardToString(new Board(width,height,map)));
    }
}