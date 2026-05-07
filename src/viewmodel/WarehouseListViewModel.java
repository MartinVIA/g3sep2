package viewmodel;

import javafx.collections.ObservableList;
import model.Product;
import model.ProductModel;
import model.WarehouseList;

public class WarehouseListViewModel {
  private ProductModel model;

  public WarehouseListViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }
}
