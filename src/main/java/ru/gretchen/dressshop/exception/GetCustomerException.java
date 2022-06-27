package ru.gretchen.dressshop.exception;

public class GetCustomerException extends RuntimeException {
    public GetCustomerException(String message) {
        super("Покупатель не найден: " + message);
    }
}
