package server;

import client.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.*;

public class StartServer {
  public static void main(String[] args) throws IOException, Exception {
    String username = "postgres";
    String password = "123456789";
    String url = "jdbc:postgresql://localhost:5432/postgres";
    //TODO Put the queries in here buvi boy
    String warehouseQuery = """
        SELECT name, description, price, isPerishable, warehouse.quantity
        FROM product
        LEFT JOIN warehouse ON product.product_id = warehouse.product_id
        """;
    String nettoQuery = """
        SELECT product.name, product.description, product.price, product.isPerishable, quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Netto'
        """;
    String remaQuery = """
        SELECT product.name, product.description, product.price, product.isPerishable, quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Rema'
        """;
    String bilkaQuery = """
        SELECT product.name, product.description, product.price, product.isPerishable, quantity
        FROM store_stock
        JOIN product ON store_stock.product_id = product.product_id
        JOIN store ON store_stock.store_id = store.store_id
        WHERE store_name = 'Bilka'
        """;
    final int PORT = 4026;
    ArrayList<Product> warehouseMasterList = new ArrayList<>();
    ArrayList<Product> nettoMasterList = new ArrayList<>();
    ArrayList<Product> remaMasterList = new ArrayList<>();
    ArrayList<Product> bilkaMasterList = new ArrayList<>();

    ServerLog logger = ServerLog.getInstance();
    ServerSocket serverSocket = new ServerSocket(PORT);
    Gson gson = new Gson();

    Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection(url, username, password);
    System.out.println("Connection Established successfully");
    Statement statement = con.createStatement();

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
    logger.log("Warehouse MasterList created");

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
    logger.log("Netto MasterList created");

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
    logger.log("Rema MasterList created");

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
    logger.log("Bilka MasterList created");

    System.out.println("Waiting for connection on " + serverSocket.getLocalPort());
    logger.newLog("Waiting for connection on " + serverSocket.getLocalPort());

    ArrayList<ClientHandler> handlerList = new ArrayList<>();
    Socket socket;

    while((socket = serverSocket.accept()) != null) { // Wait for a connection
      ClientHandler handler = new ClientHandler(socket, gson, handlerList, warehouseMasterList, nettoMasterList, remaMasterList, bilkaMasterList);
      handlerList.add(handler);
      Thread thread = new Thread(handler);
      thread.setDaemon(true);
      thread.start();
      System.out.println("Connection Established "+socket.getRemoteSocketAddress());
      logger.log("Connection Established "+socket.getRemoteSocketAddress());
    }
  }
}
