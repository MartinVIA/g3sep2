package model;

import javafx.collections.ObservableList;

public interface ProductModel {

  void addProduct( String name, String description, int quantity, boolean availability);
  Product getProduct( int index );

  ObservableList<Product> getStoreList();
  ObservableList<Product> getWarehouseList();
}
