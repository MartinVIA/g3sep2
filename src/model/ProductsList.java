package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsList implements ProductList {

    private ObservableList<Product> productsList;

    public ProductsList() {
        productsList = FXCollections.observableArrayList();
    }

    public void addProduct(int PID, String name, String description, int price, int quantity, boolean perishable) {
        productsList.add(new Product(PID, name, description, price, quantity, perishable));
    }

    public void discardProduct(int index) {
        productsList.remove(index);
    }

    public Product getProduct(int index) {
        return productsList.get(index);
    }

    public ObservableList<Product> getProductList() {
        return productsList;
    }

}
