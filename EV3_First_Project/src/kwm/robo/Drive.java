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
    while(Sensor.isBlackOrRed()&&!Sensor.checkAbyss()) {
      if(!Sensor.checkAbyss()) forward();
      else stop(); 
    }
    //check colorsensor for intercept
    //b
    stop();  
    driveCurve();
    
  }
  public static void driveSetUp() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.rotateTo(0);
    rightMotor.rotateTo(0);
    leftMotor.setSpeed(500);
    rightMotor.setSpeed(500);
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
    rightMotor.setAcceleration(400);
    //Funktion die dir immer Speed zurückgibt
    leftMotor.forward();
    //rightMotor.forward();
    Delay.msDelay(1300);
    
    rightMotor.forward();
    leftMotor.forward();
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
    leftMotor.close();
    rightMotor.close();
  }
  
}
