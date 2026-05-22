import model.Product;
import model.ProductList;
import model.WarehouseList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

  private WarehouseList warehouseList;


  @BeforeEach
  public void setUp(){
    warehouseList = new WarehouseList();
  }

  // ZOMBIES WHITE-BOX UNIT TESTS

  // Z - Zero : getProduct on an empty list
  @Test
  public void getProductEmptyList(){
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> { warehouseList.getProduct(0);});
  }

  // O - One : Verify getters, when adding one product
  @Test
  public void addOneProductGetProductReturnCorrect(){
    warehouseList.addProduct("Armpits", "Self explanatory", 1,420,false);
    Product product = warehouseList.getProduct(0);
    assertEquals("Armpits", product.getName());
    assertEquals("Self explanatory", product.getDescription());
    assertEquals(1, product.getPrice());
    assertEquals(420, product.getQuantity());
    assertFalse(product.isPerishable());
  }

  // M - Many : Verify Index, when adding 2 products
  @Test
  public void addTwoProductsVerifyIndex(){
    warehouseList.addProduct("A", "ProdA", 5, 10, false);
    warehouseList.addProduct("B", "ProdB", 10, 20, true);
    assertEquals("A", warehouseList.getProduct(0).getName());
    assertEquals("B", warehouseList.getProduct(1).getName());
  }

  // B - Boundary : Check indexes validity
  @Test
  public void getProductLastValidIndex(){
    warehouseList.addProduct("A", "ProdA", 5, 10, false);
    warehouseList.addProduct("B", "ProdB", 10, 20, true);
    Assertions.assertNotNull(warehouseList.getProduct(1));
  }

  // Invalid
  @Test
  public void getProductFirstInvalidIndex(){
    warehouseList.addProduct("A", "ProdA", 5, 10, false);
    warehouseList.addProduct("B", "ProdB", 10, 20, true);
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> { warehouseList.getProduct(2);});
  }

  // I - Interface : ProductList Interface
  @Test
  public void addProductThroughInterface(){
    ProductList list = new WarehouseList();
    list.addProduct("Armpits", "Self explanatory", 1, 420, false);
    assertEquals("Armpits", list.getProduct(0).getName());
  }


}
