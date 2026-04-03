package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.Product;
import viewmodel.WarehouseViewModel;

public class WarehouseViewController {
  @FXML private TableView<Product> productTable;
  @FXML private TableColumn<Product, String> name;
  @FXML private TableColumn<Product, Integer> price;
  @FXML private TableColumn<Product, Integer> quantity;
  @FXML private TableColumn<Product, Boolean> perishableness;
  private ViewHandler viewHandler;
  private Region root;
  private WarehouseViewModel model;
  public void init(ViewHandler viewHandler, WarehouseViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    price.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getPrice()));
    quantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()));
    perishableness.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isPerishable()));
    productTable.setItems(model.getModel().getWarehouseList());
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
  }
}
