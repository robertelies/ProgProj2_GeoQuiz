package com.example.myapplication;

public class shared_player {

    private String name;

    private int punt;

    // constructor
    public shared_player(String name, int punt) {
        this.name = name;
        this.punt = punt;
    }

    // getter

    public int getPunt() {
        return punt;
    }

    public String getName() {
        return name;
    }


}
