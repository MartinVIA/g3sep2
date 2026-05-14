package client.viewmodel;

import javafx.collections.ObservableList;
import client.model.Product;
import client.model.ProductModel;

public class WarehouseListViewModel {
  private ProductModel model;
  private ObservableList<Product> products;

  public WarehouseListViewModel(ProductModel model){
    this.model = model;
    this.products = model.getWarehouseList().getProductList();
  }

  public ObservableList<Product> getProducts(){
      return products;
  }

  public void orderFromWarehouse(){
    //client.sendUpdate("warehouse", new ArrayList<>(model.getWarehouseList().getProductList()));
  }

  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    products.setAll(model.getWarehouseList().getProductList());
  }
}
