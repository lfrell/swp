import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import kwm.robo.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import kwm.robo.*;

// -------------------------------------------------------------------- 
// Strategy.java 
// main class to test the robo functionalities
// 
// Author: Michaela Buschberger <S1710456005@students.fh-hagenberg.at> 
// Created: 16.12.2018 
// --------------------------------------------------------------------

public class RoboMain {
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //forward();
    //backward();
    //rotate();
    //lichtsensor
    //testLightSensor();
    //testColorSensor(1); // new one
    //testNXTColorSensor(1); //old one second second

    /*
    while(Sensor.isBlackOrRed()) {
      //System.out.println("it's black or red");
      
    }
    */
    Drive.driveOnRoadWay();
   
	 
	  //checkAbyss();
	  //returnColor();
    
    testDay1();
	  
  }

	
  //returns true, if he found an abyss and stops
  public static void checkAbyss() {
    Sensor.checkAbyss();
		Drive.stop();
  }
  
  //returns yellow constant code if he sees a yellow brick
  public static void returnColor() {
	  //Sensor.returnColor();
	  //strategy.moveYellowBrick();
  }
  
  private static void testDay1() {
    //Sensor.checkAbyss();
    //Drive.driveCurve();
    //Drive.stop();
    //boolean isBlack = Sensor.isBlack();
    //boolean isRed = Sensor.isRed();
    //int brickColor = Sensor.isYellowColor();
    
    while (!Button.ESCAPE.isDown()) {
      if(Sensor.checkAbyss()) {
        Drive.stop();
        Drive.driveCurve(); //back, rotate, forward
      }
      if(Sensor.isBlack()||Sensor.isRed()) {
        Drive.forward();
        int brickColor = Sensor.isYellowColor();
        if(brickColor==3) {
          Drive.stop();
          Strategy.moveYellowBrick();
        }
      }
      //LCD.drawString("isBlack:"+isBlack+",isRed:"+isRed+",yellor:"+brickColor, 1, 1);

    }
  }


}
