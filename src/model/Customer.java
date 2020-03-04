package model;

import util.DBConnection;

import java.sql.*;

public class Customer {
    public void registerCustomer(String customerName, String customerSurname, int customerAge, int cardId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO customers(customer_name, customer_surname, customer_age, card_id) VALUES(?, ?, ?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerSurname);
            preparedStatement.setInt(3, customerAge);
            preparedStatement.setInt(4, cardId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void checkBalance(int userId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM customers NATURAL JOIN cards WHERE customer_id = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("customer_name");
                long cardNumber = resultSet.getLong("card_number");
                double cardBalance = resultSet.getDouble("card_balance");
                System.out.println(name + "\t" + cardNumber + "\t" + cardBalance + "$");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
