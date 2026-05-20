package model;

public class ServerProduct {

  private String name;
  private String description;
  private int price;
  private int quantity;
  private boolean perishable;

  public ServerProduct( String name, String description,int price, int quantity, boolean perishable){
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.perishable = perishable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrice() {
    return price;
  }
  public void setPrice(int price){
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public boolean isPerishable() {
    return perishable;
  }

  public void setPerishable(boolean perishable) {
    this.perishable = perishable;
  }
}
