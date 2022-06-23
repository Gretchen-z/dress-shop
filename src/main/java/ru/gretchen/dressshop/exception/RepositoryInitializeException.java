package ru.gretchen.dressshop.exception;

public class RepositoryInitializeException extends RuntimeException {
    public RepositoryInitializeException(String message) {
        super("Ошибка инициализации репозитория: " + message);
    }
}
