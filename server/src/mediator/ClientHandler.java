package mediator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Inventory;
import model.ServerProduct;

import java.util.List;
import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientHandler implements Runnable {
  private final Socket socket;
  private final Gson gson;
  private final BufferedReader input;
  private final PrintWriter output;
  private List<ClientHandler> handlerList;
  private Inventory inventory;

  public ClientHandler(Socket socket, Gson gson, List<ClientHandler> handlerList, Inventory inventory) throws IOException {
    this.socket = socket;
    this.gson = gson;
    this.handlerList = handlerList;
    this.inventory = inventory;
    OutputStream outputStream = socket.getOutputStream();
    this.output = new PrintWriter(outputStream,true, StandardCharsets.UTF_8);
    InputStream inputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    this.input = new BufferedReader(inputStreamReader);
  }
  @Override
  public void run() {
    try {
      String ready = input.readLine();
      if("Ready!!".equals(ready)){
        output.println(gson.toJson(inventory.getAllLists()));
        System.out.println("Masterlist sent to " + socket.getRemoteSocketAddress());
      }
      String message;
      while ((message = input.readLine()) != null) {
        Type mapType = new TypeToken<HashMap<String, ArrayList<ServerProduct>>>(){}.getType();
        HashMap<String, ArrayList<ServerProduct>> incoming = gson.fromJson(message, mapType);

        if (incoming == null || incoming.isEmpty()) continue;
        String type = incoming.keySet().iterator().next();
        ArrayList<ServerProduct> updatedList = incoming.get(type);
        if (updatedList == null) continue;

        inventory.updateList(type, updatedList);

        String updatedJson = gson.toJson(inventory.getAllLists());

        for (ClientHandler handler : handlerList) {
          handler.output.println(updatedJson);
        }
      }
    } catch (SocketException e) {
      System.out.println("Client disconnected: " + socket.getRemoteSocketAddress());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
