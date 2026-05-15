package server;

import client.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.*;

public class StartServer {
  public static void main(String[] args) throws Exception {
    final int PORT = 4026;
    DatabaseHandler database = new DatabaseHandler();
    ServerSocket serverSocket = new ServerSocket(PORT);
    ArrayList<ClientHandler> handlerList = new ArrayList<>();
    Gson gson = new Gson();
    Socket socket;

    System.out.println("Waiting for connection on " + serverSocket.getLocalPort());
    ServerLog.getInstance().newLog("Waiting for connection on " + serverSocket.getLocalPort());

    while((socket = serverSocket.accept()) != null) { // Wait for a connection
      ClientHandler handler = new ClientHandler(socket, gson, handlerList, database.getWarehouseMasterList(), database.getNettoMasterList(), database.getRemaMasterList(), database.getBilkaMasterList());
      handlerList.add(handler);
      Thread thread = new Thread(handler);
      thread.setDaemon(true);
      thread.start();
      System.out.println("Connection Established "+socket.getRemoteSocketAddress());
      ServerLog.getInstance().log("Connection Established "+socket.getRemoteSocketAddress());
    }
  }
}
