import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
//import lejos.nxt.LightSensor;

//changes

public class TestRobo {

  public static RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
  public static RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
  
  public static void main(String[] args) {
    //forward();
    //backward();
    //rotate();
    //lichtsensor
    testLightSensor();
    //testColorSensor(1); // new one
    //testNXTColorSensor(1); //old one second
  }
  
  private static void testLightSensor() {
      NXTLightSensor lightSensor = new NXTLightSensor(SensorPort.S1);
        LCD.drawString("Init", 2, 2);
        LCD.setAutoRefresh(false);
        
      //Werte entsprechen den Tests mit RedMode(eingeschaltenes Licht)
      //Zusatzinfos: 1 ist hell, 0 ist ganz dunkel
      //0,56... bei weißem Papier
      //0.43 bei grau
      //0,32 wenn ganz schwarz... komplett schwarz
      //0,30 bei Abgrund
      //weiß bei Schatten 0,7  

      //SensorMode color = colorSensor.getAmbientMode(); //braucht man mit Umgebungslicht zu messen
      SensorMode color = lightSensor.getRedMode(); //braucht man um mit eingeschaltenen Licht zu messen

      float[] lightSample = new float[color.sampleSize()];
      
      while (!Button.ESCAPE.isDown()) {
        color.fetchSample(lightSample, 0);
        
        LCD.refresh();
        LCD.clear();
        LCD.drawString("ColorId: " + lightSample[0],1,1);
        //LCD.drawString(" FlooLight: " + floodNumb, 1, 1);
        Delay.msDelay(500);
      }
      
      lightSensor.close();
  }
    
  
 private static void testNXTColorSensor(int mode) {
   LCD.drawString("in testFunc", 1, 1);
   NXTColorSensor colorSensor = new NXTColorSensor(SensorPort.S4);
   colorSensor.setFloodlight(false);
     LCD.drawString("Init", 2, 2);
     LCD.setAutoRefresh(false);
     
     SensorMode color = null;
     LCD.drawString("ColorId before mode switch", 1, 1);
     switch(mode) {
       case 1: color = colorSensor.getColorIDMode(); break;
       case 2: color = colorSensor.getRedMode(); break;
       case 3: color = colorSensor.getAmbientMode(); break;
       default:{
         LCD.drawString("ColorId blablabla mode", 1, 1);
         colorSensor.getColorIDMode();
       }
     }
     

   float[] colorSample = new float[color.sampleSize()];
   
   while (!Button.ESCAPE.isDown()) {
     color.fetchSample(colorSample, 0);
     
     LCD.refresh();
     LCD.clear();
     LCD.drawString("ColorId: " + colorSample[0], 1, 1);
     Delay.msDelay(500);
   }
   
   colorSensor.close();
    
  }  
 
/* 
  Für ColorId  
  public static final int RED = 0;
  public static final int GREEN = 1;
  public static final int BLUE = 2;
  public static final int YELLOW = 3;
  public static final int MAGENTA = 4;
  public static final int ORANGE = 5;
  public static final int WHITE = 6;
    public static final int BLACK = 7;
    public static final int PINK = 8;
    public static final int GRAY = 9;
    public static final int LIGHT_GRAY = 10;
    public static final int DARK_GRAY = 11;
    public static final int CYAN = 12;
    public static final int BROWN = 13;
    public static final int NONE = -1;
   */
  //New One
  private static void testColorSensor(int mode) {
    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S4);
    colorSensor.setFloodlight(false);
      LCD.drawString("Init", 2, 2);
      LCD.setAutoRefresh(false);
      
      SensorMode color = null;
      
      switch(mode) {
        case 1: color = colorSensor.getColorIDMode(); break;
        case 2: color = colorSensor.getRedMode(); break;
        case 3: color = colorSensor.getAmbientMode(); break;
        default: colorSensor.getColorIDMode();
      }
      

    float[] colorSample = new float[color.sampleSize()];
    
    while (!Button.ESCAPE.isDown()) {
      color.fetchSample(colorSample, 0);
      
      LCD.refresh();
      LCD.clear();
      LCD.drawString("ColorId: " + colorSample[0], 1, 1);
      Delay.msDelay(500);
    }
    
    colorSensor.close();
  }

  private static void forward() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.rotateTo(0);
    rightMotor.rotateTo(0);
    leftMotor.setSpeed(1000);
    rightMotor.setSpeed(1000);
    leftMotor.setAcceleration(400);
    rightMotor.setAcceleration(400);

    leftMotor.forward();
    rightMotor.forward();
    Delay.msDelay(5000);
    leftMotor.close();
    rightMotor.close();
  }
  
  private static void backward() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.rotateTo(0);
    rightMotor.rotateTo(0);
    leftMotor.setSpeed(1000);
    rightMotor.setSpeed(1000);
    leftMotor.setAcceleration(400);
    rightMotor.setAcceleration(400);

    leftMotor.backward();
    rightMotor.backward();
    Delay.msDelay(5000);
    leftMotor.close();
    rightMotor.close();
  }
  
  private static void rotate() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.setSpeed(1000);
    rightMotor.setSpeed(1000);
    leftMotor.setAcceleration(400);
    rightMotor.setAcceleration(400);
    //Funktion die dir immer Speed zurückgibt
    leftMotor.forward();
    //rightMotor.forward();
    Delay.msDelay(1300);
    
    rightMotor.forward();
    leftMotor.forward();
    leftMotor.close();
    rightMotor.close();
  }

}
