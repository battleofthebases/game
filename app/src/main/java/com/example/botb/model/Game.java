package com.example.botb.model;

public class Game {

    private Board localBoard;
    private Board remoteBoard;
    private boolean isLocalTurn;

    public Game(Board localBoard, Board remoteBoard, boolean isLocalTurn) {
        this.localBoard = localBoard;
        this.remoteBoard = remoteBoard;
        this.isLocalTurn = isLocalTurn;
    }

    public void applyAction(boolean localPlayer, Action action) {
        if (isLocalTurn == localPlayer) {
            if (localPlayer) remoteBoard.applyAction(action);
            else localBoard.applyAction(action);
            isLocalTurn = !isLocalTurn;
        }
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

    public int checkWinner() {
        // TODO: How do we check winner?
        return 0;
    }

}
