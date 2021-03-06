package com.example.botb.controller;

import android.util.Log;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.Location;
import com.example.botb.model.Winner;
import com.example.botb.model.placeable.Placeable;
import java.util.Map;

public class GameModelController {

    private Game game;

    private Board initialLocalBoard, initialRemoteBoard;

    private boolean isPlayerOne;

    public boolean applyAction(boolean localPlayer, Action action) {
        if (game != null) {
            return game.applyAction(localPlayer, action);
        } else {
            throw new NullPointerException("Game is null");
        }
    }

    public Winner checkWinner() {
        return game.checkWinner();
    }

    public boolean gameCanStart() {
        return initialLocalBoard != null && initialRemoteBoard != null;
    }

    public Game getGame() {
        return game;
    }

    public Board getLocalBoard() {
        return game.getLocalBoard();
    }

    public Board getRemoteBoard() {
        return game.getRemoteBoard();
    }

    public boolean isLocalTurn() {
        return game.isLocalTurn();
    }

    public void setInitialLocalBoard(Board board) {
        initialLocalBoard = board;
    }

    public void setInitialRemoteBoard(int width, int height, Map<Location, Placeable> placeables) {
        initialRemoteBoard = new Board(width, height, placeables);
    }

    public void setPlayerOne(boolean playerOne) {
        isPlayerOne = playerOne;
    }

    public void startGame() {
        if (initialLocalBoard != null && initialRemoteBoard != null) {
            Log.d("GAME", "STARTED");
            game = new Game(initialLocalBoard, initialRemoteBoard, isPlayerOne);
        } else {
            throw new NullPointerException("One board is equal null");
        }
    }

    public void reset() {
        game = null;
        initialLocalBoard = null;
        initialRemoteBoard = null;
    }
}