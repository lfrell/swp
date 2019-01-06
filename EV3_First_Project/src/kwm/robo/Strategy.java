// -------------------------------------------------------------------- 
// Strategy.java 
// the class includes methodes that includes the strategies of how 
// the robo should finde the way
// 
// Author: Michaela Buschberger <S1710456005@students.fh-hagenberg.at> 
// Created: 16.12.2018 
// --------------------------------------------------------------------

package kwm.robo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Strategy {
  public static final int right = 0;
  public static final int left = 1;
  public static final int red = 0;
  public static final int black = 7;
  public static Drive drive = new Drive();
  public static int direction[] = {right,right,right,right,left,left,right,right};

  
  //-------------------------------------------------------------
  // moveYellowBrick() - checks if the brick is yellow and
  // moves it from the roadway
  //------------------------------------------------------------- 
  public void moveYellowBrick() {
    while(!Sensor.checkAbyss() || Sensor.isBlackOrRed() ) {
        drive.forward();
      }
      //check colorsensor for intercept
      drive.stop();  
      //drive.driveCurve(0);
      drive.stop();
      
  }
  
  //-------------------------------------------------------------
  // moveYellowBrick() - checks if the brick is yellow and
  // moves it from the roadway
  //------------------------------------------------------------- 
  public static void positionOnLine(int direction) { 
    //driveUntilBlack() {
    if(driveUntilLineReached())    
      drive.rotate(direction, 90,true);
    //move to the right(rotate 90°)
  }
  
  //drives and stops at a black line to be able to position on the black middle line
  public static boolean driveUntilLineReached() {
    if(!drive.getIsOpen()) drive.init();    
    drive.forward();
    int color = Sensor.identifyColorOfLine();
      if(color == red ||color == black) {
        LCD.drawString("drive 1",1,1);
        return true;
      }
      return false;
  }

  public static void driveWayPerTable() {
    //orangener Weg
    //driveForwardUntilAbbeyAndRotate (fährt kurve)
    for(int i = 0; i<direction.length; i++) {
      while(!Sensor.checkAbyss()) {
        drive.forward();    
      }
      positionOnLine(direction[i]);  
    }
    /*
    while(Sensor.identifyColorOfBrick()!=3) {
      rotateWhenAbbey();
    }
    */
    // in einer while schaut er solange nach grünen und gelben
    // bei gelb - move yellow Brick
    //positionOnLine
    
  }
  /*
    public static void rotateWhenAbbey() {
      while (!Button.ESCAPE.isDown()) {  
        if(!Sensor.checkAbyss()) {
          //LCD.drawString("isBlack:"+Sensor.isBlack(),1,1);
          if(!drive.getIsOpen()) drive.init();
          drive.forward();          
          LCD.drawString("drive 1",1,1);

        }else {
          drive.stop();
          drive.init();
            LCD.drawString("abyss",1,1);
            drive.rotate(direction,90, false); //back, rotate, forward
            drive.stop();
        }    
      }
    }
    */

    public static void driveRedLinePerTable() {
      // TODO Auto-generated method stub
      
    }
  }


