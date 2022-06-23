package ru.gretchen.dressshop.model.Enumeration;

public enum Colour {
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

    Colour(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
