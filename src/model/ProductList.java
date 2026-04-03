package model;

import javafx.collections.ObservableList;

public interface ProductList {
  ObservableList<Product> getProductList();
  void addProduct(String name, String description,int price, int quantity, boolean perishable);
  Product getProduct(int index);
}
