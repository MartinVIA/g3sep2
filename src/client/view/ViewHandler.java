package client.view;

import client.mediator.Client;
import client.model.Product;
import client.model.ProductModel;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import client.viewmodel.ViewModelFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewHandler {
  private Stage primaryStage;
  private Scene currentScene;
  private final ViewModelFactory viewModelFactory;
  private MainViewController mainVC;
  private WarehouseViewController warehouseVC;
  private StoreViewController storeVC;
  private OrderViewController orderVC;
  private WarehouseListViewController warehouseListVC;
  private final Client client;

  public ViewHandler(Client client){
    this.viewModelFactory = new ViewModelFactory();
    currentScene = new Scene(new Region());
    this.client = client;
    this.client.addPropertyChangeListener(event -> {
      if (event.getPropertyName().equals("update")) {
        HashMap<String, ArrayList<Product>> allLists  = ( HashMap<String, ArrayList<Product>>) event.getNewValue();
        Platform.runLater(() -> {
          viewModelFactory.getWarehouseListVM().getModel().setAllLists(allLists);

        });
      }
    });
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("mainView");
  }
  public ViewModelFactory getViewModelFactory(){
    return viewModelFactory;
  }
  public void openView(String id){
    Region root = null;
    switch(id) {
      case "mainView":
        root = loadMainView("/client/view/MainView.fxml");
        break;
      case "warehouseView":
        root = loadWarehouseView("/client/view/WarehouseView.fxml");
        break;
      case "storeView":
        root = loadStoreView("/client/view/StoreView.fxml");
        break;
      case "warehouseListView":
        root = loadWarehouseListView("/client/view/WarehouseListView.fxml");
        break;
      case "orderView":
        root = loadOrderView("/client/view/OrderView.fxml");
        break;
    }
    currentScene.setRoot(root);
    primaryStage.setTitle("Warehouse management system");
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadOrderView(String fxml) {
    Region root;
    if (orderVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        orderVC = loader.getController();
        orderVC.init(this,viewModelFactory.getOrderVM(),root);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }

    return orderVC.getRoot();
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
      } catch(Exception e) {
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
    }else{
      warehouseVC.refresh();
    }

    return warehouseVC.getRoot();
  }

  private Region loadStoreView(String fxml) {
    Region root;
    if (storeVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        storeVC = loader.getController();
        storeVC.init(this,viewModelFactory.getStoreVM(),root);
      } catch(Exception e) {
        e.printStackTrace();
      }
    }else{
      storeVC.refresh();
    }
    return storeVC.getRoot();
  }
  private Region loadWarehouseListView(String fxml)
  {
    Region root;
    if (warehouseListVC == null) {
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        root = loader.load();
        warehouseListVC = loader.getController();
        warehouseListVC.init(this,viewModelFactory.getWarehouseListVM(),root);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
    return warehouseListVC.getRoot();
  }

}
