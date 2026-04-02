package model;

import javafx.collections.ObservableList;

public interface ProductModel {
  ObservableList<Product> getStoreList();
  ObservableList<Product> getWarehouseList();
}
