import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ServerLog;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ServerLogTest {

  // S - Singleton : Test if getInstance returns the same object both times
  @Test
  public void getInstanceTwiceReturnSameInstance(){
    ServerLog first = ServerLog.getInstance();
    ServerLog second = ServerLog.getInstance();
    Assertions.assertSame(first, second);
  }


}
