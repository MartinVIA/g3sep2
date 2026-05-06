package viewmodel;

import model.ProductModel;
import model.WarehouseList;

public class WarehouseViewModel {
  private ProductModel model;
  private WarehouseList warehouseList;

  public WarehouseViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    warehouseList.getProductList();
  }
}
