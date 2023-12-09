package ru.meowmure.model;

public class ID {
    private String id;

    public ID(String id) {
        this.id = id;
    }

    public static ID valueOf(String id) {
        return new ID(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
