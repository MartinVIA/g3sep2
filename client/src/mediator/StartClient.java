package mediator;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;
import model.ModelManager;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class StartClient extends Application
{
  public static void main(String[] args){
    launch(args);
  System.out.println("heehee :3c");
  }

  @Override public void start(Stage primaryStage) throws IOException {
    Client client = new Client("localhost", 4026);
    ModelManager model = new ModelManager();
    client.setModel(model);
    model.setClient(client);

    ViewModelFactory factory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(factory);
    viewHandler.start(primaryStage);
    client.notifyReady();
  }
}
