package com.example.botb;

import android.util.Log;
import com.example.botb.controller.GameController;
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

    private GameController gameController;

    private List<InputSubscriber> subscribers = new ArrayList<InputSubscriber>();

    private Board tempLocalBoard;

    public static InputManager getInstance() {
        return instance;
    }

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        gameController = new GameController();
        //creating the ConnectionActivity
        connectionHandler = new ConnectionHandler(this);

        tempLocalBoard = new Board(10, 8);
        tempLocalBoard.addPlaceable(new Shield(), 0, 0);
        tempLocalBoard.addPlaceable(new Shield(), 1, 1);
        tempLocalBoard.addPlaceable(new Shield(), 2, 2);
        tempLocalBoard.addPlaceable(new Nexus(), 3, 3);
    }

    public void connectToServer() {
        connectionHandler.connect();
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
        if (gameController.getGame() != null) {
            return gameController.getRemoteBoard();
        }
        return null;
    }

    public boolean handleLocalAction(Action action) throws IOException {
        if (!gameController.isLocalTurn()) {
            return false;
        }
        if (gameController.applyAction(true, action)) {
            connectionHandler.sendMessage("Action:" + Parser.actionToString(action));
            for (InputSubscriber subscriber : subscribers) {
                subscriber.newAction(true);
                Winner winner = gameController.checkWinner();
                if (winner != Winner.NONE) {
                    subscriber.gameEnd(winner == Winner.LOCAL_PLAYER);
                }
            }
            return true;
        }
        return false;
    }

    public void handleRemoteAction(Action action) {
        gameController.applyAction(false, action);
        for (InputSubscriber subscriber : subscribers) {
            subscriber.newAction(false);
            Winner winner = gameController.checkWinner();
            if (winner != Winner.NONE) {
                subscriber.gameEnd(winner == Winner.LOCAL_PLAYER);
            }
        }
    }

    public void setInitialLocalBoard() throws IOException {
        gameController.setInitialLocalBoard(tempLocalBoard);
        connectionHandler.sendMessage("InitialGameBoard:" + Parser.boardToString(tempLocalBoard));
        if (gameController.gameCanStart()) {
            gameController.startGame();
            for (InputSubscriber subscriber : subscribers) {
                subscriber.setInitialOpponentBoard(); //creating objects from model
            }
        }
    }

    public void setInitialRemoteBoard(Board board) {
        gameController.setInitialRemoteBoard(board.getWidth(), board.getHeight(), board.getPlaceables());
        if (gameController.gameCanStart()) {
            gameController.startGame();
            for (InputSubscriber subscriber : subscribers) {
                subscriber.setInitialOpponentBoard(); //creating objects from model
            }
        }
    }

    public void setPlayerOne(boolean playerOne) {
        gameController.setPlayerOne(playerOne);
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