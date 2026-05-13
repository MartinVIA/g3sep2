package server;

import client.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
  private ArrayList<ClientHandler> handlerList;
  private ArrayList<Product> masterList;
  private ArrayList<Product> warehouseMasterList = new ArrayList<>();
  private ArrayList<Product> nettoMasterList = new ArrayList<>();
  private ArrayList<Product> remaMasterList = new ArrayList<>();
  private ArrayList<Product> bilkaMasterList = new ArrayList<>();

  public ClientHandler(Socket socket, Gson gson, ArrayList<ClientHandler>handlerList, ArrayList<Product> masterList) throws IOException {
    this.socket = socket;
    this.gson = gson;
    this.handlerList = handlerList;
    this.masterList = masterList;
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
        HashMap<String, ArrayList<Product>> allLists = new HashMap<>();
        allLists.put("warehouse", warehouseMasterList);
        allLists.put("netto", nettoMasterList);
        allLists.put("rema", remaMasterList);
        allLists.put("bilka", bilkaMasterList);
        output.println(gson.toJson(allLists));
        System.out.println("Masterlist sent to "+socket.getRemoteSocketAddress());
      }
      String message;
      while ((message = input.readLine()) != null) {
        Type mapType = new TypeToken<HashMap<String, ArrayList<Product>>>(){}.getType();
        HashMap<String, ArrayList<Product>> incoming = gson.fromJson(message, mapType);

        String type = incoming.keySet().iterator().next();
        ArrayList<Product> updatedList = incoming.get(type);

        if ("warehouse".equals(type)){ warehouseMasterList.clear();
          warehouseMasterList.addAll(updatedList); }
        else if ("netto".equals(type)){ nettoMasterList.clear();
          nettoMasterList.addAll(updatedList); }
        else if ("rema".equals(type)){ remaMasterList.clear();
          remaMasterList.addAll(updatedList); }
        else if ("bilka". equals(type)){ bilkaMasterList.clear();
          bilkaMasterList.addAll(updatedList);}

        HashMap<String, ArrayList<Product>> allLists = new HashMap<>();
        allLists.put("warehouse", warehouseMasterList);
        allLists.put("netto", nettoMasterList);
        allLists.put("rema", remaMasterList);
        allLists.put("bilka", bilkaMasterList);

        String updatedJson = gson.toJson(allLists);
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
