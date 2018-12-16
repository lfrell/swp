package kwm.test;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;

//hab was geändert...

public class KWM {
	public static void main(String[] args) {
		//testMotors();
		//combineMotorAndTouch();
		testColorSensor(1); //hier gibt es 3 verschiedene modi ..
		System.exit(0);
	}		

	/*	Klasse testet die Motoren	medium ist der große, large sind die kleinen ... left motor ist zB auf Port b ..  MotorPort.B*/
	private static void testMotors() {
		LCD.drawString("Hello KWM! Testing the motors", 0, 4);
		
		EV3MediumRegulatedMotor medMotor = new EV3MediumRegulatedMotor(MotorPort.A);
		RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	    RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	    		
		leftMotor.resetTachoCount();
	    rightMotor.resetTachoCount();
	    leftMotor.rotateTo(0);
	    rightMotor.rotateTo(0);
	    leftMotor.setSpeed(400);
	    rightMotor.setSpeed(400);
	    leftMotor.setAcceleration(800);
	    rightMotor.setAcceleration(800);
	    	    
	  medMotor.forward();
		leftMotor.forward();
		rightMotor.forward();
		Delay.msDelay(5000); //braucht man .. es sieht so aus als würde er nix machen, alle sachen sind in eigenem thread der im hintergrund läuft .. in dem fall würde er alle x sekunden das ding vorwärts bewegen .... man kann das auch mit while schleife machen
		medMotor.close();
		leftMotor.close();
		rightMotor.close();
	}
	
	
	//**		Teil zu den Touch Sensoren		***//
	//while(!Button.ESCAPE.isDOWN()) {..} solagne ich den button nicht drücke ist er in einer schleife
	
	private static void testTouchSensor() {
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);
		LCD.clear();
		
		while (!Button.ESCAPE.isDown()) {
			int sampleSize = touch.sampleSize();
			float[] sample = new float[sampleSize];
			touch.fetchSample(sample, 0);
	        LCD.drawString("TS: " + sample[0], 0, 0);
			System.out.println("TS: " + sample[0]);
			Delay.msDelay(500);
	    }
		touch.close();
		LCD.clear();
	}
	
	private static void testNXTTouchSensor() {
		NXTTouchSensor touchSensor = new NXTTouchSensor(SensorPort.S3);
		LCD.clear();
		
		while (!Button.ESCAPE.isDown()) {
			int sampleSize = touchSensor.sampleSize();
			float[] sample = new float[sampleSize];
			touchSensor.fetchSample(sample, 0);
	        LCD.drawString("TS: " + sample[0], 0, 0);
			System.out.println("TS: " + sample[0]);
			Delay.msDelay(500);
	    }
		touchSensor.close();
		LCD.clear();
	}

	//sobald gedrückt wird wird er ausgeschaltet
	private static void combineMotorAndTouch() {
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);
		RegulatedMotor leftMotor = Motor.B;
	    RegulatedMotor rightMotor = Motor.C;
	    
	    leftMotor.setAcceleration(1000); //wie schnell sollen die vorwärts fahren die robots
	    rightMotor.setAcceleration(1000);
	    
	    boolean drive = true;
	    while(drive) {
	    	leftMotor.forward();//er fährt ein stück vorwerts
			rightMotor.forward();	
			int sampleSize = touch.sampleSize(); //wert des touch sensors abfragen
			float[] sample = new float[sampleSize]; //array soll so groß sein wie samples von dem sensor daherkommen
			touch.fetchSample(sample, 0); //ergebnis abfragen und in sample array schreiben .....  //offset 0 , immer von forne zu lesen anfangen
			if(sample[0]!=0) { //nur wenn sample 1 ist hat er gedrückt, dann kommt 1 vom touch sensor zurück, dann soll schleife beendet werden
				drive = false;
			}
	    }
	    leftMotor.stop();
	    rightMotor.stop();
	    //wichtig ist es die motoren immer mit close zu schließen sonst fehler support anynews ...!!
	    touch.close();
	    leftMotor.close();
	    rightMotor.close();
	}
	
	//**		farbsensoren testen **/
	//entweder er messt das rotlicht oder das umgebungslicht ...... umgebungslicht ist das ungeneuste .. eher rotlicht oder colour modus verwenden
	//gelben, grünen und schwarzen linien drunter legen und mit den robo testen ob er die linien gut erkennt
	
	
	
/*
	
	Für ColorId	 
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int YELLOW = 3;
	public static final int MAGENTA = 4;
	public static final int ORANGE = 5;
	public static final int WHITE = 6;
    public static final int BLACK = 7;
    public static final int PINK = 8;
    public static final int GRAY = 9;
    public static final int LIGHT_GRAY = 10;
    public static final int DARK_GRAY = 11;
    public static final int CYAN = 12;
    public static final int BROWN = 13;
    public static final int NONE = -1;
	 */
	
	private static void testColorSensor(int mode) {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
		colorSensor.setFloodlight(false);
	    LCD.drawString("Init", 2, 2);
	    LCD.setAutoRefresh(false);
	    
	    SensorMode color = null;
	    
	    switch(mode) {
	    	case 1: color = colorSensor.getColorIDMode(); break;
	    	case 2: color = colorSensor.getRedMode(); break;
	    	case 3: color = colorSensor.getAmbientMode(); break;
	    	default: colorSensor.getColorIDMode();
	    }
	    

		float[] colorSample = new float[color.sampleSize()];
		
		while (!Button.ESCAPE.isDown()) {
			color.fetchSample(colorSample, 0);
			
			LCD.refresh();
			LCD.clear();
			LCD.drawString("ColorId: " + colorSample[0], 1, 1);
			Delay.msDelay(500);
		}
		
		colorSensor.close();
	}
	
		
	/*The theoretical range of the sensor is 0,04 to 2.54 meter. */
	private static void testUltrasonicSensor() {
		NXTUltrasonicSensor us = new NXTUltrasonicSensor(SensorPort.S3);
		SampleProvider distance = us.getDistanceMode();
		//gives the average of the last 5 samples
		SampleProvider average = new MeanFilter(distance,5);
		float[] sample = new float[average.sampleSize()];
		LCD.clear();

		while (!Button.ESCAPE.isDown()) {
			average.fetchSample(sample, 0);
	        LCD.drawString("US: " + sample[0], 0, 0);
			System.out.println("US: " + sample[0]);
			Delay.msDelay(500);
	    }
		us.close();
		LCD.clear();
	}
	
	/* 0 - sehr nahe, 100 weit entfernt */
	private static void testInfraredSensor() {
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S3);
		SampleProvider distance = irSensor.getDistanceMode();
		//gives the average of the last 5 samples
		SampleProvider average = new MeanFilter(distance,5);
		LCD.clear();

		while (!Button.ESCAPE.isDown()) {
			float[] sample = new float[average.sampleSize()];
			average.fetchSample(sample, 0);
	        LCD.drawString("US: " + sample[0], 0, 0);
			System.out.println("US: " + sample[0]);
			Delay.msDelay(500);
	    }
		irSensor.close();
		LCD.clear();
		irSensor.close();
	}
}
