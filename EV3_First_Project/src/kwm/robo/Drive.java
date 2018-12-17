package kwm.robo;

import kwm.robo.Sensor;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

//-------------------------------------------------------------------- 
//Strategy.java 
//includes the basic functions to drive and rotate
//
//Author: Michaela Buschberger <S1710456005@students.fh-hagenberg.at> 
//Created: 16.12.2018 
//--------------------------------------------------------------------
public class Drive {
  //constants of motor setup
  public RegulatedMotor leftMotor;
  public RegulatedMotor rightMotor;
  public boolean isOpen = false;
  public Drive() {
	  leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
	  rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	  this.isOpen = true;
	 	  
  }
  
  public boolean getIsOpen() {
	  return this.isOpen;
  }
  
  public void init() {
	  leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
	  rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	  this.isOpen = true;
	}
  
  //-------------------------------------------------------------
  // driveOnRoadWay - check the desk for the black and red
  // lines (LighSensor)
  //-------------------------------------------------------------  
  public void driveOnRoadWay() {
    while(!Sensor.checkAbyss()) {
      if(Sensor.isBlackOrRed())
    	forward();
    }
    //check colorsensor for intercept
    //b
    stop();  
    driveCurve();
    stop();
    
  }
  public void driveSetUp() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.setSpeed(100);
    rightMotor.setSpeed(100);
    leftMotor.setAcceleration(200);
    rightMotor.setAcceleration(200);
  }
  //-------------------------------------------------------------
  // forward() - drives forward
  //------------------------------------------------------------- 
  public void forward() {
    driveSetUp();
    leftMotor.forward();
    rightMotor.forward();
	Delay.msDelay(2000); //braucht man .. es sieht so aus als w�rde er nix machen, alle sachen sind in eigenem thread der im hintergrund l�uft .. in dem fall w�rde er alle x sekunden das ding vorw�rts bewegen .... man kann das auch mit while schleife machen
	//leftMotor.close();
	//rightMotor.close();
  }
  
  
  //-------------------------------------------------------------
  // backward() - drives backward
  //------------------------------------------------------------- 
  public void backward() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    //driveSetUp();
    driveSetUp();
    leftMotor.backward();
    rightMotor.backward();
    Delay.msDelay(1000);
  }
  //-------------------------------------------------------------
  // driveCurve() - includes backward, forward and rotation
  // to drive a curve
  //------------------------------------------------------------- 
  public void driveCurve() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);
    //driveSetUp();
    //driveSetUp();
    //rightMotor.setAcceleration(400);
    //Funktion die dir immer Speed zur�ckgibt
    //leftMotor.forward();
    //rightMotor.forward();
    Delay.msDelay(700);

    
    backward();
    rotate(0); //1 links, 0 rechts
    //forward();
  }
  
  
  //-------------------------------------------------------------
  // rotate() - based on parameter leftRight, it rotates to 
  // the defined direction, the parameter degree defines the 
  // size of the rotation (implements rotateLeft and rotateRight)
  //------------------------------------------------------------- 
  public void rotate(int leftRight) {
    if(leftRight==0) {
      rightMotor.setAcceleration(400);
      //Funktion die dir immer Speed zur�ckgibt
      leftMotor.forward();
      //rightMotor.forward();
    }else {
      leftMotor.setAcceleration(400);
      //Funktion die dir immer Speed zur�ckgibt
      rightMotor.forward();
      //rightMotor.forward();
    }

    Delay.msDelay(2000);
  }
  
  //-------------------------------------------------------------
  // stop() - the robo stops
  //------------------------------------------------------------- 
  public void stop() {
    leftMotor.close();
    rightMotor.close();
    this.isOpen=false;
  }
  
}
