package com.example.botb;

import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;


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

    public void handleRemoteAction(String actionJson) {
        Log.e(TAG, "handleRemoteAction: "+Parser.jsonToAction(actionJson).toString());
        gameController.applyAction(false, Parser.jsonToAction(actionJson));
    }

    public void handleLocalAction(Action action) {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage("Action:"+Parser.actionToJson(action));
    }
    
    public void setRemoteBoard(String boardJson){
        Board remoteBoard = Parser.jsonToBoard(boardJson);
        gameController.setRemoteBoard(remoteBoard.getWidth(), remoteBoard.getHeight(), remoteBoard.getPlaceables());
    }
    
    public void setLocalBoard(int width, int height, Map<Location, Placeable> map){
        gameController.setLocalBoard(width,height,map);
        connectionHandler.sendMessage("InitialGameBoard:"+Parser.boardToJson(new Board(width,height,map)));
    }
}