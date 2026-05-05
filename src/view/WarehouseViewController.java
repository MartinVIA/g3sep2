package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ProductViewModel;
import viewmodel.WarehouseViewModel;

public class WarehouseViewController {
  @FXML private TableView<ProductViewModel> productTable;
  @FXML private TableColumn<ProductViewModel, String> name;
  @FXML private TableColumn<ProductViewModel, String> description;
  @FXML private TableColumn<ProductViewModel, Integer> price;
  @FXML private TableColumn<ProductViewModel, Integer> quantity;
  @FXML private TableColumn<ProductViewModel, Boolean> perishableness;
  @FXML private Button orderButton;
  private ViewHandler viewHandler;
  private Region root;
  private WarehouseViewModel model;
  public void init(ViewHandler viewHandler, WarehouseViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> cell.getValue().nameProperty());
    description.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
    price.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
    quantity.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
    perishableness.setCellValueFactory(cell -> cell.getValue().isPerishableProperty());
    productTable.setItems(model.getWarehouseList());
  }
  @FXML
  public void initialize(){
    //Leave it blank, it doesn't depend on any other injected data
  }

  public Region getRoot() {
    return root;
  }

  public void refresh()
  {
    model.reload();
  }
}
