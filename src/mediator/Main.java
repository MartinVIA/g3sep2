package mediator;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelManager;
import model.ProductModel;
import view.ViewHandler;

import java.io.IOException;

public class Main extends Application
{
  public static void main(String[] args){
    launch(args);
  System.out.println("heehee :3c");
  }

  @Override public void start(Stage primaryStage) throws IOException {
    // Client client = new Client("localhost", 4026);
    ViewHandler viewHandler = new ViewHandler();
    viewHandler.start(primaryStage);
  }
}
