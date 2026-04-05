package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StoreList implements ProductList {

  private ObservableList<Product> storeList;

  public StoreList(){
    storeList = FXCollections.observableArrayList();
  }

   public void addProduct(int PID, String name, String description,int price, int quantity, boolean perishable) {
     storeList.add(new Product(PID,name,description,price,quantity,perishable));
  }

  public void orderProduct(int PID,int quantity){

  }
  public void discardProduct(int index){
    storeList.remove(index);
  }
  
  public Product getProduct(int index) {
    return storeList.get(index);
  }
  public ObservableList<Product> getProductList()
  {
    return storeList;
  }

}
