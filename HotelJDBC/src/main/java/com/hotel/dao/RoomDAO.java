package com.hotel.dao;

import com.hotel.model.Room;
import com.hotel.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomDAO implements GenericDAO<Room> {

    @Override
    public void create(Room room) throws Exception {
        String sql = "INSERT INTO Room (room_number, capacity, type_id, Roomcol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getCapacity());
            ps.setInt(3, room.getTypeId());
            ps.setString(4, room.getRoomCol());
            ps.executeUpdate();
            System.out.println("Номер додано!");
        }
    }

    @Override
    public Room read(int id) throws Exception {
        String sql = "SELECT * FROM Room WHERE room_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Room(rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getInt("type_id"),
                        rs.getString("Roomcol"));
            }
        }
        return null;
    }

    @Override
    public List<Room> readAll() throws Exception {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM Room";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new Room(rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getInt("type_id"),
                        rs.getString("Roomcol")));
            }
        }
        return list;
    }

    @Override
    public void update(Room room) throws Exception {
        String sql = "UPDATE Room SET room_number = ?, capacity = ?, type_id = ?, Roomcol = ? WHERE room_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getCapacity());
            ps.setInt(3, room.getTypeId());
            ps.setString(4, room.getRoomCol());
            ps.setInt(5, room.getRoomId());
            ps.executeUpdate();
            System.out.println("Номер оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Room WHERE room_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Номер видалено!");
        }
    }

    @Override
    public List<Room> search(String keyword) throws Exception {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE room_number LIKE ? OR Roomcol LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getInt("capacity"),
                        rs.getInt("type_id"),
                        rs.getString("Roomcol")));
            }
        }
        return list;
    }
}