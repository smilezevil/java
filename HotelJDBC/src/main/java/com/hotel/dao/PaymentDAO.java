package com.hotel.dao;

import com.hotel.model.Payment;
import com.hotel.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PaymentDAO implements GenericDAO<Payment> {

    @Override
    public void create(Payment p) throws Exception {
        String sql = "INSERT INTO Payment (total_amount, reservation_id) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setBigDecimal(1, p.getTotalAmount());
            ps.setInt(2, p.getReservationId());
            ps.executeUpdate();
            System.out.println("Оплату додано!");
        }
    }

    @Override
    public Payment read(int id) throws Exception {
        String sql = "SELECT * FROM Payment WHERE payment_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Payment(rs.getInt("payment_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getInt("reservation_id"));
            }
        }
        return null;
    }

    @Override
    public List<Payment> readAll() throws Exception {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new Payment(rs.getInt("payment_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getInt("reservation_id")));
            }
        }
        return list;
    }

    @Override
    public void update(Payment p) throws Exception {
        String sql = "UPDATE Payment SET total_amount = ?, reservation_id = ? WHERE payment_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setBigDecimal(1, p.getTotalAmount());
            ps.setInt(2, p.getReservationId());
            ps.setInt(3, p.getPaymentId());
            ps.executeUpdate();
            System.out.println("Оплату оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Payment WHERE payment_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Оплату видалено!");
        }
    }

    @Override
    public List<Payment> search(String keyword) throws Exception {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment WHERE total_amount LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Payment(rs.getInt("payment_id"),
                        rs.getBigDecimal("total_amount"),
                        rs.getInt("reservation_id")));
            }
        }
        return list;
    }
}