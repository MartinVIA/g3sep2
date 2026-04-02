package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private MainViewController mainVC;
  private WarehouseViewController warehouseVC;
  private StoreViewController storeVC;
  private OrderViewController orderVC;
  private WarehouseListViewController warehouseListVC;

  public ViewHandler(){
    this.viewModelFactory = new ViewModelFactory();
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage) {

  }

  public void openView(){
    
  }

}
