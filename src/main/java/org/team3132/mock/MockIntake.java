package org.team3132.mock;

import org.team3132.interfaces.IntakeInterface;
import org.team3132.interfaces.Log;


/**
 * Mock subsystem responsible for the intake
 */
public class MockIntake implements IntakeInterface {
	private double output = 0;
	private boolean isExtended = false;

	public MockIntake(Log log) {
	}

	@Override
	public IntakeInterface setExtended(boolean extended) {
		isExtended = extended;
		return this;
	}

	@Override
	public boolean isExtended() {
		return isExtended;
	}

	@Override
	public boolean isRetracted() {
		return !isExtended;
	}

	@Override
	public void setMotorOutput(double current){
		output = current;
	}
	
	public double getMotorOutput(){
		return output;
	}

	@Override
	public boolean hasCargo() {
		return false;
	}
	
	@Override
	public String getName() {
		return "MockIntake";
	}

	@Override
	public void enable() {
	}

	@Override
	public void disable() {
	}

	@Override
	public void execute(long timeInMillis) {
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void cleanup() {
	}	
}