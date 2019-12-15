package org.team3132.mock;

import org.team3132.interfaces.SpitterInterface;
import org.team3132.interfaces.Log;

public class MockSpitter implements SpitterInterface {

    private double targetDutyCycle = 0; 

    public MockSpitter(Log log) {
    }

    @Override
    public SpitterInterface setTargetDutyCycle(double dutyCycle) {
        targetDutyCycle = dutyCycle;
        return this;
    }

    @Override
    public double getTargetDutyCycle() {
        return targetDutyCycle;
    }

    @Override
    public boolean hasCargo() {
        return false;
    }

    @Override
    public String getName() {
        return "MockSplitter";
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
        return true;
    }

    @Override
    public void cleanup() {
    }
}