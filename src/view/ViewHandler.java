package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler {
  private Stage primaryStage;
  private Scene currentScene;
  private final ViewModelFactory viewModelFactory;
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
    this.primaryStage = primaryStage;
    openView("mainView");
  }
  public void openView(String id){
    Region root = null;
    switch(id) {
      case "mainView":
        root = loadMainView("/view/MainView.fxml");
        break;
      case "warehouseView":
        root = loadWarehouseView("/view/WarehouseView.fxml");
        break;
      case "storeView":
        root = loadStoreView("/view/StoreView.fxml");
        break;
    }
    currentScene.setRoot(root);
    primaryStage.setTitle("Vinyl manager thingy");
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadMainView(String fxml) {
    Region root;
    if (mainVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        mainVC = loader.getController();
        mainVC.init(this,viewModelFactory.getMainVM(),root);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }else {
      mainVC.reset();
    }
    return mainVC.getRoot();
  }

  private Region loadWarehouseView(String fxml) {
    Region root;
    if (warehouseVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        warehouseVC = loader.getController();
        warehouseVC.init(this,viewModelFactory.getWarehouseVM(),root);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }else {
      warehouseVC.reset();
    }
    return warehouseVC.getRoot();
  }

  private Region loadStoreView(String fxml)
  {
    Region root;
    if (storeVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        storeVC = loader.getController();
        storeVC.init(this,viewModelFactory.getStoreVM(),root);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }else {
      storeVC.reset();
    }
    return storeVC.getRoot();
  }


}
