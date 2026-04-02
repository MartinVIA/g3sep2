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


  public ObservableList<Product> getStoreList() {
    return storeList.getProductList();
  }
  public ObservableList<Product> getWarehouseList() {
    return warehouseList.getProductList();
  }
}
