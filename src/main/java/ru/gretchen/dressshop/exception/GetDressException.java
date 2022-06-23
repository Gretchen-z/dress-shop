package ru.gretchen.dressshop.exception;

public class GetDressException extends RuntimeException{
    public GetDressException(String message) {
        super("Платье не найдено: " + message);
    }
}
