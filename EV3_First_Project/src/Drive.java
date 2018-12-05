import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	public static RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
	public static RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	
	public static void main(String[] args) {
	    forward();
		backward();
		rotate();
	}
	
	private static void forward() {
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
	
	private static void backward() {
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
	
	private static void rotate() {
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



}
