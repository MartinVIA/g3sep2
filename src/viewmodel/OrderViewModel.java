package viewmodel;

import javafx.beans.property.*;
import model.ProductModel;

public class OrderViewModel {
  private ProductModel model;
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();
  private StringProperty price = new SimpleStringProperty();
  private StringProperty quantity = new SimpleStringProperty();
  private StringProperty perishable = new SimpleStringProperty();
  private int index;

  public OrderViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }
  public void setProductIndex(int index){
    this.index = index;
    name.set(model.getWarehouseList().getProduct(index).getName());
    description.set(model.getWarehouseList().getProduct(index).getDescription());
    price.set(String.valueOf(model.getWarehouseList().getProduct(index).getPrice()));
    quantity.set(String.valueOf(model.getWarehouseList().getProduct(index).getQuantity()));
    perishable.set(String.valueOf(model.getWarehouseList().getProduct(index).isPerishable()));
  }
  public StringProperty getNameProperty(){
    return name;
  }
  public StringProperty getDescriptionProperty(){
    return description;
  }
  public StringProperty getPriceProperty(){
    return price;
  }
  public StringProperty getQuantityProperty(){
    return quantity;
  }
  public StringProperty getPerishableProperty(){
    return perishable;
  }
  public void orderStock(int stock){
    int newStock = stock+model.getWarehouseList().getProduct(index).getQuantity();
    model.getWarehouseList().getProduct(index).setQuantity(newStock);
    System.out.println(model.getWarehouseList().getProduct(index).getQuantity());
  }
}
