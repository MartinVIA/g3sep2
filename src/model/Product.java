package model;

import java.beans.PropertyChangeSupport;

public class Product {

  private String name;
  private String description;
  private int quantity;
  private boolean availability;
  private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  public Product ( String name, String description, int quantity, boolean availability ){
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.availability = false;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public boolean isAvailability()
  {
    return availability;
  }

  public void setAvailability(boolean availability)
  {
    this.availability = availability;
  }
}
