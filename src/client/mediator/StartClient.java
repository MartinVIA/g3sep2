package client.mediator;

import javafx.application.Application;
import javafx.stage.Stage;
import client.view.ViewHandler;

import java.io.IOException;

public class StartClient extends Application
{
  public static void main(String[] args){
    launch(args);
  System.out.println("heehee :3c");
  }

  @Override public void start(Stage primaryStage) throws IOException {
    Client client = new Client("localhost", 4026);
    ViewHandler viewHandler = new ViewHandler(client);
    viewHandler.start(primaryStage);
  }
}
