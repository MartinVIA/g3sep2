package client.viewmodel;

import client.mediator.Client;
import client.model.ModelManager;
import client.model.ProductModel;

public class ViewModelFactory {
  private final MainViewModel mainVM;
  private final StoreViewModel storeVM;
  private final WarehouseListViewModel warehouseListVM;
  private final WarehouseViewModel warehouseVM;
  private final OrderViewModel orderVM;

  public ViewModelFactory(Client client){
    ProductModel model = new ModelManager();
    mainVM = new MainViewModel();
    storeVM = new StoreViewModel(model,client);
    warehouseListVM = new WarehouseListViewModel(model);
    warehouseVM = new WarehouseViewModel(model);
    orderVM = new OrderViewModel(model, client);
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
