package client.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelManager implements ProductModel {

  private ProductList nettoList;
  private ProductList remaList;
  private ProductList bilkaList;
  private ProductList warehouseList;
  public ModelManager(){
  }

  public ObservableList<Product> getBilkaList() {
    return bilkaList.getProductList();
  }
  public ObservableList<Product> getNettoList() {
    return nettoList.getProductList();
  }
  public ObservableList<Product> getRemaList() {
    return remaList.getProductList();
  }

  public void setAllLists(HashMap<String, ArrayList<Product>> allLists) {
    nettoList.setProductList(allLists.get("netto"));
    remaList.setProductList(allLists.get("rema"));
    bilkaList.setProductList(allLists.get("bilka"));
    warehouseList.setProductList(allLists.get("warehouse"));
    }

  public ProductList getWarehouseList() {
    return warehouseList;
  }
}
