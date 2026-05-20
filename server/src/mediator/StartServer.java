package mediator;

import com.google.gson.Gson;
import model.Inventory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StartServer {
  public static void main(String[] args) throws IOException, Exception {

    DatabaseHandler db = new DatabaseHandler();
    final int PORT = 4026;
    Inventory inventory = new Inventory(
        db.getWarehouseMasterList(),
        db.getNettoMasterList(),
        db.getRemaMasterList(),
        db.getBilkaMasterList()
    );

    ServerLog logger = ServerLog.getInstance();
    ServerSocket serverSocket = new ServerSocket(PORT);
    Gson gson = new Gson();

    System.out.println("Waiting for connection on " + serverSocket.getLocalPort());
    logger.newLog("Waiting for connection on " + serverSocket.getLocalPort());

    List<ClientHandler> handlerList = new CopyOnWriteArrayList<>();
    Socket socket;

    while((socket = serverSocket.accept()) != null) { // Wait for a connection
      ClientHandler handler = new ClientHandler(socket, gson, handlerList, inventory);
      handlerList.add(handler);
      Thread thread = new Thread(handler);
      thread.setDaemon(true);
      thread.start();
      System.out.println("Connection Established "+socket.getRemoteSocketAddress());
      logger.log("Connection Established "+socket.getRemoteSocketAddress());
    }
  }
}
