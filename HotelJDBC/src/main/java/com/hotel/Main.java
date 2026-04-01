package com.hotel;

import com.hotel.dao.*;
import com.hotel.model.*;
import com.hotel.util.AuthService;
import com.hotel.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting the hotel system...\n");

        ClientDAO clientDAO = new ClientDAO();
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        RoomDAO roomDAO = new RoomDAO();
        ReservationDAO reservationDAO = new ReservationDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        RoomRatingDAO roomRatingDAO = new RoomRatingDAO();

        try {

            System.out.println("Total clients: " + clientDAO.readAll().size());
            System.out.println("Total room types: " + roomTypeDAO.readAll().size());
            System.out.println("Total rooms: " + roomDAO.readAll().size());
            System.out.println("Total reservations: " + reservationDAO.readAll().size());
            System.out.println("Total payments: " + paymentDAO.readAll().size());
            System.out.println("Total room ratings: " + roomRatingDAO.readAll().size());

            System.out.println("\n-----------------------------------\n");


            System.out.println("Searching clients with 'Мельник':");
            List<Client> clients = clientDAO.search("Мельник");
            for (Client c : clients) {
                System.out.println(c);
            }

            System.out.println("\nSearching room types with 'Стандарт':");
            List<RoomType> roomTypes = roomTypeDAO.search("Стандарт");
            for (RoomType rt : roomTypes) {
                System.out.println(rt);
            }

            System.out.println("\nSearching rooms with number '101':");
            List<Room> rooms = roomDAO.search("101");
            for (Room r : rooms) {
                System.out.println(r);
            }

            System.out.println("\nSearching payments with '1000':");
            List<Payment> payments = paymentDAO.search("1000");
            for (Payment p : payments) {
                System.out.println(p);
            }

            System.out.println("\nSearching room with popularity score '4.5':");
            List<RoomRating> ratings = roomRatingDAO.search("4.5");
            for (RoomRating rr : ratings) {
                System.out.println(rr);
            }

            System.out.println("\n-----------------------------------\n");


            boolean login = AuthService.login("admin", "admin123");
            System.out.println("Login: " + (login ? "SUCCESS" : "FAILED"));

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (Client)");

            Client newClient = new Client(0, "Бенедикт Камбербетч", "AA123456");
            clientDAO.create(newClient);

            Client foundClient = null;
            for (Client c : clientDAO.readAll()) {
                if ("Бенедикт Камбербетч".equals(c.getFullName())) {
                    foundClient = c;
                    break;
                }
            }

            if (foundClient != null) {
                System.out.println("Created: " + foundClient);

                foundClient.setFullName("Бенедикт Камбербетч");
                foundClient.setPassportData("BB654321");
                clientDAO.update(foundClient);

                System.out.println("Updated: " + clientDAO.read(foundClient.getClientId()));

                clientDAO.delete(foundClient.getClientId());
                System.out.println("Client Бенедикт Камбербетч was deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (RoomType)");

            RoomType newRoomType = new RoomType(0, "Мегасуперультралюкс", new BigDecimal("1500.00"));
            roomTypeDAO.create(newRoomType);

            RoomType foundRoomType = null;
            for (RoomType rt : roomTypeDAO.readAll()) {
                if ("Мегасуперультралюкс".equals(rt.getTypeName())) {
                    foundRoomType = rt;
                    break;
                }
            }

            if (foundRoomType != null) {
                System.out.println("Created: " + foundRoomType);

                foundRoomType.setTypeName("Мегасуперультралюкс");
                foundRoomType.setPricePerNight(new BigDecimal("180000.00"));
                roomTypeDAO.update(foundRoomType);

                System.out.println("Updated: " + roomTypeDAO.read(foundRoomType.getTypeId()));

                roomTypeDAO.delete(foundRoomType.getTypeId());
                System.out.println("Room type Мегасуперультралюкс was deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (Room)");

            Room newRoom = new Room(0, "666", 2, 1, "рожевий");
            roomDAO.create(newRoom);

            Room foundRoom = null;
            for (Room r : roomDAO.readAll()) {
                if ("666".equals(r.getRoomNumber())) {
                    foundRoom = r;
                    break;
                }
            }

            if (foundRoom != null) {
                System.out.println("Created: " + foundRoom);

                foundRoom.setCapacity(3);
                foundRoom.setRoomCol("галубой");
                roomDAO.update(foundRoom);

                System.out.println("Updated: " + roomDAO.read(foundRoom.getRoomId()));

                roomDAO.delete(foundRoom.getRoomId());
                System.out.println("Room with number 666 was deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (Reservation)");

            Reservation newReservation = new Reservation(
                    0,
                    2,
                    LocalDateTime.now().plusDays(1),
                    LocalDateTime.now().plusDays(3),
                    1,
                    1
            );
            reservationDAO.create(newReservation);

            Reservation foundReservation = null;
            for (Reservation r : reservationDAO.readAll()) {
                if (r.getClientId() == 1 && r.getRoomId() == 1 && r.getGuestsCount() == 2) {
                    foundReservation = r;
                    break;
                }
            }

            if (foundReservation != null) {
                System.out.println("Created: " + foundReservation);

                foundReservation.setGuestsCount(4);
                reservationDAO.update(foundReservation);

                System.out.println("Updated: " + reservationDAO.read(foundReservation.getReservationId()));

                reservationDAO.delete(foundReservation.getReservationId());
                System.out.println("Reservation deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (Payment)");

            Payment newPayment = new Payment(0, new BigDecimal("2500.00"), 1);
            paymentDAO.create(newPayment);

            Payment foundPayment = null;
            for (Payment p : paymentDAO.readAll()) {
                if (p.getTotalAmount().compareTo(new BigDecimal("2500.00")) == 0 && p.getReservationId() == 1) {
                    foundPayment = p;
                    break;
                }
            }

            if (foundPayment != null) {
                System.out.println("Created: " + foundPayment);

                foundPayment.setTotalAmount(new BigDecimal("3000.00"));
                paymentDAO.update(foundPayment);

                System.out.println("Updated: " + paymentDAO.read(foundPayment.getPaymentId()));

                paymentDAO.delete(foundPayment.getPaymentId());
                System.out.println("Payment deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("CRUD test (RoomRating)");

            RoomRating newRating = new RoomRating(0, 4.5f, 1);
            roomRatingDAO.create(newRating);

            RoomRating foundRating = null;
            for (RoomRating rr : roomRatingDAO.readAll()) {
                if (rr.getRoomId() == 1 && rr.getPopularityScore() == 4.5f) {
                    foundRating = rr;
                    break;
                }
            }

            if (foundRating != null) {
                System.out.println("Created: " + foundRating);

                foundRating.setPopularityScore(4.2f);
                roomRatingDAO.update(foundRating);

                System.out.println("Updated: " + roomRatingDAO.read(foundRating.getRatingId()));

                roomRatingDAO.delete(foundRating.getRatingId());
                System.out.println("Room rating deleted");
            }

            System.out.println("\n-----------------------------------\n");


            System.out.println("Metadata (Room):");

            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Room");
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.println(meta.getColumnName(i) + " - " + meta.getColumnTypeName(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
            System.out.println("\nSystem finished.");
        }
    }
}