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
  static final RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
  static final RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
  
  //-------------------------------------------------------------
  // driveOnRoadWay - check the desk for the black and red
  // lines (LighSensor)
  //-------------------------------------------------------------  
  public static void driveOnRoadWay() {
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
  public static void driveSetUp() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.rotateTo(0);
    rightMotor.rotateTo(0);
    leftMotor.setSpeed(200);
    rightMotor.setSpeed(200);
    leftMotor.setAcceleration(400);
    rightMotor.setAcceleration(400);
  }
  //-------------------------------------------------------------
  // forward() - drives forward
  //------------------------------------------------------------- 
  public static void forward() {

    driveSetUp();
    leftMotor.forward();
    rightMotor.forward();
    //Delay.msDelay(5000);
  }
  
  //-------------------------------------------------------------
  // backward() - drives backward
  //------------------------------------------------------------- 
  public static void backward() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    driveSetUp();
    leftMotor.backward();
    rightMotor.backward();
  }
  //-------------------------------------------------------------
  // driveCurve() - includes backward, forward and rotation
  // to drive a curve
  //------------------------------------------------------------- 
  public static void driveCurve() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);
    driveSetUp();
    //driveSetUp();
    //rightMotor.setAcceleration(400);
    //Funktion die dir immer Speed zur�ckgibt
    //leftMotor.forward();
    //rightMotor.forward();
    Delay.msDelay(700);

    
    backward();
    rotate(0); //1 links, 0 rechts
    forward();
  }
  
  //-------------------------------------------------------------
  // rotate() - based on parameter leftRight, it rotates to 
  // the defined direction, the parameter degree defines the 
  // size of the rotation (implements rotateLeft and rotateRight)
  //------------------------------------------------------------- 
  public static void rotate(int leftRight) {
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

    Delay.msDelay(1300);
  }
  
  //-------------------------------------------------------------
  // stop() - the robo stops
  //------------------------------------------------------------- 
  public static void stop() {
    leftMotor.close();
    rightMotor.close();
  }
  
}
