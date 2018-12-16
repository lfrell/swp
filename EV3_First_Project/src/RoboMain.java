import kwm.robo.*;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

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
	 
	 //checkAbyss();
	  returnColor();
	  
  }
  
  public static Sensor sensor = new Sensor();
  public static Drive drive = new Drive();
  public static Strategy strategy = new Strategy();

	
  //returns true, if he found an abyss and stops
  public static void checkAbyss() {
		sensor.checkAbyss();
		drive.stop();
  }
  
  //returns yellow constant code if he sees a yellow brick
  public static void returnColor() {
	  sensor.returnColor();
	  //strategy.moveYellowBrick();
  }


}
