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
	public Drive drive = new Drive();
	
	
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
	    drive.driveCurve();
	    drive.stop();
	    
  }

}
