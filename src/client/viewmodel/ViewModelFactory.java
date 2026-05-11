package client.viewmodel;

import client.mediator.Client;
import client.model.ModelManager;
import client.model.ProductModel;

public class ViewModelFactory {
  private MainViewModel mainVM;
  private StoreViewModel storeVM;
  private WarehouseListViewModel warehouseListVM;
  private WarehouseViewModel warehouseVM;
  private OrderViewModel orderVM;

  public ViewModelFactory(Client client){
    ProductModel model = new ModelManager();
    client.setModel(model);
    client.notifyReady();
    mainVM = new MainViewModel();
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
