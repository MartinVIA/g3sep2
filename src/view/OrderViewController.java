package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import viewmodel.MainViewModel;
import viewmodel.OrderViewModel;

public class OrderViewController {
  public Button cancelButton;
  public Button confirmButton;
  private ViewHandler viewHandler;
  private OrderViewModel model;
  private Region root;
  public Region getRoot(){
    return root;
  }

  public void init(ViewHandler viewHandler, OrderViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

  }

  public void handleCancel() {

    viewHandler.openView("warehouseView");
  }

  public void handleConfirm() {

    viewHandler.openView("warehouseView");
  }
}
