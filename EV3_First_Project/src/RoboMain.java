import kwm.robo.Drive;
import kwm.robo.Sensor;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

// -------------------------------------------------------------------- 
// Strategy.java 
// main class to test the robo functionalities
// 
// Author: Michaela Buschberger <S1710456005@students.fh-hagenberg.at> 
// Created: 16.12.2018 
// --------------------------------------------------------------------

public class RoboMain {
  
  public static void main(String[] args) {

	//for test Day 1
    testRotateWhenAbbey();
    //checkAbyss();				//geht
    //testStopWhenBlack();
    //testRotation();  //nur kleine rotation!!
    //checkIsBlack();
    //checkIsRed();				//geht
	 // testStopWhenRed();
    //checkIsYellowBrick();		//würde gehen aber pos von sensor ist schlecht, müsste tiefer hängen!
    //checkColorSensorForIntercept();  //geht nicht weil der farbsensor rechts nicht5 geht
  
  }

	
  //returns true, if he found an abyss and stops
  public static void checkAbyss() {
    while (!Button.ESCAPE.isDown()) {
    	boolean abyssState = Sensor.checkAbyss();
		Delay.msDelay(1000);

    }
  }
  

  public static void checkIsBlack() {
	    while (!Button.ESCAPE.isDown()) {
	        boolean blackState = Sensor.isBlack();

	    }    
  }
  public static void checkIsRed() {
	  
      boolean redState = Sensor.isRed();
		Delay.msDelay(3000);  
  }
  
  public static void checkIsYellowBrick() {
      int yellowState = Sensor.isYellowColor();
  }
  
  //returns true, if he found an abyss and stops
  public static void checkColorSensorForIntercept() {
    Sensor.checkIntercept(1);
  }
  
  private static void testRotateWhenAbbey() {
    //Sensor.checkAbyss();
    //Drive.driveCurve();
    //Drive.stop();
    //boolean isBlack = Sensor.isBlack();
    //boolean isRed = Sensor.isRed();
    //int brickColor = Sensor.isYellowColor();
	  
	  /*
	    while (!Button.ESCAPE.isDown()) {

	            Drive.driveCurve(); //back, rotate, forward

	        }
		*/
	  Drive drive = new Drive();
    while (!Button.ESCAPE.isDown()) {

    	
      if(!Sensor.checkAbyss()) {
        //LCD.drawString("isBlack:"+Sensor.isBlack(),1,1);
    	  if(!drive.getIsOpen()) drive.init();
    	  drive.forward();
        
    	  LCD.drawString("drive 1",1,1);

        /*
        int brickColor = Sensor.isYellowColor();
        if(brickColor==3) {
          Drive.stop();
          Strategy.moveYellowBrick();
        }*/
      }else {
    	  drive.stop();
    	  drive.init();
          LCD.drawString("abyss",1,1);
          //drive.stop();
          drive.driveCurve(); //back, rotate, forward
          drive.stop();
      }    
    }
  }
  
  //used to get position
  public static void testStopWhenBlack() {
	  Drive drive = new Drive();
    if(!drive.getIsOpen()) drive.init();    
    drive.forward();
	    if(Sensor.isBlack()) {
	      //LCD.drawString("isBlack:"+isblack,1,1);
	      LCD.drawString("drive 1",1,1);
	      drive.stop();
	    }
  }
  
 public static void testStopWhenRed() {
	  Drive drive = new Drive();
	    if(!drive.getIsOpen()) drive.init();    
	    drive.forward();
		    if(Sensor.isRed()) {
		      LCD.drawString("stooooopppppw",1,1);
		      drive.stop();
		    }
	  
  }
  
  // check rotation
  public static void testRotation() {
    Drive drive = new Drive();
    if(!drive.getIsOpen()) drive.init();  
    //move back and rotate
    drive.rotate(0);
    drive.stop();
    drive.init();
    //drive forward
    drive.forward();
    drive.stop();
  }
}
