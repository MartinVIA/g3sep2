package client.mediator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import client.model.Product;
import client.model.ProductModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {

  private final Socket socket;
  private final PrintWriter writer;
  private final BufferedReader reader;
  private final MessageReceiver receiver;
  private final PropertyChangeSupport support;
  private ProductModel model;
  private final Gson gson = new Gson();

  public Client(String host, int port )  throws IOException {
    socket = new Socket(host, port);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    reader = new BufferedReader(inputStreamReader);
    writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
    receiver = new MessageReceiver(this, reader);
    support = new PropertyChangeSupport(this);
    Thread thread = new Thread(receiver);
    thread.setDaemon(true);
    thread.start();
  }

  public void setModel(ProductModel model){
    this.model = model;
  }

  public void notifyReady(){
    writer.println("Ready!!");
  }

  public void receiveBroadcast( String s ){
    Type type = new TypeToken<HashMap<String, ArrayList<Product>>>(){}.getType();
    HashMap<String, ArrayList<Product>> allLists = gson.fromJson(s, type);
    support.firePropertyChange("update",null,allLists);
  }

  public Socket getSocket()
  {
    return socket;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  public void sendUpdate(String type, ArrayList<Product> list){
    HashMap<String, ArrayList<Product>> message = new HashMap<>();
    message.put(type, list);
    writer.println(gson.toJson(message));
  }

}
