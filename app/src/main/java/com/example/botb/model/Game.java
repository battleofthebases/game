package com.example.botb.model;

import com.example.botb.Winner;

public class Game {

    private boolean isLocalTurn;

    private Board localBoard;

    private Board remoteBoard;

    public Game(Board localBoard, Board remoteBoard, boolean isLocalTurn) {
        this.localBoard = localBoard;
        this.remoteBoard = remoteBoard;
        this.isLocalTurn = isLocalTurn;
    }

    public boolean applyAction(boolean localPlayer, Action action) {

        if (localPlayer != isLocalTurn) {
            return false;
        }

        boolean success;
        if (localPlayer) {
            success = remoteBoard.applyAction(action);
        } else {
            success = localBoard.applyAction(action);
        }

        if (success) {
            isLocalTurn = !isLocalTurn;
            return true;
        }

        return false;
    }

    public Winner checkWinner() {
        if (localBoard.checkWinCondition()) {
            return Winner.REMOTE_PLAYER;
        } else if (remoteBoard.checkWinCondition()) {
            return Winner.LOCAL_PLAYER;
        }
        return Winner.NONE;
    }

    public Board getLocalBoard() {
        return localBoard;
    }

    public void setLocalBoard(Board localBoard) {
        this.localBoard = localBoard;
    }

    public Board getRemoteBoard() {
        return remoteBoard;
    }

    public void setRemoteBoard(Board remoteBoard) {
        this.remoteBoard = remoteBoard;
    }

    public boolean isLocalTurn() {
        return isLocalTurn;
    }

}
