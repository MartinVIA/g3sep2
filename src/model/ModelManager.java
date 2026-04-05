package model;

import javafx.collections.ObservableList;

public class ModelManager implements ProductModel {
  private ProductList storeList;
  private ProductList warehouseList;
  private ProductList productsList;
  public ModelManager(){
    //These can later be made into test cases for adding items to the warehouse, albeit its not part of the use cases to create new items, for testing we kinda need to
    storeList = new StoreList();
    warehouseList = new WarehouseList();
    productsList=new ProductsList();
    storeList.addProduct(101,"Cheeseburger","A cheesburger?",100, 666,true);
    storeList.addProduct(102,"Buvany Special","A what?",50, 67,false);
    warehouseList.addProduct(103,"Armpits","Self-explanatory",1, 420,false);
    warehouseList.addProduct(104,"Sonic Fanfiction","Those who knows",1234567 ,42,true);
    
    productsList.addProduct(101,"Cheeseburger","A cheesburger?",100, 1,true);
    productsList.addProduct(102,"Buvany Special","A what?",50, 1,false);
    productsList.addProduct(103,"Armpits","Self-explanatory",1, 1,false);
    productsList.addProduct(104,"Sonic Fanfiction","Those who knows",1234567 ,1,true);
  }

  public ProductList getStoreList() {
    return storeList;
  }
  public ProductList getWarehouseList() {
    return warehouseList;
  }

  public ProductsList getProductsList(){
    return productsList;
  }
}
