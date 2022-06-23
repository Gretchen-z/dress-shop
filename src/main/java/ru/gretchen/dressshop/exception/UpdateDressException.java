package ru.gretchen.dressshop.exception;

public class UpdateDressException extends RuntimeException {
    public UpdateDressException(String message) {
        super("Не удалось обновить платье: " + message);
    }
}
