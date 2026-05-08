package client.mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageReceiver implements Runnable {

  private BufferedReader reader;
  private Client client;
  public MessageReceiver(Client client, BufferedReader in){
    this.client = client;
    reader = in;
  }
  @Override public void run() {
    try {
      listen();
    }catch (IOException e){
      e.printStackTrace();
    }
  }
  public void listen() throws IOException {
    String message;
    while ((message = reader.readLine()) != null) {
      client.receiveBroadcast(message);
    }
  }

}
