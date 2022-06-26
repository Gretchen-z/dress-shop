package ru.gretchen.dressshop.exception;

public class GetShopException extends RuntimeException {
    public GetShopException(String message) {
        super("Магазин не найден: " + message);
    }
}
