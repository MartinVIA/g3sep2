package dao;

import db.DatabaseConnection;
import model.ServerProduct;
import utils.ServerLog;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class StoreDao implements ProductDao {
  private final String QUERY = """
            SELECT product.name, product.description, product.price,
                   product.perishable, store_quantity AS quantity
            FROM store_stock
            JOIN product ON store_stock.product_id = product.product_id
            JOIN store   ON store_stock.store_id   = store.store_id
            WHERE store_name = ?
            """;
  private final String UPDATE_QUERY = """
        UPDATE store_stock
        SET store_quantity = ?
        WHERE product_id = (SELECT product_id FROM product WHERE name = ?)
          AND store_id   = (SELECT store_id   FROM store   WHERE store_name = ?)
        """;
  private final String storeName;
  public StoreDao(String storeName){
    this.storeName = storeName;
  }
  @Override public ArrayList<ServerProduct> findAllProducts() {
    ArrayList<ServerProduct> serverProducts = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(QUERY)) {
      statement.setString(1, storeName);
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          serverProducts.add(mapRow(rs));
        }
      }
      ServerLog.getInstance().log(storeName + " list loaded: " + serverProducts.size() + " serverProducts");

    }
    catch (SQLException | FileNotFoundException e) {
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
          stmt.setString(3, storeName);
          stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        ServerLog.getInstance().log(storeName + " DB updated on shutdown.");

      } catch (SQLException e) {
        conn.rollback();
        System.out.println("Failed to update " + storeName + " serverProducts, rolling back.");
      }

    } catch (SQLException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  private ServerProduct mapRow(ResultSet rs) throws SQLException {
    return new ServerProduct(
        rs.getString("name"),
        rs.getString("description"),
        rs.getInt("price"),
        rs.getInt("quantity"),
        rs.getBoolean("perishable")
    );
  }
}
