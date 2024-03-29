package org.team3132.drive.routines;

import org.team3132.interfaces.DrivebaseInterface.DriveRoutineParameters;

/**
 * Tells the drive wheels to drive forward at a constant power level.
 */
public class ConstantDrive implements DriveRoutine {
	private DriveMotion motion = new DriveMotion(0, 0);
	
	public ConstantDrive() {}
	
	@Override
	public void reset(DriveRoutineParameters parameters) {
		motion = new DriveMotion(parameters.value, parameters.value);
	}

	@Override
	public DriveMotion getMotion() {
		return motion;
	}
	
	@Override
	public String getName() {
		return "ConstantDrive";
	}

	@Override
	public boolean hasFinished() {
		return true;  // Always ready for the next state.
	}
}
