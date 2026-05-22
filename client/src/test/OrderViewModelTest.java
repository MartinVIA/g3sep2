import model.ModelManager;
import model.ProductModel;
import org.junit.jupiter.api.Assertions;
import viewmodel.OrderViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderViewModelTest {


  // E - Exception : Order when warehouse is empty
  @Test
  public void orderStockWithNoProduct(){
    ProductModel model = new ModelManager();
    OrderViewModel viewModel = new OrderViewModel(model);
    assertThrows(IllegalStateException.class, () -> viewModel.orderStock(5));
  }

}
