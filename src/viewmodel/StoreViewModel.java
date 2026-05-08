package viewmodel;

import javafx.collections.ObservableList;
import model.Product;
import model.ProductModel;

public class StoreViewModel {
  private ProductModel model;
  private ObservableList<Product> products;

  public StoreViewModel(ProductModel model)
  {
    this.model = model;
    this.products = model.getStoreList().getProductList();
  }

  public ObservableList<Product> getProducts(){
    return products;
  }

  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    products.setAll(model.getStoreList().getProductList());
  }
}
