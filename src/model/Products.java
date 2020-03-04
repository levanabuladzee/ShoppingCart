package model;

import util.DBConnection;

import java.sql.*;

public class Products {
    public void getProducts() {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "SELECT * FROM products;";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");
                System.out.println(productId + "\t" + productName + "\t" + productPrice + "$");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (statement != null) statement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void addProduct(String productName, double productPrice) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "INSERT INTO products(product_name, product_price) VALUES(?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, productPrice);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void updateProduct(String productName, double productPrice, int productId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "UPDATE products SET product_name = ?, product_price = ? WHERE product_id = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, productPrice);
            preparedStatement.setInt(3, productId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void removeProduct(int productId) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "DELETE FROM products WHERE product_id = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            int i = preparedStatement.executeUpdate();
            System.out.println(i + " records deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void increasePrice(int productId, double productPrice) {
        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.connectDB();
        String sql = "CALL increase_price(?, ?)";

        CallableStatement callableStatement = null;

        try {
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, productId);
            callableStatement.setDouble(2, productPrice);

            callableStatement.executeUpdate();
            System.out.println("records updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (callableStatement != null) callableStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
