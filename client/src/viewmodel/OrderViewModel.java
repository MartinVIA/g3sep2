package viewmodel;

import model.Product;
import javafx.beans.property.*;
import model.ProductModel;

public class OrderViewModel {
  private ProductModel model;
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();
  private StringProperty price = new SimpleStringProperty();
  private StringProperty quantity = new SimpleStringProperty();
  private StringProperty perishable = new SimpleStringProperty();
  private String targetStore = "warehouse";
  private String selectedProductName;

  public OrderViewModel(ProductModel model) {
    this.model = model;
  }

  public void setTargetStore(String store)
  {
    this.targetStore = store;
  }

  public String getTargetStore()
  {
    return targetStore;
  }

  public void setSelectedIndex(int index, String store) {
    this.targetStore = store;
    Product p = model.getWarehouseList().getProductList().get(index);
    this.selectedProductName = p.getName();
    name.set(p.getName());
    description.set(p.getDescription());
    price.set(String.valueOf(p.getPrice()));
    quantity.set(String.valueOf(p.getQuantity()));
    perishable.set(String.valueOf(p.isPerishable()));
  }

  public ProductModel getModel() {
    return model;
  }

  public StringProperty getNameProperty() {
    return name;
  }

  public StringProperty getDescriptionProperty() {
    return description;
  }

  public StringProperty getPriceProperty() {
    return price;
  }

  public StringProperty getQuantityProperty() {
    return quantity;
  }

  public StringProperty getPerishableProperty() {
    return perishable;
  }

  public void orderStock(int stock) {
    model.orderStock(targetStore, selectedProductName, stock);
  }

}
