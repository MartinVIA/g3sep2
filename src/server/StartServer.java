package server;

import client.model.Product;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class StartServer {
  public static void main(String[] args) throws IOException {
    final int PORT = 4026;
    ServerLog logger=new ServerLog();

    ServerSocket serverSocket = new ServerSocket(PORT);
    System.out.println("Waiting for connection on " + serverSocket.getLocalPort());
    logger.newLog("Waiting for connection on " + serverSocket.getLocalPort());

      Gson gson = new Gson();
    ArrayList<ClientHandler> handlerList = new ArrayList<>();
    ArrayList<Product> masterList = new ArrayList<>();
    Socket socket;

    while((socket = serverSocket.accept()) != null) { // Wait for a connection
      ClientHandler handler = new ClientHandler(socket,gson,handlerList, masterList);
      handlerList.add(handler);
      Thread thread = new Thread(handler);
      thread.setDaemon(true);
      thread.start();
      System.out.println("Connection Established "+socket.getRemoteSocketAddress());
      logger.log("Connection Established "+socket.getRemoteSocketAddress());
    }
  }
}
