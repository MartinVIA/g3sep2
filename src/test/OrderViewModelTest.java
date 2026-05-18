import client.model.ModelManager;
import client.model.ProductModel;
import client.viewmodel.OrderViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderViewModelTest {


  // E - Exception : Order when warehouse is empty
  @Test
  public void orderStockWithNoProduct(){
    ProductModel model = new ModelManager();
    OrderViewModel viewModel = new OrderViewModel(model, null);
    assertThrows(NullPointerException.class, () -> { viewModel.orderStock(5);});
  }

}
