package ru.gretchen.dressshop.exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(Long id) {
        super("Магазина c id " + id + " не существует");
    }
}
