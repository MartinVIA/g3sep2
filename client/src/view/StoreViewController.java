package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ProductViewModel;
import viewmodel.StoreViewModel;
import javafx.scene.text.Text;

public class StoreViewController {
  @FXML private TableView<ProductViewModel> productTable;
  @FXML private TableColumn<ProductViewModel, String>  name;
  @FXML private TableColumn<ProductViewModel, String>  description;
  @FXML private TableColumn<ProductViewModel, Integer> price;
  @FXML private TableColumn<ProductViewModel, Integer> quantity;
  @FXML private TableColumn<ProductViewModel, Boolean> perishableness;
  @FXML private Button warehouseButton;
  @FXML private Button backButton;
  @FXML private Button discardButton;
  @FXML private ComboBox<String> storeSelector;
  @FXML private Text nonSelectedWarning;

  private ViewHandler viewHandler;
  private Region root;
  private StoreViewModel model;

  public void init(ViewHandler viewHandler, StoreViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    name.setCellValueFactory(cell -> cell.getValue().nameProperty());
    description.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
    price.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());
    quantity.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
    perishableness.setCellValueFactory(cell -> cell.getValue().perishableProperty());

    storeSelector.getItems().addAll("Netto", "Rema", "Bilka");
    storeSelector.setValue("Netto");
    productTable.setItems(model.getNettoProducts());

    storeSelector.setOnAction(e -> {
      nonSelectedWarning.setVisible(false);
      switch (storeSelector.getValue()) {
        case "Netto": productTable.setItems(model.getNettoProducts()); break;
        case "Rema":  productTable.setItems(model.getRemaProducts());  break;
        case "Bilka": productTable.setItems(model.getBilkaProducts()); break;
      }
    });
  }

  @FXML
  public void initialize(){
    // blank
  }

  public Region getRoot(){ return root; }

  public void refresh(){ productTable.refresh(); }

  public void handleWarehouseButton(){
    nonSelectedWarning.setVisible(false);
    viewHandler.setSelectedStore(storeSelector.getValue().toLowerCase());
    viewHandler.openView("warehouseListView");
  }

  public void handleBackButton(){
    nonSelectedWarning.setVisible(false);
    viewHandler.openView("mainView");
  }

  public void handleDiscardButton(){
    int index = productTable.getSelectionModel().getSelectedIndex();
    if (index < 0) {
      nonSelectedWarning.visibleProperty().set(true);
      return;
    }
    model.discardProduct(storeSelector.getValue().toLowerCase(), index);
    productTable.refresh();
    nonSelectedWarning.visibleProperty().set(false);
  }

}
