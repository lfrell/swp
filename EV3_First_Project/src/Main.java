import kwm.robo.Strategy;

public class Main {

  public static void main(String[] args) {
    testNewStrategy();

  }

  private static void testNewStrategy() {
    for (int i = 0; i <= 3; i++) {
      Strategy.driveWayPerTable();
      // don't need the redline logic at the last desk
      if (i != 3)
        Strategy.driveRedLinePerTable();
    }
  }

}
