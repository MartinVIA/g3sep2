package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import viewmodel.MainViewModel;

public class MainViewController {
  private ViewHandler viewHandler;
  private Region root;
  @FXML private Button warehouseButton;
  @FXML private Button storeButton;
  private MainViewModel model;
  public void init(ViewHandler viewHandler, MainViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public Region getRoot() {
    return root;
  }

  public void handleWarehouseButton(){
    viewHandler.openView("warehouseView");
  }

  public void handleStoreButton(){
    viewHandler.openView("storeView");
  }

  public void reset()
  {
    //ts makes the back button in warehouseView not return to main view, as root cannot be null or sum bs like that

  }
}
