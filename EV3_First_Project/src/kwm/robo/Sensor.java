package kwm.robo;
//-------------------------------------------------------------------- 
//Sensor.java 
//the class includes all methodes that implements the functionality
//of the required sensors
//
//Author: Michaela Buschberger <S1710456005@students.fh-hagenberg.at> 
//Created: 16.12.2018 
//--------------------------------------------------------------------

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

public class Sensor {
  static final NXTLightSensor lightSensor = new NXTLightSensor(SensorPort.S2);
  static final double red = 0.39;
  static final double black = 0.36;
  
  //uni testtag 1:
  //schwarz ist 0.36
  //grau ist 0.42
  //rot ist 0.39
  

  //-------------------------------------------------------------
  // isBlackOrRed - checks for black and red lines (lightsensor)
  // return true if the line is black or red
  // return false if there is no black or red line
  // Werte entsprechen den Tests mit RedMode(eingeschaltenes Licht)
  // Zusatzinfos: 1 ist hell, 0 ist ganz dunkel
  // 0,56... bei weiﬂem Papier
  // 0.43 bei grau
  // 0,32 wenn ganz schwarz... komplett schwarz
  // 0,30 bei Abgrund
  // weiﬂ bei Schatten 0,7  
  //-------------------------------------------------------------  
  public static boolean isBlackOrRed() {
    LCD.drawString("Init", 2, 2);
      LCD.setAutoRefresh(false);
      
      SensorMode color = lightSensor.getRedMode(); //braucht man um mit eingeschaltenen Licht zu messen
      Delay.msDelay(2000);
      
      float[] lightSample = new float[color.sampleSize()];
      color.fetchSample(lightSample, 0); 
      LCD.refresh();
      LCD.clear();
      LCD.drawString("Black: " + lightSample[0],1,1);
      Delay.msDelay(2000);
      
      
      
      if(!isBlack()&&!isRed()) {
        lightSensor.close();
        return false;
      }
      return true;
  }
  
  //-------------------------------------------------------------
  // isBlack - checks for black lines (lightsensor)
  //-------------------------------------------------------------  
  public static boolean isBlack() {  
      
      LCD.drawString("Init Black", 2, 2);
      LCD.setAutoRefresh(false);
      
      SensorMode color = lightSensor.getRedMode(); //braucht man um mit eingeschaltenen Licht zu messen
      //Delay.msDelay(2000);
      
      float[] lightSample = new float[color.sampleSize()];
      while (!Button.ESCAPE.isDown()) {
        color.fetchSample(lightSample, 0); 
        LCD.refresh();
        LCD.clear();
        LCD.drawString("Black: " + lightSample[0],1,1);
        //Delay.msDelay(2000);
        
          if(lightSample[0] < black)
          {
             return true;
            //return true;
          }
       }
        
      lightSensor.close();
      return false;
  }
  
  //-------------------------------------------------------------
  // isRed - checks for red lines and
  //returnts true if red line found (color sensor on the right)
  //-------------------------------------------------------------  
  
  public static boolean isRed() {    
    
  EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
  colorSensor.setFloodlight(false);
    LCD.drawString("Init", 2, 2);
    LCD.setAutoRefresh(false);
    
    SensorMode color = null;
    
    color = colorSensor.getColorIDMode(); 
  float[] colorSample = new float[color.sampleSize()];
  
  while (!Button.ESCAPE.isDown()) {
    color.fetchSample(colorSample, 0);
    
    LCD.refresh();
    LCD.clear();
    LCD.drawString("Color: " + colorSample[0], 1, 1);
    
    if(colorSample[0] == 0) {
          LCD.drawString("RED",2,2);
      return true;

    }
  }
  
  colorSensor.close();
    return false;
  }
  
    
  //-------------------------------------------------------------
  // checkIntercept() - checks if there is an intercept (black
  // or red)
  // to change position and rotate (old nxt colorsensor)
  //------------------------------------------------------------- 
 public static void checkIntercept(int mode) {
   LCD.drawString("in testFunc", 1, 1);
   NXTColorSensor colorSensor = new NXTColorSensor(SensorPort.S2);
   colorSensor.setFloodlight(false);
     LCD.drawString("Init", 1, 1);
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
  F¸r ColorId  
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

 //-------------------------------------------------------------
 // IdentifyColorOfBrick() - identifies yellow bricks which need
 // to be moved (new color sensor)
 //------------------------------------------------------------- 
  public static void IdentifyColorOfBrick(int mode) {
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

  //-------------------------------------------------------------
  // checkAbyss() - checks if there is an abyss(ultra sensor)
  // The theoretical range of the sensor is 0,04 to 2.54 meter.
  // normal desk height: 0.089 .. if > 0.09, there is an abyssis!!
  // returns true, if robo sees, that there is an abyssis upcoming
  //------------------------------------------------------------- 

  static final double abyss = 0.09; //abyss has to be desk abyss, if he sees more there is an abyss
  
  public static boolean checkAbyss() {
    NXTUltrasonicSensor us = new NXTUltrasonicSensor(SensorPort.S4);
    SampleProvider distance = us.getDistanceMode();
    //gives the average of the last 5 samples
    SampleProvider average = new MeanFilter(distance,5);
    float[] sample = new float[average.sampleSize()];
    
    LCD.clear();

      average.fetchSample(sample, 0);
          //LCD.drawString("US: " + sample[0], 0, 0);
      //System.out.println("US: " + sample[0]);
      
   //   Delay.msDelay(500);
      
      if(sample[0] >= abyss) //wenn der abstand grˆﬂer wird als der vom Tisch ist ein Abgrund da
      {
        //es wurde ein Abgrund erkannt, der Robot soll nun stoppen! 
            LCD.drawString("Abgrund erkannnnnttt!!",0,0);
            
            us.close();
            return true;            
      }
    
    us.close();
    LCD.clear();
     LCD.drawString("KEIIIIIIN Abgrund erkannnnnttt!!",0,0);
    return false;
  }
  

  //-------------------------------------------------------------
  // returnColor() - checks if the brick is yellow, if yes he
  // returns the color code yellow (3)
  //------------------------------------------------------------- 
  public static final int Yellow = 3;

  public static int isYellowColor() {
    
    int mode = 1;
    
    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
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
      
      if(colorSample[0] == Yellow)
      {
        LCD.drawString("YELLLOW: ",0,0);
        
        return Yellow;
      }
     }
    
     colorSensor.close();
    
     return 0;
  }
  public static boolean identifyColorOfLine() {
    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
    return identifyColor(colorSensor);
  }
  
  public static boolean identifyColorOfBrick() {
    EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
    return identifyColor(colorSensor);
  }
  
  private static boolean identifyColor(EV3ColorSensor colorSensor) {
    colorSensor.setFloodlight(false);
    LCD.drawString("Init", 2, 2);
    LCD.setAutoRefresh(false);
      
    SensorMode color = colorSensor.getColorIDMode();
      
  
     float[] colorSample = new float[color.sampleSize()];
    
     /*
     while (!Button.ESCAPE.isDown()) {
      color.fetchSample(colorSample, 0);
      
      LCD.refresh();
      LCD.clear();
      LCD.drawString("ColorId: " + colorSample[0], 1, 1);


     }
*/
     colorSensor.close();
     if(colorSample[0] == 0 || colorSample[0]==7) {
       return true;
     }

     return false;
    
  }


  
  
}
