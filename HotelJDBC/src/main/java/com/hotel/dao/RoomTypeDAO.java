package com.hotel.dao;

import com.hotel.model.RoomType;
import com.hotel.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomTypeDAO implements GenericDAO<RoomType> {

    @Override
    public void create(RoomType rt) throws Exception {
        String sql = "INSERT INTO RoomType (type_name, price_per_night) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, rt.getTypeName());
            ps.setBigDecimal(2, rt.getPricePerNight());
            ps.executeUpdate();
            System.out.println("Тип номера додано!");
        }
    }

    @Override
    public RoomType read(int id) throws Exception {
        String sql = "SELECT * FROM RoomType WHERE type_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new RoomType(rs.getInt("type_id"),
                        rs.getString("type_name"),
                        rs.getBigDecimal("price_per_night"));
            }
        }
        return null;
    }

    @Override
    public List<RoomType> readAll() throws Exception {
        List<RoomType> list = new ArrayList<>();
        String sql = "SELECT * FROM RoomType";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new RoomType(rs.getInt("type_id"),
                        rs.getString("type_name"),
                        rs.getBigDecimal("price_per_night")));
            }
        }
        return list;
    }

    @Override
    public void update(RoomType rt) throws Exception {
        String sql = "UPDATE RoomType SET type_name = ?, price_per_night = ? WHERE type_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, rt.getTypeName());
            ps.setBigDecimal(2, rt.getPricePerNight());
            ps.setInt(3, rt.getTypeId());
            ps.executeUpdate();
            System.out.println("Тип номера оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM RoomType WHERE type_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Тип номера видалено!");
        }
    }

    @Override
    public List<RoomType> search(String keyword) throws Exception {
        List<RoomType> list = new ArrayList<>();
        String sql = "SELECT * FROM RoomType WHERE type_name LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomType(rs.getInt("type_id"),
                        rs.getString("type_name"),
                        rs.getBigDecimal("price_per_night")));
            }
        }
        return list;
    }
}