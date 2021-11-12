package Model;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {
    SINGLE (1),
    DOUBLE (2);

    //https://codingexplained.com/coding/java/enum-to-integer-and-integer-to-enum
    private int value;

    private static Map<Integer,RoomType>map = new HashMap<>();

    private RoomType(int value) {
        this.value = value;
    }

    static {
        for (RoomType roomType : RoomType.values()) {
            map.put(roomType.value, roomType);
        }
    }

    public static RoomType valueOf(int roomType) {
        return (RoomType) map.get(roomType);
    }

    public int getValue() {
        return value;
    }
}