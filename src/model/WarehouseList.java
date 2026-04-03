package model;

import javafx.collections.ObservableList;

public class WarehouseList implements ProductModel {

  private ObservableList<Product> warehouseList;

  @Override public void addProduct(String name, String description,
      int quantity, boolean availability)
  {

  }

  @Override public Product getProduct(int index)
  {
    return null;
  }

  @Override public ObservableList<Product> getStoreList()
  {
    return null;
  }

  @Override public ObservableList<Product> getWarehouseList()
  {
    return null;
  }
}
