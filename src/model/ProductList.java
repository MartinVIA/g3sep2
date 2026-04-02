package model;

import javafx.collections.ObservableList;

public interface ProductList {
  ObservableList<Product> getProductList();
  void addProduct();
}
