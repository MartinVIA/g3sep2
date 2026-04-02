package viewmodel;

import model.ProductModel;

public class WarehouseListViewModel {
  private ProductModel model;

  public WarehouseListViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }
}
