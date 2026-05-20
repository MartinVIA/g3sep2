package model;

import java.util.ArrayList;

public class Warehouse {

  private ArrayList<ServerProduct> serverProductList;

  public Warehouse(ArrayList<ServerProduct> serverProductList) {
    this.serverProductList = serverProductList;
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
