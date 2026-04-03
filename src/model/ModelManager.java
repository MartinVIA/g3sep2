package model;

import javafx.collections.ObservableList;

public class ModelManager implements ProductModel {
  private ProductList storeList;
  private ProductList warehouseList;
  public ModelManager(){
    storeList.addProduct();
    storeList.addProduct();
    warehouseList.addProduct();
    warehouseList.addProduct();
    warehouseList.addProduct();
  }

  @Override public void addProduct(String name, String description,
      int quantity, boolean availability)
  {

  }

  @Override public Product getProduct(int index)
  {
    return null;
  }

  public ObservableList<Product> getStoreList() {
    return storeList.getProductList();
  }
  public ObservableList<Product> getWarehouseList() {
    return warehouseList.getProductList();
  }
}
