package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import client.viewmodel.OrderViewModel;

public class OrderViewController {
  @FXML private Button cancelButton;
  @FXML private Button confirmButton;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextField perishableField;
  @FXML private TextField quantityField;
  @FXML private TextField stockField;
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
    nameField.textProperty().bindBidirectional(model.getNameProperty());
    descriptionField.textProperty().bindBidirectional(model.getDescriptionProperty());
    priceField.textProperty().bindBidirectional(model.getPriceProperty());
    perishableField.textProperty().bindBidirectional(model.getPerishableProperty());
    quantityField.textProperty().bindBidirectional(model.getQuantityProperty());


  }

  public void handleCancel() {
    stockField.clear();
    if("warehouse".equals(model.getTargetStore()))
      viewHandler.openView("warehouseView");
    else
      viewHandler.openView("warehouseListView");
  }

  public void handleConfirm() throws NumberFormatException {
    try{
      if(Integer.parseInt((stockField.getText())) > 0){
        model.orderStock(Integer.parseInt((stockField.getText())));
        stockField.clear();
        if("warehouse".equals(model.getTargetStore()))
          viewHandler.openView("warehouseView");
        else
          viewHandler.openView("warehouseListView");
      }
      else
        System.out.println("Order quantity invalid");
    }
    catch (NumberFormatException e) {
      System.out.println("Order format invalid");
    }
  }

  public void reset(){
    stockField.clear();
  }
}
