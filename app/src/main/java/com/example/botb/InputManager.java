package com.example.botb;

import android.util.Log;

import com.example.botb.controller.GameController;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placable.Placeable;
import com.example.botb.model.weapon.WeaponFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
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
        connectionHandler = new ConnectionHandler();
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void handleRemoteAction(String[] actionData) {
        Log.e(TAG, "Got string message!");
        gameController.applyAction(false, stringToAction(actionData));
    }

    public void handleLocalAction(Action action) {
        gameController.applyAction(true, action);
        connectionHandler.sendMessage("Action:"+actionToString(action));
    }
    
    public void setRemoteBoard(String[] data){
        gameController.setRemoteBoard(Integer.parseInt(data[0]),Integer.parseInt(data[1]), stringToBoard(Arrays.copyOfRange(data, 2, data.length)));
    }
    
    public void setLocalBoard(int width, int height, Map<Location,Placeable> map){
        gameController.setLocalBoard(width,height,map);
        connectionHandler.sendMessage("InitialGameBoard:"+width+":"+height+":"+mapToString(map));
    }
    
    //Helper Methods
    private Action stringToAction(String[] actionData) {
        // Format: "LocationX:LocationY:WeaponID"
        return new Action(new Location(Integer.parseInt(actionData[0]), Integer.parseInt(actionData[1])), WeaponFactory.getWeapon(actionData[2]));
    }

    private String actionToString(Action action){
        // Format: "LocationX:LocationY:WeaponID"
        return ""+action.getLocation().getX()+":"+action.getLocation().getY()+":"+action.getWeapon().getWeaponType();
    }
    
    private String mapToString(Map<Location,Placeable> map){
        StringBuilder returnStr = new StringBuilder();
        //Format [1,2,3] -- forEach entry
        for (Map.Entry<Location,Placeable> entry : map.entrySet()) {
            returnStr.append("[").append(entry.getKey().getX()).append("/").append(entry.getKey().getY()).append("/").append(entry.getValue().getPlaceableType());
        }
        return returnStr.toString();
    }

    private HashMap<Location, Placeable> stringToBoard(String[] data){

        HashMap<Location, Placeable> map = new HashMap<Location, Placeable>();
        
        return map;
    }
}