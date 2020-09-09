package com.amiralbatti;

public abstract class Player {
    private String name;
    public Map playerMap;

    public Player(String name){
        this.name = name;
        playerMap = new Map();
    }

    public  String getName(){
        return this.name;
    }
}
