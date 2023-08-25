package com.abdullahturhan.reservation.enumTypes;

public enum Room {
    ROOM_1(1),
    ROOM_2(2),
    ROOM_3(3),
    ROOM_4(4),
    ROOM_5(5),
    ROOM_6(6),
    ROOM_7(7),
    ROOM_8(8),
    ROOM_9(9),
    ROOM_10(10),
    ROOM_11(11),
    ROOM_12(12),
    ROOM_13(13),
    ROOM_14(14),
    ROOM_15(15),
    ROOM_16(16),
    ROOM_17(17),
    ROOM_18(18),
    ROOM_19(19),
    ROOM_20(20),
    ;

    private final Integer value;


    Room(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
