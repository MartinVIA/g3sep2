package client.viewmodel;

import client.mediator.Client;
import client.model.Product;
import javafx.beans.property.*;
import client.model.ProductModel;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrderViewModel
{
  private ProductModel model;
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();
  private StringProperty price = new SimpleStringProperty();
  private StringProperty quantity = new SimpleStringProperty();
  private StringProperty perishable = new SimpleStringProperty();
  private int index;
  private Client client;
  private String targetStore = "warehouse";
  private Product selectedProduct;

  public OrderViewModel(ProductModel model, Client client)
  {
    this.model = model;
    this.client = client;
  }

  public void setTargetStore(String store)
  {
    this.targetStore = store;
  }

  public String getTargetStore()
  {
    return targetStore;
  }

  public void setSelectedProduct(Product product)
  {
    this.selectedProduct = product;
    name.set(product.getName());
    description.set(product.getDescription());
    price.set(String.valueOf(product.getPrice()));
    quantity.set(String.valueOf(product.getQuantity()));
    perishable.set(String.valueOf(product.isPerishable()));
  }

  public ProductModel getModel()
  {
    return model;
  }

  public StringProperty getNameProperty()
  {
    return name;
  }

  public StringProperty getDescriptionProperty()
  {
    return description;
  }

  public StringProperty getPriceProperty()
  {
    return price;
  }

  public StringProperty getQuantityProperty()
  {
    return quantity;
  }

  public StringProperty getPerishableProperty()
  {
    return perishable;
  }

  public void setProductIndex(int index)
  {
    this.index = index;
    Product product;
    switch (targetStore)
    {
      case "netto":
        product = model.getNettoList().get(index);
        break;
      case "rema":
        product = model.getRemaList().get(index);
        break;
      case "bilka":
        product = model.getBilkaList().get(index);
        break;
      default:
        product = model.getWarehouseList().getProduct(index);
        break;
    }
    name.set(product.getName());
    description.set(product.getDescription());
    price.set(String.valueOf(product.getPrice()));
    quantity.set(String.valueOf(product.getQuantity()));
    perishable.set(String.valueOf(product.isPerishable()));
  }

  public void orderStock(int stock)
  {
    switch (targetStore)
    {
      case "netto":
      {
        ObservableList<Product> storelist = model.getNettoList();
        Product storeProduct = findMatchingProduct(new ArrayList<>(storelist), selectedProduct.getName());
        if (storeProduct != null)
        {
          storeProduct.setQuantity(storeProduct.getQuantity() + stock);
          client.sendUpdate("netto", new ArrayList<>(storelist));
          sendWarehouseQuantityDeduction(selectedProduct.getName(), stock);
        }
        break;
      }
      case "rema":
      {
        ObservableList<Product> storelist = model.getRemaList();
        Product storeProduct = findMatchingProduct(new ArrayList<>(storelist), selectedProduct.getName());
        if (storeProduct != null)
        {
          storeProduct.setQuantity(storeProduct.getQuantity() + stock);
          client.sendUpdate("rema", new ArrayList<>(storelist));
          sendWarehouseQuantityDeduction(selectedProduct.getName(), stock);
        }
        break;
      }
      case "bilka":
      {
        ObservableList<Product> storelist = model.getBilkaList();
        Product storeProduct = findMatchingProduct(new ArrayList<>(storelist), selectedProduct.getName());
        if (storeProduct != null)
        {
          storeProduct.setQuantity(storeProduct.getQuantity() + stock);
          client.sendUpdate("bilka", new ArrayList<>(storelist));
          sendWarehouseQuantityDeduction(selectedProduct.getName(), stock);
        }
        break;
      }
      default:
      {
        int newStock = stock + selectedProduct.getQuantity();
        selectedProduct.setQuantity(newStock);
        client.sendUpdate("warehouse", new ArrayList<>(model.getWarehouseList().getProductList()));
        break;
      }
    }
  }

  private Product findMatchingProduct(ArrayList<Product> list, String name)
  {
    for (Product product : list)
    {
      if (product.getName().equals(name))
        return product;
    }
    return null;
  }

  private void sendWarehouseQuantityDeduction(String productName, int stock)
  {
    ArrayList<Product> warehouseList = new ArrayList<>(
        model.getWarehouseList().getProductList());
    Product warehouseProduct = findMatchingProduct(warehouseList, productName);
    if (warehouseProduct != null)
    {
      warehouseProduct.setQuantity(warehouseProduct.getQuantity() - stock);
      client.sendUpdate("warehouse", warehouseList);
    }
  }
}
