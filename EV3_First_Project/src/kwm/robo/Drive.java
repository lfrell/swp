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
  

  final double rad = 3.3*Math.PI;
  
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
 /* public void driveOnRoadWay() {
    while(!Sensor.checkAbyss()) {
      if(Sensor.isBlackOrRed())
    	forward();
    }
    //check colorsensor for intercept
    //b
    stop();  
    driveCurve(0);
    stop();
    
  }*/
  
  public void driveSetUp() {
    LCD.drawString("Hello KWM! Testing the motors", 0, 4);

    leftMotor.resetTachoCount();
    rightMotor.resetTachoCount();
    leftMotor.setSpeed(200);
    rightMotor.setSpeed(200);
    //leftMotor.setAcceleration(400);
    //rightMotor.setAcceleration(400);

  }
  //-------------------------------------------------------------
  // forward() - drives forward
  //------------------------------------------------------------- 
  public void forward() {
    driveSetUp();
    leftMotor.forward();
    rightMotor.forward();
	//Delay.msDelay(100); //braucht man .. es sieht so aus als w�rde er nix machen, alle sachen sind in eigenem thread der im hintergrund l�uft .. in dem fall w�rde er alle x sekunden das ding vorw�rts bewegen .... man kann das auch mit while schleife machen
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
	//fdkf�lk
  }
  
  
  //-------------------------------------------------------------
  // rotate() - based on parameter leftRight, it rotates to 
  // the defined direction, the parameter degree defines the 
  // size of the rotation (implements rotateLeft and rotateRight)
  //parameter: leftRight = 1 links, 0 rechts; int degrees=zb 90 degrees; bool positionWithCurve=if true it drives a curve and  considers the 7 cm of the distance of the lightsensor to the middle of the robo!
  //------------------------------------------------------------- 
  public void rotate(int leftRight, int degrees, boolean positionWithCurve) {
	  int radius=1; //die kurve in cm die er f�hrt

	 if (positionWithCurve)
		  radius = 7; //nicht �ndern!!! ist die exakte Entfernung vom Lichtsensor zur Robo Mitte die er ber�cksichtigt beim rotaten!
		  
    double drehi = (radius*Math.PI*2*360)/rad/360*degrees;
  	double dreha = ((radius+10.5)*Math.PI*2*360)/rad/360*degrees;

  	int speed = (int) (200/dreha*drehi);
  	
  	if(leftRight==0) {
  		rightMotor.setSpeed(speed);

    	leftMotor.rotate((int)dreha,true);
    	rightMotor.rotate((int)drehi);
    	
    	rightMotor.setSpeed(200);

    }else {
  		leftMotor.setSpeed(speed);

    	rightMotor.rotate((int)dreha,true);
    	leftMotor.rotate((int)drehi);
    	
    	leftMotor.setSpeed(200);
    }

    Delay.msDelay(1000);		//how far back?
    
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
