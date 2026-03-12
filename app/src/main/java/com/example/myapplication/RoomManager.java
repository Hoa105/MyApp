package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private static RoomManager instance;
    private List<Room> rooms = new ArrayList<>();

    private RoomManager() {}

    public static RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }
        return instance;
    }

    // CRUD Operations
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public Room getRoomById(String id) {
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public void updateRoom(String id, Room updatedRoom) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId().equals(id)) {
                rooms.set(i, updatedRoom);
                break;
            }
        }
    }

    public void deleteRoom(String id) {
        rooms.removeIf(room -> room.getId().equals(id));
    }

    // Kiểm tra ID tồn tại
    public boolean isIdExists(String id) {
        return getRoomById(id) != null;
    }

    // Validate dữ liệu
    public static boolean isValidRoom(Room room) {
        return room != null && 
               isNotEmpty(room.getId()) && 
               isNotEmpty(room.getName()) && 
               room.getPrice() > 0;
    }

    private static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
