package view;

import javafx.scene.layout.Region;
import viewmodel.WarehouseViewModel;

public class WarehouseViewController {
  private ViewHandler viewHandler;
  private Region root;
  private WarehouseViewModel model;
  public void init(ViewHandler viewHandler, WarehouseViewModel model, Region root) {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
  }
}
