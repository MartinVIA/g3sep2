package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

  private Warehouse warehouse;
  private Store netto;
  private Store rema;
  private Store bilka;

  public Inventory(ArrayList<Product> warehouseList,
      ArrayList<Product> nettoList,
      ArrayList<Product> remaList,
      ArrayList<Product> bilkaList){
    this.warehouse = new Warehouse(warehouseList);
    this.netto = new Store("netto", nettoList);
    this.rema = new Store("rema", remaList);
    this.bilka = new Store("bilka", bilkaList);
  }

  public synchronized void updateList(String type, ArrayList<Product> updatedList) {
    switch (type) {
      case "warehouse" -> warehouse.setProductList(updatedList);
      case "netto"     -> netto.setProductList(updatedList);
      case "rema"      -> rema.setProductList(updatedList);
      case "bilka"     -> bilka.setProductList(updatedList);
    }
  }

  public synchronized HashMap<String, ArrayList<Product>> getAllLists(){
    HashMap<String, ArrayList<Product>> allLists = new HashMap<>();
    allLists.put("warehouse", warehouse.getProductList());
    allLists.put("netto", netto.getProductList());
    allLists.put("rema", rema.getProductList());
    allLists.put("bilka", bilka.getProductList());
    return allLists;
  }

  public ArrayList<Product> getWarehouseList(){ return warehouse.getProductList(); }
  public ArrayList<Product> getNettoList(){ return netto.getProductList(); }
  public ArrayList<Product> getRemaList(){ return rema.getProductList(); }
  public ArrayList<Product> getBilkaList(){ return bilka.getProductList(); }

}
