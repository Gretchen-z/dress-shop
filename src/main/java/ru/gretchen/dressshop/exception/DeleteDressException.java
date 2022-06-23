package ru.gretchen.dressshop.exception;

public class DeleteDressException extends RuntimeException {
    public DeleteDressException(String message) {
        super("Не удалось удалить платье: " + message);
    }
}
