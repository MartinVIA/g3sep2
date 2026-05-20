package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ProductViewModel;
import viewmodel.WarehouseListViewModel;

public class WarehouseListViewController {
  @FXML private TableView<ProductViewModel> productTable;
  @FXML private TableColumn<ProductViewModel, String>  name;
  @FXML private TableColumn<ProductViewModel, String>  description;
  @FXML private TableColumn<ProductViewModel, Integer> price;
  @FXML private TableColumn<ProductViewModel, Integer> quantity;
  @FXML private TableColumn<ProductViewModel, Boolean> perishableness;
  @FXML private Button backButton;
  @FXML private Button orderButton;

  private ViewHandler viewHandler;
  private Region root;
  private WarehouseListViewModel model;

  public void init(ViewHandler viewHandler, WarehouseListViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    name.setCellValueFactory(cell -> cell.getValue().nameProperty());
    description.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
    price.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
    quantity.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
    perishableness.setCellValueFactory(cell -> cell.getValue().perishableProperty());

    productTable.setItems(model.getProducts());
  }

  @FXML
  public void initialize(){
    // blank
  }

  public Region getRoot(){ return root; }

  public void refresh(){ productTable.refresh(); }

  public void handleBackButton(){
    viewHandler.openView("storeView");
  }

  public void handleOrderButton(){
    int index = productTable.getSelectionModel().getSelectedIndex();
    if (index < 0) return;
    model.selectProductForOrder(index, viewHandler.getSelectedStore());
    viewHandler.openView("orderView");
  }

}