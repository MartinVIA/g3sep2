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
import viewmodel.StoreViewModel;

public class StoreViewController {
  @FXML private TableView<ProductViewModel> productTable;
  @FXML private TableColumn<ProductViewModel, String> name;
  @FXML private TableColumn<ProductViewModel, String> description;
  @FXML private TableColumn<ProductViewModel, Integer> price;
  @FXML private TableColumn<ProductViewModel, Integer> quantity;
  @FXML private TableColumn<ProductViewModel, Boolean> perishableness;
  @FXML private Button warehouseButton;
  private ViewHandler viewHandler;
  private Region root;
  private StoreViewModel model;

  public Region getRoot(){
    return root;
  }
  
  public void init(ViewHandler viewHandler, StoreViewModel model, Region root)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> cell.getValue().nameProperty());
    description.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
    price.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
    quantity.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
    perishableness.setCellValueFactory(cell -> cell.getValue().isPerishableProperty());
    productTable.setItems(model.getStoreList());

  }

  @FXML
  public void initialize(){
    //Leave it blank, it doesn't depend on any other injected data
  }

  public void refresh()
  {
    model.reload();
  }

  public void handleWarehouseButton(){
    viewHandler.openView("warehouseListView");
  }
}
