package org.team3132.mock;

import org.team3132.interfaces.TapeInterface;

public class MockTape implements TapeInterface{
    
    public MockTape(){

    }

    @Override
    public Direction getRecommendation() {
        return Direction.NO_LINE_DETECTED;
    }

    @Override
    public boolean isCarpet(double sensorValue) {
        return false;
    }

    @Override
    public boolean isTape(double sensorValue) {
        return false;
    }

    @Override
    public boolean moveToLeft() {
        return false;
    }

    @Override
    public boolean moveToRight() {
        return false;
    }

    @Override
    public boolean moveStraight() {
        return false;
    }
}