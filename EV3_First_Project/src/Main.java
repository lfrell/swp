import kwm.robo.Sensor;
import kwm.robo.Strategy;
import lejos.hardware.Button;
import lejos.utility.Delay;

public class Main {

  public static void main(String[] args) {
      testNewStrategy();
    //checkIsRed();
  }
  
  public static void checkIsRed() {
    
    boolean redState = Sensor.isRed();
  Delay.msDelay(3000);  
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