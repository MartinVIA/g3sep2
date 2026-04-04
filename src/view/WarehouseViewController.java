package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import model.Product;
import viewmodel.WarehouseViewModel;

public class WarehouseViewController {
  @FXML private TableView<Product> productTable;
  @FXML private TableColumn<Product, String> name;
  @FXML private TableColumn<Product, String> description;
  @FXML private TableColumn<Product, Integer> price;
  @FXML private TableColumn<Product, Integer> quantity;
  @FXML private TableColumn<Product, Boolean> perishableness;
  @FXML private Button orderButton;
  private ViewHandler viewHandler;
  private Region root;
  private WarehouseViewModel model;
  public void init(ViewHandler viewHandler, WarehouseViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    description.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
    price.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getPrice()).asObject());
    quantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
    perishableness.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isPerishable()));
    productTable.setItems(model.getModel().getWarehouseList().getProductList());
  }
  @FXML
  public void initialize(){
    //Leave it blank, it doesn't depend on any other injected data
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
  }
}
