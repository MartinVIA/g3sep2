package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
import model.ProductModel;


public class WarehouseViewModel {
  private ProductModel model;
  private ObservableList<ProductViewModel> warehouseList;

  public WarehouseViewModel(ProductModel model)
  {
    this.model = model;
    warehouseList = FXCollections.observableArrayList();
    for (Product product : model.getWarehouseList().getProductList())
    {
      warehouseList.add(new ProductViewModel(product));
    }
  }

  public ProductModel getModel() {
    return model;
  }

  public void reload()
  {
    warehouseList.clear();
    warehouseList = FXCollections.observableArrayList();
    for (Product product : model.getWarehouseList().getProductList())
    {
      warehouseList.add(new ProductViewModel(product));
    }
  }

  public ObservableList<ProductViewModel> getWarehouseList()
  {
    return warehouseList;
  }
}
