package model;

import java.util.ArrayList;

public class Store {

  private String name;
  private ArrayList<ServerProduct> serverProductList;

  public Store(String name, ArrayList<ServerProduct> serverProductList) {
    this.name = name;
    this.serverProductList = serverProductList;
  }

  public String getName() {
    return name;
  }

  public synchronized ArrayList<ServerProduct> getProductList() {
    return serverProductList;
  }

  public void setProductList(ArrayList<ServerProduct> serverProductList) {
    synchronized (this.serverProductList) {
      this.serverProductList.clear();
      this.serverProductList.addAll(serverProductList);
    }
  }

}
