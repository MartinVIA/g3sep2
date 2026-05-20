package viewmodel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ProductModel;


public class StoreViewModel {
  private ProductModel model;
  private final ObservableList<ProductViewModel> nettoProducts  = FXCollections.observableArrayList();
  private final ObservableList<ProductViewModel> remaProducts   = FXCollections.observableArrayList();
  private final ObservableList<ProductViewModel> bilkaProducts  = FXCollections.observableArrayList();

  public StoreViewModel(ProductModel model) {
    this.model = model;
    model.getNettoList().addListener(
        (javafx.collections.ListChangeListener<model.Product>) change -> {
          nettoProducts.clear();
          model.getNettoList().forEach(p -> nettoProducts.add(new ProductViewModel(p)));
        }
    );
    model.getRemaList().addListener(
        (javafx.collections.ListChangeListener<model.Product>) change -> {
          remaProducts.clear();
          model.getRemaList().forEach(p -> remaProducts.add(new ProductViewModel(p)));
        }
    );
    model.getBilkaList().addListener(
        (javafx.collections.ListChangeListener<model.Product>) change -> {
          bilkaProducts.clear();
          model.getBilkaList().forEach(p -> bilkaProducts.add(new ProductViewModel(p)));
        }
    );
  }

  public ObservableList<ProductViewModel> getNettoProducts() { return nettoProducts; }
  public ObservableList<ProductViewModel> getRemaProducts()  { return remaProducts; }
  public ObservableList<ProductViewModel> getBilkaProducts() { return bilkaProducts; }

  public void discardProduct(String store, int index) {
    String productName;
    switch (store) {
      case "netto": productName = nettoProducts.get(index).getName(); break;
      case "rema":  productName = remaProducts.get(index).getName();  break;
      case "bilka": productName = bilkaProducts.get(index).getName(); break;
      default: return;
    }
    model.discardProduct(store, productName);
  }

}
