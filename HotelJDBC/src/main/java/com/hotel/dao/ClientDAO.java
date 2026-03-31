package com.hotel.dao;

import com.hotel.model.Client;
import com.hotel.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements GenericDAO<Client> {

    @Override
    public void create(Client client) throws Exception {
        String sql = "INSERT INTO Client (full_name, passport_data) VALUES (?, ?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, client.getFullName());
            ps.setString(2, client.getPassportData());
            ps.executeUpdate();
            System.out.println("Клієнта додано!");
        }
    }

    @Override
    public Client read(int id) throws Exception {
        String sql = "SELECT * FROM Client WHERE client_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(rs.getInt("client_id"),
                        rs.getString("full_name"),
                        rs.getString("passport_data"));
            }
        }
        return null;
    }

    @Override
    public List<Client> readAll() throws Exception {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("Колонки: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " | ");
            }
            System.out.println();
            while (rs.next()) {
                list.add(new Client(rs.getInt("client_id"),
                        rs.getString("full_name"),
                        rs.getString("passport_data")));
            }
        }
        return list;
    }

    @Override
    public void update(Client client) throws Exception {
        String sql = "UPDATE Client SET full_name = ?, passport_data = ? WHERE client_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, client.getFullName());
            ps.setString(2, client.getPassportData());
            ps.setInt(3, client.getClientId());
            ps.executeUpdate();
            System.out.println("Клієнта оновлено!");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Client WHERE client_id = ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Клієнта видалено!");
        }
    }

    @Override
    public List<Client> search(String keyword) throws Exception {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM Client WHERE full_name LIKE ? OR passport_data LIKE ?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getInt("client_id"),
                        rs.getString("full_name"),
                        rs.getString("passport_data")));
            }
        }
        return list;
    }
}