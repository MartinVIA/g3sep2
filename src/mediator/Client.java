package mediator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import model.Product;
import model.ProductModel;
import view.ViewHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Client {

  private final Socket socket;
  private PrintWriter writer;
  private final BufferedReader reader;
  private final MessageReceiver receiver;
  private ProductModel model;
  private final Gson gson = new Gson();
  private ViewHandler viewHandler;

  public Client(String host, int port )  throws IOException {
    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
        StandardCharsets.UTF_8);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
    receiver = new MessageReceiver(this, reader);
    Thread thread = new Thread(receiver);
    thread.setDaemon(true);
    thread.start();
  }

  public void notifyReady(){
    writer.println("Ready!!");
  }

  public void receiveBroadcast( String s ){
    Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
    ArrayList<Product> updatedList = gson.fromJson(s, listType);
    Platform.runLater(() -> model.getWarehouseList().getProductList().setAll(updatedList));
  }

 // public void addPropertyChangeListener(PropertyChangeListener listener) {
   // support.addPropertyChangeListener(listener);
  //}

  //public void removePropertyChangeListener(PropertyChangeListener listener) {
   // support.removePropertyChangeListener(listener);
  //}

  public Socket getSocket()
  {
    return socket;
  }

}
