package ru.gretchen.dressshop.exception;

public class DressNotFoundException extends RuntimeException {
    public DressNotFoundException(Long id) {
        super("Позиции c id " + id + " не существует");
    }
}
