package viewmodel;

import model.ProductModel;

public class StoreViewModel {
  private ProductModel model;

  public StoreViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }
}
