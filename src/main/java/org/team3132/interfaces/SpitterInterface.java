package org.team3132.interfaces;

import org.strongback.Executable;

public interface SpitterInterface extends SubsystemInterface, Executable, DashboardUpdater {

    /**
     * Sets the duty cycle on the spitter.
     */
    public SpitterInterface setTargetDutyCycle(double dutyCycle);
    public double getTargetDutyCycle();

    public boolean hasCargo();
}