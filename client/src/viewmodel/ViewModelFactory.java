package viewmodel;



import model.ProductModel;


public class ViewModelFactory {
  private final MainViewModel mainVM;
  private final StoreViewModel storeVM;
  private final WarehouseListViewModel warehouseListVM;
  private final WarehouseViewModel warehouseVM;
  private final OrderViewModel orderVM;

  public ViewModelFactory(ProductModel model){
    mainVM = new MainViewModel();
    orderVM = new OrderViewModel(model);
    storeVM = new StoreViewModel(model);
    warehouseListVM = new WarehouseListViewModel(model, orderVM);
    warehouseVM = new WarehouseViewModel(model, orderVM);

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
