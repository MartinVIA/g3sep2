package server;

import client.model.Product;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
  private final Socket socket;
  private final Gson gson;
  private final BufferedReader input;
  private final PrintWriter output;
  private ArrayList<ClientHandler> handlerList;
  private ArrayList<Product> masterList;

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
        output.println(gson.toJson(masterList));
        System.out.println("Masterlist sent to "+socket.getRemoteSocketAddress());
      }
      String message;
      while ((message = input.readLine()) != null) {

        String updatedJson = gson.toJson(masterList);
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
