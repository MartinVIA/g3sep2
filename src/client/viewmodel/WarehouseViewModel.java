package client.viewmodel;

import javafx.collections.ObservableList;
import client.model.Product;
import client.model.ProductModel;

public class WarehouseViewModel {
  private ProductModel model;
  private ObservableList<Product> products;

  public WarehouseViewModel(ProductModel model){
    this.model = model;
    this.products = model.getWarehouseList().getProductList();
  }

  public ObservableList<Product> getProducts(){
    return products;
  }

  public void reload(){
    products.setAll(model.getWarehouseList().getProductList());
  }

  public ProductModel getModel() {
    return model;
  }

}
