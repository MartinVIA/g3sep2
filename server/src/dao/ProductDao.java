package dao;

import java.util.ArrayList;
import model.Product;
public interface ProductDao {
  ArrayList<Product> findAllProduct();
  void save();
}
