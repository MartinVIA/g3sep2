package viewmodel;

import javafx.beans.property.*;
import model.Product;

public class ProductViewModel {
  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty description = new SimpleStringProperty();
  private final IntegerProperty price = new SimpleIntegerProperty();
  private final IntegerProperty quantity = new SimpleIntegerProperty();
  private final BooleanProperty perishable = new SimpleBooleanProperty();

  public ProductViewModel(Product product) {
    name.set(product.getName());
    description.set(product.getDescription());
    price.set(product.getPrice());
    quantity.set(product.getQuantity());
    perishable.set(product.isPerishable());
  }

  public StringProperty  nameProperty(){ return name; }
  public StringProperty  descriptionProperty(){ return description; }
  public IntegerProperty priceProperty(){ return price; }
  public IntegerProperty quantityProperty(){ return quantity; }
  public BooleanProperty perishableProperty(){ return perishable; }

  public String getName() { return name.get(); }
}
