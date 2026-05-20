package model;

import java.util.ArrayList;

public class Store {

  private String name;
  private ArrayList<Product> productList;

  public Store(String name, ArrayList<Product> productList) {
    this.name = name;
    this.productList = productList;
  }

  public String getName() {
    return name;
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
