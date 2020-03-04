package model;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Card {
    public void registerCard() {
        Card card = new Card();
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO cards(card_number, card_balance) VALUES(?, ?);";

        PreparedStatement preparedStatement = null;

        long cardNumber = card.generateCardNumber();
        double cardBalance = card.generateCardBalance();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, cardNumber);
            preparedStatement.setDouble(2, cardBalance);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public long generateCardNumber() {
        Random random = new Random();
        String cardNumber = "";
        for (int i = 0; i < 16; i++) {
            cardNumber += random.nextInt(10);
        }

        return Long.parseLong(cardNumber);
    }

    public double generateCardBalance() {
        double randomValue = 0 + Math.random( ) * 1000;
        double tempRes = Math.floor(randomValue * 10);
        return tempRes / 10;
    }
}
