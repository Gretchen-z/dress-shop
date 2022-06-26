package ru.gretchen.dressshop.controller;

public class DressControllerFactory {
    private final static DressController instance = new DressController();

    public static DressController getController() {
        return instance;
    }
}
