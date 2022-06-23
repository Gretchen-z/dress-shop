package ru.gretchen.dressshop.exception;

public class CreateDressException extends RuntimeException {
    public CreateDressException(String message) {
        super("Не удалось создать новое платье: " + message);
    }
}
