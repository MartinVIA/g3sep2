package dao;

import java.util.ArrayList;
import model.ServerProduct;
public interface ProductDao {
  ArrayList<ServerProduct> findAllProducts();
  void saveAllProducts(ArrayList<ServerProduct> serverProducts);
}
