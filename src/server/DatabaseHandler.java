package server;

import client.model.Product;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
  private ArrayList<Product> warehouseMasterList = new ArrayList<>();
  private ArrayList<Product> nettoMasterList = new ArrayList<>();
  private ArrayList<Product> remaMasterList = new ArrayList<>();
  private ArrayList<Product> bilkaMasterList = new ArrayList<>();
  private Statement statement;
  private final String username = "postgres";
  private final String password = "Buvan@DBS";
  private final String url = "jdbc:postgresql://localhost:5432/postgres";
  private final String warehouseQuery = """
      SELECT name, description, price, quantity, perishable
      FROM product;
      """;
  private final String nettoQuery = """
        SELECT product.name, product.description, product.price, product.perishable, store_quantity AS quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Netto'
        """;
  private final String remaQuery = """
        SELECT product.name, product.description, product.price, product.perishable, store_quantity AS quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Rema'
        """;
  private final String bilkaQuery = """
        SELECT product.name, product.description, product.price, product.perishable, store_quantity AS quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Bilka'
        """;
  public DatabaseHandler() throws Exception{
    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection(url, username, password);
    System.out.println("Connection Established successfully");
    ServerLog.getInstance().log("Connection Established successfully");
    this.statement = con.createStatement();
    ResultSet warehouseResult = statement.executeQuery(warehouseQuery);
    while (warehouseResult.next()) {
      Product product = new Product(
          warehouseResult.getString("name"),
          warehouseResult.getString("description"),
          warehouseResult.getInt("price"),
          warehouseResult.getInt("quantity"),
          warehouseResult.getBoolean("perishable"));
      warehouseMasterList.add(product);
    }
    ServerLog.getInstance().log("Warehouse MasterList created");

    ResultSet nettoResult = statement.executeQuery(nettoQuery);
    while (nettoResult.next()) {
      Product product = new Product(
          nettoResult.getString("name"),
          nettoResult.getString("description"),
          nettoResult.getInt("price"),
          nettoResult.getInt("quantity"),
          nettoResult.getBoolean("perishable"));
      nettoMasterList.add(product);
    }
    ServerLog.getInstance().log("Netto MasterList created");

    ResultSet remaResult = statement.executeQuery(remaQuery);
    while (remaResult.next()) {
      Product product = new Product(
          remaResult.getString("name"),
          remaResult.getString("description"),
          remaResult.getInt("price"),
          remaResult.getInt("quantity"),
          remaResult.getBoolean("perishable"));
      remaMasterList.add(product);
    }
    ServerLog.getInstance().log("Rema MasterList created");

    ResultSet bilkaResult = statement.executeQuery(bilkaQuery);
    while (bilkaResult.next()) {
      Product product = new Product(
          bilkaResult.getString("name"),
          bilkaResult.getString("description"),
          bilkaResult.getInt("price"),
          bilkaResult.getInt("quantity"),
          bilkaResult.getBoolean("perishable"));
      bilkaMasterList.add(product);
    }
    ServerLog.getInstance().log("Bilka MasterList created");

  }
  public ArrayList<Product> getWarehouseMasterList() {
    return warehouseMasterList;
  }
  public ArrayList<Product> getNettoMasterList() {
    return nettoMasterList;
  }
  public ArrayList<Product> getRemaMasterList() {
    return remaMasterList;
  }
  public ArrayList<Product> getBilkaMasterList() {
    return bilkaMasterList;
  }
}
