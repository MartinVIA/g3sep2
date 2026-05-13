package client.model;

import javafx.collections.ObservableList;

public interface ProductModel {
  ProductList getStoreList();
  ProductList getWarehouseList();
  ObservableList<Product> getNettoList();
  ObservableList<Product> getBilkaList();
  ObservableList<Product> getRemaList();
}
