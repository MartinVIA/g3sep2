package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ProductModel;

public class WarehouseListViewModel {
  private ProductModel model;
  private final ObservableList<ProductViewModel> products = FXCollections.observableArrayList();
  private final OrderViewModel orderViewModel;

  public WarehouseListViewModel(ProductModel model, OrderViewModel orderViewModel) {
    this.model = model;
    this.orderViewModel = orderViewModel;
    model.getWarehouseList().getProductList().addListener(
        (javafx.collections.ListChangeListener<model.Product>) change -> {
          products.clear();
          model.getWarehouseList().getProductList()
              .forEach(p -> products.add(new ProductViewModel(p)));
        }
    );
  }

  public ObservableList<ProductViewModel> getProducts() { return products; }

  public void selectProductForOrder(int index, String store) {
    orderViewModel.setSelectedIndex(index, store);
  }
}
