package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class WarehouseList implements ProductList {

  private ObservableList<Product> warehouseList;

  public WarehouseList(){
    warehouseList = FXCollections.observableArrayList();
  }

  public void addProduct(String name, String description, int price, int quantity, boolean perishable) {
    warehouseList.add(new Product(name,description,price,quantity,perishable));
  }
  public void setProductList(ArrayList<Product> productList){
    warehouseList.setAll(productList);
  }

  public void discardProduct(int index){
    warehouseList.remove(index);
  }
  public Product getProduct(int index) {
    return warehouseList.get(index);
  }
  public ObservableList<Product> getProductList() {
    return warehouseList;
  }
}
