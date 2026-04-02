package view;

import javafx.scene.layout.Region;
import viewmodel.MainViewModel;

public class MainViewController {
  private ViewHandler viewHandler;
  private Region root;
  public void init(ViewHandler viewHandler, MainViewModel mainVM, Region root) {

  }

  public Region getRoot() {
    return root;
  }

  public void reset() {
  }
}
