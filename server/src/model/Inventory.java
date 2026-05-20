package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

  private Warehouse warehouse;
  private Store netto;
  private Store rema;
  private Store bilka;

  public Inventory(ArrayList<ServerProduct> warehouseList,
      ArrayList<ServerProduct> nettoList,
      ArrayList<ServerProduct> remaList,
      ArrayList<ServerProduct> bilkaList){
    this.warehouse = new Warehouse(warehouseList);
    this.netto = new Store("netto", nettoList);
    this.rema = new Store("rema", remaList);
    this.bilka = new Store("bilka", bilkaList);
  }

  public synchronized void updateList(String type, ArrayList<ServerProduct> updatedList) {
    switch (type) {
      case "warehouse" -> warehouse.setProductList(updatedList);
      case "netto"     -> netto.setProductList(updatedList);
      case "rema"      -> rema.setProductList(updatedList);
      case "bilka"     -> bilka.setProductList(updatedList);
    }
  }

  public synchronized HashMap<String, ArrayList<ServerProduct>> getAllLists(){
    HashMap<String, ArrayList<ServerProduct>> allLists = new HashMap<>();
    allLists.put("warehouse", warehouse.getProductList());
    allLists.put("netto", netto.getProductList());
    allLists.put("rema", rema.getProductList());
    allLists.put("bilka", bilka.getProductList());
    return allLists;
  }

  public ArrayList<ServerProduct> getWarehouseList(){ return warehouse.getProductList(); }
  public ArrayList<ServerProduct> getNettoList(){ return netto.getProductList(); }
  public ArrayList<ServerProduct> getRemaList(){ return rema.getProductList(); }
  public ArrayList<ServerProduct> getBilkaList(){ return bilka.getProductList(); }

}
