package client.view;

import client.mediator.Client;
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
import client.viewmodel.StoreViewModel;
import javafx.scene.text.Text;

public class StoreViewController {
    @FXML private TableView<Product> productTable;
  @FXML private TableColumn<Product, String> name;
  @FXML private TableColumn<Product, String> description;
  @FXML private TableColumn<Product, Integer> price;
  @FXML private TableColumn<Product, Integer> quantity;
  @FXML private TableColumn<Product, Boolean> perishableness;
  @FXML private Button warehouseButton;
  @FXML private Button backButton;
  @FXML private Button discardButton;
  @FXML private ComboBox<String> storeSelector;
  @FXML private Text nonSelectedWarning;
  private ViewHandler viewHandler;
  private Region root;
  private StoreViewModel model;

  public Region getRoot(){
    return root;
  }

  public void init(ViewHandler viewHandler, StoreViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    name.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
    description.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
    price.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getPrice()).asObject());
    quantity.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getQuantity()).asObject());
    perishableness.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isPerishable()));
    storeSelector.getItems().addAll("Netto", "Rema", "Bilka");
    storeSelector.setValue("Netto");
    productTable.setItems(model.getNettoProducts());

    storeSelector.setOnAction( e -> {
      switch ( storeSelector.getValue() ){
        case "Netto": productTable.setItems(model.getNettoProducts()); break;
        case "Rema": productTable.setItems(model.getRemaProducts()); break;
        case "Bilka": productTable.setItems(model.getBilkaProducts()); break;
      }
    });
  }

  @FXML
  public void initialize(){
    //Leave it blank, it doesn't depend on any other injected data
  }

  public void refresh(){
    productTable.refresh();
  }

  public void handleWarehouseButton(){
    viewHandler.openView("warehouseListView");
  }

  public void handleBackButton(){
    viewHandler.openView("mainView");
  }

    public void handleDiscardButton() {
      Product selected=productTable.getSelectionModel().getSelectedItem();
      if (selected==null){
          nonSelectedWarning.visibleProperty().set(true);
      }
      if (selected==null)
          return;
        selected.setQuantity(0);
        productTable.refresh();
        nonSelectedWarning.visibleProperty().set(false);



  }

}
