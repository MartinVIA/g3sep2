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
  public void init(ViewHandler viewHandler, MainViewModel mainVM, Region root) {

  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
  }

  public void handleWarehouseButton(){
    viewHandler.openView("warehouseView");
  }

  public void handleStoreButton(){
    viewHandler.openView("storeView");
  }
}
