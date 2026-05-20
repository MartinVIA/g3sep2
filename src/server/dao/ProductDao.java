package server.dao;

import java.util.ArrayList;
import server.model.Product;
public interface ProductDao {
  ArrayList<Product> findAllProduct();
  void save();
}
