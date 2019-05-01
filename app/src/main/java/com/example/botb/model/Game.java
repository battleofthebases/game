package com.example.botb.model;

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

    public int checkWinner() {
        // TODO: How do we check winner?
        return 0;
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
