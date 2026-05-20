package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProductModel {
  ProductList getWarehouseList();
  ObservableList<Product> getNettoList();
  ObservableList<Product> getBilkaList();
  ObservableList<Product> getRemaList();
  void setAllLists(HashMap<String, ArrayList<Product>> allLists);
  void orderStock(String store, String productName, int quantity);
  void discardProduct(String store, String productName);
}
