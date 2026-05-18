package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import client.model.Product;
import client.viewmodel.WarehouseListViewModel;

public class WarehouseListViewController {
  @FXML private TableView<Product> productTable;
  @FXML private TableColumn<Product, String> name;
  @FXML private TableColumn<Product, String> description;
  @FXML private TableColumn<Product, Integer> price;
  @FXML private TableColumn<Product, Integer> quantity;
  @FXML private TableColumn<Product, Boolean> perishableness;
  @FXML private Button backButton;
  @FXML private Button orderButton;
  private ViewHandler viewHandler;
  private Region root;
  private WarehouseListViewModel model;

  public void init(ViewHandler viewHandler, WarehouseListViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    description.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
    price.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getPrice()).asObject());
    quantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
    perishableness.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isPerishable()));
    productTable.setItems(model.getProducts());
//    storeSelector.setValue("Netto");
  }
  @FXML
  public void initialize(){
    //Leave it blank, it doesn't depend on any other injected data
  }

  public Region getRoot(){
    return root;
  }

  public void refresh(){
    productTable.refresh();
  }

  public void handleBackButton(){
    viewHandler.openView("storeView");
  }

  public void handleOrderButton(){
    Product selected = productTable.getSelectionModel().getSelectedItem();
    if (selected == null)
      return;
    String store = viewHandler.getSelectedStore();
    viewHandler.getViewModelFactory().getOrderVM().setSelectedProduct(selected);
    viewHandler.getViewModelFactory().getOrderVM().setTargetStore(store);
    viewHandler.openView("orderView");
  }

}