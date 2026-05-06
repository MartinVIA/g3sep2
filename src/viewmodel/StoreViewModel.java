package viewmodel;

import model.ProductModel;
import model.StoreList;

public class StoreViewModel {
  private ProductModel model;
  private StoreList storeList;

  public StoreViewModel(ProductModel model){
    this.model = model;
  }
  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    storeList.getProductList();
  }
}
