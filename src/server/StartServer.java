package server;

import client.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class StartServer {
  public static void main(String[] args) throws IOException, Exception {

    DatabaseHandler db = new DatabaseHandler();
    final int PORT = 4026;
    ArrayList<Product> warehouseMasterList = db.getWarehouseMasterList();
    ArrayList<Product> nettoMasterList = db.getNettoMasterList();
    ArrayList<Product> remaMasterList = db.getRemaMasterList();
    ArrayList<Product> bilkaMasterList = db.getBilkaMasterList();

    ServerLog logger = ServerLog.getInstance();
    ServerSocket serverSocket = new ServerSocket(PORT);
    Gson gson = new Gson();

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
