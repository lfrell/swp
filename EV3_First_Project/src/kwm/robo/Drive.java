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
    while(Sensor.isBlackOrRed()) {
      forward();
    }
    stop();
    

  }
  
  //-------------------------------------------------------------
  // forward() - drives forward
  //------------------------------------------------------------- 
  public static void forward() {
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
  
  //-------------------------------------------------------------
  // backward() - drives backward
  //------------------------------------------------------------- 
  public static void backward() {
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
  //-------------------------------------------------------------
  // driveCurve() - includes backward, forward and rotation
  // to drive a curve
  //------------------------------------------------------------- 
  public static void driveCurve() {
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
  
  //-------------------------------------------------------------
  // rotate() - based on parameter leftRight, it rotates to 
  // the defined direction, the parameter degree defines the 
  // size of the rotation (implements rotateLeft and rotateRight)
  //------------------------------------------------------------- 
  public static void rotate(int leftRight, int degree) {
    
  }
  
  //-------------------------------------------------------------
  // stop() - the robo stops
  //------------------------------------------------------------- 
  public static void stop() {
    
  }
  
}
