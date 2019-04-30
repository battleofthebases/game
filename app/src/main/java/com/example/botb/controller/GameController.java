package com.example.botb.controller;

import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import java.util.Map;

public class GameController {

    private Game game;

    private Board initialLocalBoard, initialRemoteBoard;

    public void applyAction(boolean localPlayer, Action action) {
        if (game != null) {
            game.applyAction(localPlayer, action);
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(final Game game) {
        this.game = game;
    }

    public Board getLocalBoard() {
        return game.getLocalBoard();
    }

    public Board getRemoteBoard() {
        return game.getRemoteBoard();
    }

    public void setInitialLocalBoard(Board board) {
        initialLocalBoard = board;
    }

    public void setInitialRemoteBoard(final Board initialRemoteBoard) {
        this.initialRemoteBoard = initialRemoteBoard;
    }

    public void setInitialRemoteBoard(int width, int height, Map<Location, Placeable> placeables) {
        initialRemoteBoard = new Board(width, height, placeables);
    }

    public boolean startGame(boolean isLocalTurn) {
        if (initialLocalBoard != null && initialRemoteBoard != null) {
            game = new Game(initialLocalBoard, initialRemoteBoard, isLocalTurn);
            return true;
        }
        return false;
    }
}