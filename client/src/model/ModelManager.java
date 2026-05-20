package model;

import javafx.collections.ObservableList;
import mediator.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelManager implements ProductModel {

  private ProductList nettoList;
  private ProductList remaList;
  private ProductList bilkaList;
  private ProductList warehouseList;
  private Client client;

  public ModelManager(){
    this.warehouseList = new WarehouseList();
    this.remaList = new StoreList();
    this.nettoList = new StoreList();
    this.bilkaList = new StoreList();
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

  public void setClient(Client client) {
    this.client = client;
  }

  @Override public void orderStock(String store, String productName,
      int quantity)
  {
    switch (store) {
      case "netto" -> {
        ArrayList<Product> list = new ArrayList<>(getNettoList());
        Product p = findProduct(list, productName);
        if (p != null) {
          p.setQuantity(p.getQuantity() + quantity);
          client.sendUpdate("netto", list);
          deductWarehouse(productName, quantity);
        }
      }
      case "rema" -> {
        ArrayList<Product> list = new ArrayList<>(getRemaList());
        Product p = findProduct(list, productName);
        if (p != null) {
          p.setQuantity(p.getQuantity() + quantity);
          client.sendUpdate("rema", list);
          deductWarehouse(productName, quantity);
        }
      }
      case "bilka" -> {
        ArrayList<Product> list = new ArrayList<>(getBilkaList());
        Product p = findProduct(list, productName);
        if (p != null) {
          p.setQuantity(p.getQuantity() + quantity);
          client.sendUpdate("bilka", list);
          deductWarehouse(productName, quantity);
        }
      }
      default -> {
        ArrayList<Product> list = new ArrayList<>(warehouseList.getProductList());
        Product p = findProduct(list, productName);
        if (p != null) {
          p.setQuantity(p.getQuantity() + quantity);
          client.sendUpdate("warehouse", list);
        }
      }
    }
  }

  @Override public void discardProduct(String store, String productName)
  {
    switch (store) {
      case "netto" -> {
        ArrayList<Product> list = new ArrayList<>(getNettoList());
        Product p = findProduct(list, productName);
        if (p != null) { p.setQuantity(0); client.sendUpdate("netto", list); }
      }
      case "rema" -> {
        ArrayList<Product> list = new ArrayList<>(getRemaList());
        Product p = findProduct(list, productName);
        if (p != null) { p.setQuantity(0); client.sendUpdate("rema", list); }
      }
      case "bilka" -> {
        ArrayList<Product> list = new ArrayList<>(getBilkaList());
        Product p = findProduct(list, productName);
        if (p != null) { p.setQuantity(0); client.sendUpdate("bilka", list); }
      }
    }
  }

  private void deductWarehouse(String productName, int quantity) {
    ArrayList<Product> list = new ArrayList<>(warehouseList.getProductList());
    Product p = findProduct(list, productName);
    if (p != null) {
      p.setQuantity(p.getQuantity() - quantity);
      client.sendUpdate("warehouse", list);
    }
  }

  private Product findProduct(ArrayList<Product> list, String name) {
    for (Product p : list)
      if (p.getName().equals(name)) return p;
    return null;
  }

  public ProductList getWarehouseList() {
    return warehouseList;
  }
}
