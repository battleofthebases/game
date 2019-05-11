package com.example.botb.controller;

import android.util.Log;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Game;
import com.example.botb.model.Winner;
import com.example.botb.model.placeable.Nexus;
import com.example.botb.model.placeable.Shield;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InputManager {

    private static final InputManager instance = new InputManager();

    private final String TAG = "InputManger";

    private ConnectionHandler connectionHandler;

    private GameModelController gameModelController;

    private List<InputSubscriber> subscribers = new ArrayList<InputSubscriber>();

    private Board tempLocalBoard;

    public static InputManager getInstance() {
        return instance;
    }

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        //creating the ConnectionActivity
        connectionHandler = new ConnectionHandler(this);
        gameModelController = new GameModelController();
    }

    public void connectToServer() {
        connectionHandler.connect();
    }

    public void setupGame() {
        gameModelController.reset();

        // Create initial local board
        tempLocalBoard = new Board(10, 8);
        tempLocalBoard.addPlaceable(new Shield(), 0, 0);
        tempLocalBoard.addPlaceable(new Shield(), 1, 1);
        tempLocalBoard.addPlaceable(new Shield(), 2, 2);
        tempLocalBoard.addPlaceable(new Nexus(), 3, 3);

        Log.d(TAG, "Setup game");
    }

    public Game getGame() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(gameModelController.getGame());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return (Game) objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Board getLocalBoard() {
        if (gameModelController.getGame() == null) {
            return tempLocalBoard;
        } else {
            return gameModelController.getLocalBoard();
        }
    }

    public Board getRemoteBoard() {
        if (gameModelController.getGame() != null) {
            return gameModelController.getRemoteBoard();
        }
        return null;
    }

    public boolean handleLocalAction(Action action) throws IOException {
        if (!gameModelController.isLocalTurn()) {
            return false;
        }
        if (gameModelController.applyAction(true, action)) {
            connectionHandler.sendMessage("Action:" + Parser.actionToString(action));
            for (InputSubscriber subscriber : subscribers) {
                subscriber.newAction(true);
                Winner winner = gameModelController.checkWinner();
                if (winner != Winner.NONE) {
                    subscriber.gameEnd(winner == Winner.LOCAL_PLAYER);
                }
            }
            return true;
        }
        return false;
    }

    public void handleRemoteAction(Action action) {
        gameModelController.applyAction(false, action);
        for (InputSubscriber subscriber : subscribers) {
            subscriber.newAction(false);
            Winner winner = gameModelController.checkWinner();
            if (winner != Winner.NONE) {
                subscriber.gameEnd(winner == Winner.LOCAL_PLAYER);
            }
        }
    }

    public void setInitialLocalBoard() throws IOException {
        gameModelController.setInitialLocalBoard(tempLocalBoard);
        connectionHandler.sendMessage("InitialGameBoard:" + Parser.boardToString(tempLocalBoard));
        if (gameModelController.gameCanStart()) {
            gameModelController.startGame();
            for (InputSubscriber subscriber : subscribers) {
                subscriber.setInitialOpponentBoard(); //creating objects from model
            }
        }
    }

    public void setInitialRemoteBoard(Board board) {
        gameModelController.setInitialRemoteBoard(board.getWidth(), board.getHeight(), board.getPlaceables());
        if (gameModelController.gameCanStart()) {
            gameModelController.startGame();
            for (InputSubscriber subscriber : subscribers) {
                subscriber.setInitialOpponentBoard(); //creating objects from model
            }
        }
    }

    public void setPlayerOne(boolean playerOne) {
        gameModelController.setPlayerOne(playerOne);
    }

    public void subscribe(InputSubscriber newSubscriber) {
        subscribers.add(newSubscriber);
    }

    public void subscriptionEvent(ConnectionEvent event) {
        for (InputSubscriber subscriber : subscribers) {
            switch (event) {
                case CONNECTED:
                    subscriber.connectionOpen();
                    break;
                case DISCONNECTED:
                    subscriber.connectionClosed();
                    break;
                case MATCHED:
                    subscriber.matched();
                    break;
                default:
                    Log.e("Tag", "Non valid syntax!" + " id: " + event);
            }
        }
    }

    public void unsubscribe(InputSubscriber newSubscriber) {
        subscribers.remove(newSubscriber);
    }

}