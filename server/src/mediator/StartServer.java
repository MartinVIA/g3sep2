package mediator;

import com.google.gson.Gson;
import dao.ProductDao;
import dao.StoreDao;
import dao.WarehouseDao;
import model.Inventory;
import utils.ServerLog;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StartServer {
  public static void main(String[] args) throws Exception {
    final int PORT = 4026;
    ProductDao warehouseDao = new WarehouseDao();
    ProductDao nettoDao = new StoreDao("Netto");
    ProductDao remaDao = new StoreDao("Rema");
    ProductDao bilkaDao = new StoreDao("Bilka");
    ServerLog logger = ServerLog.getInstance();
    ServerSocket serverSocket = new ServerSocket(PORT);
    Gson gson = new Gson();
    List<ClientHandler> handlerList = new CopyOnWriteArrayList<>();
    Socket socket;

    Inventory inventory = new Inventory(
        warehouseDao.findAllProducts(),
        nettoDao.findAllProducts(),
        remaDao.findAllProducts(),
        bilkaDao.findAllProducts()
    );
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      warehouseDao.saveAllProducts(inventory.getWarehouseList());
      nettoDao.saveAllProducts(inventory.getNettoList());
      remaDao.saveAllProducts(inventory.getRemaList());
      bilkaDao.saveAllProducts(inventory.getBilkaList());

      System.out.println("Database updated on shutdown.");
    }));

    System.out.println("Waiting for connection on " + serverSocket.getLocalPort());
    logger.newLog("Waiting for connection on " + serverSocket.getLocalPort());


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
