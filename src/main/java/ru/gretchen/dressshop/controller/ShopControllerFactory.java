package ru.gretchen.dressshop.controller;

public class ShopControllerFactory {
    private final static ShopController instance = new ShopController();

    public static ShopController getController() {
        return instance;
    }
}
