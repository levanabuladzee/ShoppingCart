package model;

import util.DBConnection;

import java.sql.*;

public class ShoppingCart {
    public void addProduct(int customerId, int productId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO shopping_cart(customer_id, product_id) VALUES(?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void checkCart(int customerId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM customers NATURAL JOIN (SELECT * FROM shopping_cart NATURAL JOIN products) AS cart_products WHERE customer_id = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int shoppingCartId = resultSet.getInt("shopping_cart_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");

                System.out.println(shoppingCartId + "\t" + productName + "\t" + productPrice + "$");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public double getCartPrice(int customerId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();

        String sql = "SELECT * FROM customers NATURAL JOIN (SELECT * FROM shopping_cart NATURAL JOIN products) AS cart_products WHERE customer_id = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double finalPrice = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                finalPrice += resultSet.getDouble("product_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return finalPrice;
    }

    public void buyProduct(double cardBalance, int customerId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();

        String sql = "UPDATE cards SET card_balance = ? WHERE card_id = (SELECT card_id FROM customers WHERE customer_id = ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, cardBalance);
            preparedStatement.setInt(2, customerId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records updated");
            clearCart(customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public double changedBalance(int userId, double price) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM customers NATURAL JOIN cards WHERE customer_id = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double cardBalance = 0;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cardBalance = resultSet.getDouble("card_balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return cardBalance - price;
    }

    public void clearCart(int customerId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "DELETE FROM shopping_cart WHERE customer_id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
