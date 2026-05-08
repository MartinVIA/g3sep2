package viewmodel;

import javafx.collections.ObservableList;
import model.Product;
import model.ProductModel;

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

  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    products.setAll(model.getWarehouseList().getProductList());
  }
}
