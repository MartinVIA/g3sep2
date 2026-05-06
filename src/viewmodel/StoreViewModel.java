package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.ProductModel;

public class StoreViewModel {
  private ProductModel model;
  private ObservableList<ProductViewModel> storeList;

  public StoreViewModel(ProductModel model){
    this.model = model;
    storeList = FXCollections.observableArrayList();
    for (Product product : model.getStoreList().getProductList())
    {
      storeList.add(new ProductViewModel(product));
    }
  }
  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    storeList.clear();
    storeList = FXCollections.observableArrayList();
    for (Product product : model.getStoreList().getProductList())
    {
      storeList.add(new ProductViewModel(product));
    }
  }

  public ObservableList<ProductViewModel> getStoreList(){
    return storeList;
  }
}
