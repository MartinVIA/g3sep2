package viewmodel;

import javafx.beans.property.*;
import model.Product;

public class ProductViewModel
{
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();
  private IntegerProperty price = new SimpleIntegerProperty();
  private IntegerProperty quantity = new SimpleIntegerProperty();
  private BooleanProperty isPerishable = new SimpleBooleanProperty();

  public ProductViewModel(Product product)
  {
    this.name.set(product.getName());
    this.description.set(product.getDescription());
    this.price.set(product.getPrice());
    this.quantity.set(product.getQuantity());
    this.isPerishable.set(product.isPerishable());
  }


  public StringProperty nameProperty()
  {
    return name;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public IntegerProperty priceProperty()
  {
    return price;
  }

  public IntegerProperty quantityProperty()
  {
    return quantity;
  }

  public BooleanProperty isPerishableProperty()
  {
    return isPerishable;
  }
}

