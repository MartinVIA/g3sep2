package model;

import javafx.collections.ObservableList;

public class ModelManager implements ProductModel {
  private ProductList storeList;
  private ProductList warehouseList;
  public ModelManager(){
    //These can later be made into test cases for adding items to the warehouse, albeit its not part of the use cases to create new items, for testing we kinda need to
    storeList = new StoreList();
    warehouseList = new WarehouseList();
    storeList.addProduct("Cheeseburger","A cheesburger?",100, 666,true);
    storeList.addProduct("Buvany Special","A what?",50, 67,false);
    warehouseList.addProduct("Armpits","Self-explanatory",1, 420,false);
    warehouseList.addProduct("Sonic Fanfiction","Those who knows",1234567 ,42,true);
  }

  public ProductList getStoreList() {
    return storeList;
  }
  public ProductList getWarehouseList() {
    return warehouseList;
  }
}
