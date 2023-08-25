package com.abdullahturhan.reservation.enumTypes;

public enum RoomType {
    SINGLE("Single"),
    DOUBLE("Double"),
    SUITE("Suite");

    private final String value;
    RoomType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
