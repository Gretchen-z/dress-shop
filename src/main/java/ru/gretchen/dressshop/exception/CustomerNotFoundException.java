package ru.gretchen.dressshop.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Покупателя c id " + id + " не существует");
    }
}
