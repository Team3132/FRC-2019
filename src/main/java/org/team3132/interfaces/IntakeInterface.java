package org.team3132.interfaces;

/**
 * Cargo intake. Spinning omniwheels on a extendable pneumatically driven arm.
 */
import org.strongback.Executable;

public interface IntakeInterface extends SubsystemInterface, Executable, DashboardUpdater {

	public IntakeInterface setExtended(boolean extended);

	/**
	 * @return the state of the intake solenoid. 
	 * */
	public boolean isExtended();
	public boolean isRetracted();
	/**
	 * Detects whether if the intake has the cargo.
	 */
	public boolean hasCargo();

	// Current control.
	public void setMotorOutput(double current);
	public double getMotorOutput();
}
