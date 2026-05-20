package model;

import java.util.ArrayList;

public class Warehouse {

  private ArrayList<Product> productList;

  public Warehouse(ArrayList<Product> productList) {
    this.productList = productList;
  }

  public synchronized ArrayList<Product> getProductList() {
    return productList;
  }

  public void setProductList(ArrayList<Product> productList) {
    synchronized (this.productList) {
      this.productList.clear();
      this.productList.addAll(productList);
    }
  }

}
