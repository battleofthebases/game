package com.example.botb;

import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.WeaponFactory;

import java.lang.reflect.Array;

public class InputManager {

    private final String TAG = "InputManger";
    private static final InputManager instance = new InputManager();
    private GameController gameController;
    private ConnectionHandler connectionHandler;

    //private constructor to avoid client applications to use constructor
    private InputManager() {
        gameController = new GameController();
        //creating the connection
        connectionHandler = new ConnectionHandler();
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void handleExternalAction(String[] actionData) {
        Log.e(TAG, "Got string message!");
        gameController.applyAction(false, stringToAction(actionData));
    }

    public void handleLocalAction(Action action) {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage(actionToString(action));
    }

    private Action stringToAction(String[] actionData) {
        // Format: "LocationX:LocationY:WeaponID"
        return new Action(new Location(Integer.parseInt(actionData[0]), Integer.parseInt(actionData[1])), WeaponFactory.getWeapon(Integer.parseInt(actionData[2])));
    }

    private String actionToString(Action action){
        // Format: "LocationX:LocationY:WeaponID"
        return ""+action.getLocation().getX()+":"+action.getLocation().getY()+":"+action.getWeapon().getWeaponType();
    }
}