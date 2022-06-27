package ru.gretchen.dressshop.controller;

public class CustomerControllerFactory {
    private final static CustomerController instance = new CustomerController();

    public static CustomerController getController() {
        return instance;
    }
}
