package viewmodel;

import model.ProductModel;
import model.WarehouseList;

public class WarehouseListViewModel {
  private ProductModel model;
  private WarehouseList warehouseList;

  public WarehouseListViewModel(ProductModel model){
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
