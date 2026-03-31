package com.hotel.dao;

import com.hotel.model.RoomRating;
import com.hotel.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRatingDAO implements GenericDAO<RoomRating> {

    @Override
    public void create(RoomRating rr) throws Exception {
        String sql = "INSERT INTO RoomRating (popularity_score, room_id) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setFloat(1, rr.getPopularityScore());
            ps.setInt(2, rr.getRoomId());
            ps.executeUpdate();
            System.out.println("Рейтинг додано!");
        }
    }

    @Override
    public RoomRating read(int id) throws Exception {
        String sql = "SELECT * FROM RoomRating WHERE rating_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new RoomRating(rs.getInt("rating_id"),
                        rs.getFloat("popularity_score"),
                        rs.getInt("room_id"));
            }
        }
        return null;
    }

    @Override
    public List<RoomRating> readAll() throws Exception {
        List<RoomRating> list = new ArrayList<>();
        String sql = "SELECT * FROM RoomRating";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new RoomRating(rs.getInt("rating_id"),
                        rs.getFloat("popularity_score"),
                        rs.getInt("room_id")));
            }
        }
        return list;
    }

    @Override
    public void update(RoomRating rr) throws Exception {
        String sql = "UPDATE RoomRating SET popularity_score = ?, room_id = ? WHERE rating_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setFloat(1, rr.getPopularityScore());
            ps.setInt(2, rr.getRoomId());
            ps.setInt(3, rr.getRatingId());
            ps.executeUpdate();
            System.out.println("Рейтинг оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM RoomRating WHERE rating_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Рейтинг видалено!");
        }
    }

    @Override
    public List<RoomRating> search(String keyword) throws Exception {
        List<RoomRating> list = new ArrayList<>();
        String sql = "SELECT * FROM RoomRating WHERE popularity_score LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomRating(rs.getInt("rating_id"),
                        rs.getFloat("popularity_score"),
                        rs.getInt("room_id")));
            }
        }
        return list;
    }
}