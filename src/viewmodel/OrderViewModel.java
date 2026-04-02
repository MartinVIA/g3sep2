package viewmodel;

import model.ProductModel;

public class OrderViewModel {
  private ProductModel model;

  public OrderViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }
}
