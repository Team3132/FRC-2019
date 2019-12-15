package org.team3132.mock;

import org.team3132.drive.routines.DriveRoutine;
import org.team3132.interfaces.DrivebaseInterface;
import org.team3132.interfaces.Log;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class MockDrivebase implements DrivebaseInterface  {
	private DriveRoutineParameters parameters = new DriveRoutineParameters(DriveRoutineType.ARCADE);

	String name = "MockDrivebase";
	Log log;
	
	public MockDrivebase(Log log) {
		this.log = log;
	}

	@Override
	public void execute(long timeInMillis) {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void enable() {
	}

	@Override
	public void disable() {
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void setDriveRoutine(DriveRoutineParameters parameters) {
		this.parameters = parameters;
	}

	@Override
	public DriveRoutineParameters getDriveRoutine() {
		return parameters;
	}

	@Override
	public boolean hasFinished() {
		return true;
	}

	@Override
	public void registerDriveRoutine(DriveRoutineType mode, DriveRoutine routine, ControlMode controlMode) {
	}
}
