package com.hotel.dao;

import com.hotel.model.Reservation;
import com.hotel.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO implements GenericDAO<Reservation> {

    @Override
    public void create(Reservation r) throws Exception {
        String sql = "INSERT INTO Reservation (guests_count, check_in_out_datetime, client_id, room_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, r.getGuestsCount());
            ps.setTimestamp(2, Timestamp.valueOf(r.getCheckInOutDatetime()));
            ps.setInt(3, r.getClientId());
            ps.setInt(4, r.getRoomId());
            ps.executeUpdate();
            System.out.println("Бронювання додано!");
        }
    }

    @Override
    public Reservation read(int id) throws Exception {
        String sql = "SELECT * FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt("reservation_id"),
                        rs.getInt("guests_count"),
                        rs.getTimestamp("check_in_out_datetime").toLocalDateTime(),
                        rs.getInt("client_id"),
                        rs.getInt("room_id"));
            }
        }
        return null;
    }

    @Override
    public List<Reservation> readAll() throws Exception {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("reservation_id"),
                        rs.getInt("guests_count"),
                        rs.getTimestamp("check_in_out_datetime").toLocalDateTime(),
                        rs.getInt("client_id"),
                        rs.getInt("room_id")));
            }
        }
        return list;
    }

    @Override
    public void update(Reservation r) throws Exception {
        String sql = "UPDATE Reservation SET guests_count = ?, check_in_out_datetime = ?, client_id = ?, room_id = ? WHERE reservation_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, r.getGuestsCount());
            ps.setTimestamp(2, Timestamp.valueOf(r.getCheckInOutDatetime()));
            ps.setInt(3, r.getClientId());
            ps.setInt(4, r.getRoomId());
            ps.setInt(5, r.getReservationId());
            ps.executeUpdate();
            System.out.println("Бронювання оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Reservation WHERE reservation_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Бронювання видалено!");
        }
    }

    @Override
    public List<Reservation> search(String keyword) throws Exception {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservation WHERE guests_count LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt("reservation_id"),
                        rs.getInt("guests_count"),
                        rs.getTimestamp("check_in_out_datetime").toLocalDateTime(),
                        rs.getInt("client_id"),
                        rs.getInt("room_id")));
            }
        }
        return list;
    }
}