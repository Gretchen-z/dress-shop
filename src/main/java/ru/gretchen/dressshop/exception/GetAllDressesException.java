package ru.gretchen.dressshop.exception;

public class GetAllDressesException extends RuntimeException {
    public GetAllDressesException(String message) {
        super("Не удалось получить список всех платьев: " + message);
    }
}
