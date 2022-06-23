package ru.gretchen.dressshop.controller;

public class ControllerFactory {
    private final static Controller instance = new Controller();

    public static Controller getController() {
        return instance;
    }
}
