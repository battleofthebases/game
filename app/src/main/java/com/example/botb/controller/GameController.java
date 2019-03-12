package com.example.botb.controller;

import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;

import java.util.Map;

public class GameController {
    private Game game;
    private Board localBoard, remoteBoard;
    public boolean startGame(boolean isLocalTurn){
        if (localBoard != null && remoteBoard != null) {
            game = new Game(localBoard, remoteBoard, isLocalTurn);
            return true;
        }
        return false;
    }
    public void setLocalBoard(int width, int height, Map<Location, Placeable> placeables) {
        localBoard = new Board(width, height, placeables);
    }
    public void setRemoteBoard(int width, int height, Map<Location, Placeable> placeables) {
        remoteBoard = new Board(width, height, placeables);
    }
    public void applyAction(boolean localPlayer, Action action) {
        if (game != null) {
            game.applyAction(localPlayer, action);
        }
    }
}