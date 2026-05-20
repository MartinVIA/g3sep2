package dao;

import db.DatabaseConnection;
import model.ServerProduct;
import utils.ServerLog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class WarehouseDao implements ProductDao{
  private final String QUERY = """
      SELECT name, description, price, quantity, perishable
      FROM serverProduct;
      """;
  private final String UPDATE_QUERY = """
        UPDATE serverProduct
        SET quantity = ?
        WHERE name = ?
        """;

  public ArrayList<ServerProduct> findAllProducts()  {
    ArrayList<ServerProduct> serverProducts = new ArrayList<>();
    try (Connection con = DatabaseConnection.getConnection();
        PreparedStatement statement = con.prepareStatement(QUERY);
        ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        serverProducts.add(mapRow(resultSet));
      }
      ServerLog.getInstance().log("Warehouse list loaded: " + serverProducts.size() + " serverProducts");

    } catch (SQLException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return serverProducts;
  }

  @Override public void saveAllProducts(ArrayList<ServerProduct> serverProducts) {
    try (Connection conn = DatabaseConnection.getConnection()) {
      conn.setAutoCommit(false);

      try (PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
        for (ServerProduct serverProduct : serverProducts) {
          stmt.setInt(1, serverProduct.getQuantity());
          stmt.setString(2, serverProduct.getName());
          stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        ServerLog.getInstance().log("Warehouse updated on shutdown.");

      } catch (SQLException e) {
        conn.rollback();
        System.out.println("Failed to update warehouse serverProducts, changes rolled back.");
      }
    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ServerProduct mapRow(ResultSet resultSet) throws SQLException {
    return new ServerProduct(
        resultSet.getString("name"),
        resultSet.getString("description"),
        resultSet.getInt("price"),
        resultSet.getInt("quantity"),
        resultSet.getBoolean("perishable")
    );
  }
}
