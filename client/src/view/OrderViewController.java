package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.OrderViewModel;
import javafx.scene.text.Text;

public class OrderViewController {
  @FXML private Button cancelButton;
  @FXML private Button confirmButton;
  @FXML private TextField nameField;
  @FXML private TextField descriptionField;
  @FXML private TextField priceField;
  @FXML private TextField perishableField;
  @FXML private TextField quantityField;
  @FXML private TextField stockField;
  @FXML private Text errorLabel;
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
    errorLabel.setVisible(false);
    errorLabel.setManaged(false);
    if("warehouse".equals(model.getTargetStore()))
      viewHandler.openView("warehouseView");
    else
      viewHandler.openView("warehouseListView");
  }

  public void handleConfirm() throws NumberFormatException {
    try {
      int stock = Integer.parseInt(stockField.getText());
      if (stock <= 0) {
        errorLabel.setText("Quantity must be greater than 0.");
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
        return;
      }
      if (!model.isStockValid(stock)) {
        errorLabel.setText("Not enough stock available in warehouse.");
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
        return;
      }
      errorLabel.setVisible(false);
      model.orderStock(stock);
      stockField.clear();
      if ("warehouse".equals(model.getTargetStore()))
        viewHandler.openView("warehouseView");
      else
        viewHandler.openView("warehouseListView");
    }
    catch (NumberFormatException e) {
      errorLabel.setText("Please enter a valid number.");
      errorLabel.setVisible(true);
      errorLabel.setManaged(true);
    }
  }

  public void reset(){
    stockField.clear();
    errorLabel.setVisible(false);
    errorLabel.setManaged(false);
  }
}
