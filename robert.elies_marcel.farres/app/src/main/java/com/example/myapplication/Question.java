package com.example.myapplication;

class Question {
    private String name;
    private boolean result;
    private int id;

    // constructor
    public Question(String name, boolean result, int id) {
        this.name = name;
        this.id = id;
        this.result = result;
    }

    // getter
    public String getName() { return name; }
    public int getId() { return id; }
    public boolean getResult() { return result; }

}
