package viewmodel;

import model.ProductModel;

public class WarehouseViewModel {
  private ProductModel model;

  public WarehouseViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    model.getWarehouseList();
  }
}
