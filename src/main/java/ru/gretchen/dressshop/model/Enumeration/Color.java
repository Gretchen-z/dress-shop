package ru.gretchen.dressshop.model.Enumeration;

public enum Color {
    WHITE("WHITE"),
    GREY("GREY"),
    BLACK("BLACK"),
    BROWN("BROWN"),
    RED("RED"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    ORANGE("ORANGE"),
    GREEN("GREEN"),
    VIOLET("VIOLET"),
    PINK("PINK");

    private final String color;

    Color(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
