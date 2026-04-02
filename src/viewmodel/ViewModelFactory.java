package viewmodel;

import model.ModelManager;
import model.ProductModel;

public class ViewModelFactory {
  private MainViewModel mainVM;
  private StoreViewModel storeVM;
  private WarehouseListViewModel warehouseListVM;
  private WarehouseViewModel warehouseVM;
  private OrderViewModel orderVM;

  public ViewModelFactory(){
    ModelManager model = new ModelManager();
    storeVM = new StoreViewModel(model);
    warehouseListVM = new WarehouseListViewModel(model);
    warehouseVM = new WarehouseViewModel(model);
    orderVM = new OrderViewModel(model);
  }
  public MainViewModel getMainVM() {
    return mainVM;
  }
  public OrderViewModel getOrderVM() {
    return orderVM;
  }
  public StoreViewModel getStoreVM() {
    return storeVM;
  }
  public WarehouseViewModel getWarehouseVM() {
    return warehouseVM;
  }
  public WarehouseListViewModel getWarehouseListVM() {
    return warehouseListVM;
  }
}
