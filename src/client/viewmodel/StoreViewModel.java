package client.viewmodel;

import client.model.ProductList;
import javafx.collections.ObservableList;
import client.model.Product;
import client.model.ProductModel;

public class StoreViewModel {
  private ProductModel model;
  private ObservableList<Product> nettoProducts;
  private ObservableList<Product> remaProducts;
  private ObservableList<Product> bilkaProducts;

  public StoreViewModel(ProductModel model) {
    this.model = model;
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

  public ProductModel getModel() {
    return model;
  }

}
