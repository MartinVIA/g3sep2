package model;

import javafx.collections.ObservableList;

public interface ProductList {
  ObservableList<Product> getProductList();
  void addProduct(int PID,String name, String description,int price, int quantity, boolean perishable);
  void discardProduct(int index);
  Product getProduct(int index);

}
