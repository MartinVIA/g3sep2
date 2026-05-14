package client.viewmodel;

import client.mediator.Client;
import client.model.ProductList;
import javafx.collections.ObservableList;
import client.model.Product;
import client.model.ProductModel;

import java.util.ArrayList;

public class StoreViewModel {
  private ProductModel model;
  private ObservableList<Product> nettoProducts;
  private ObservableList<Product> remaProducts;
  private ObservableList<Product> bilkaProducts;
  private final Client client;

  public StoreViewModel(ProductModel model, Client client) {
    this.model = model;
    this.client = client;
    nettoProducts = this.model.getNettoList();
    remaProducts = this.model.getRemaList();
    bilkaProducts = this.model.getBilkaList();
  }

  public ObservableList<Product> getNettoProducts(){
    return nettoProducts;
  }
  public ObservableList<Product> getRemaProducts(){
    return remaProducts;
  }
  public ObservableList<Product> getBilkaProducts(){
    return bilkaProducts;
  }
  public void orderFromWarehouse(){
    //client.sendUpdate("warehouse", new ArrayList<>(model.getWarehouseList().getProductList()));
  }
  public ProductModel getModel() {
    return model;
  }

}
