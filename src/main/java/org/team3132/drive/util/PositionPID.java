package org.team3132.drive.util;

import java.util.function.DoubleSupplier;

import org.strongback.components.Clock;
import org.team3132.interfaces.Log;

/**
 * Using a PositionCalc, takes a target speed and calculates what the encoder
 * should be reporting, applying extra power to the motors if they aren't in
 * the target positions.
 * 
 * One of these is created per side of the drivebase to force that the robot
 * turns reliably even with high traction wheels.
 * 
 * This is likely overkill, but we have had issues in the past with fast reliable
 * turning due to static and dynamic friction.
 */
 public class PositionPID {

    private DoubleSupplier targetSpeed;
    private DoubleSupplier encoderPos;
    private DoubleSupplier encoderSpeed;
    private PositionCalc calc;
    private double lastError = 0;
    // Coefficients on how the velocity and acceleration affect the motor power.
    private double kV = 0, kA = 0;
    // Position PID values.
    private double kP = 0, kI = 0, kD = 0;
    private boolean enabled = true;

    public PositionPID(String name, DoubleSupplier targetSpeed, double maxJerk,
         DoubleSupplier encoderPos, DoubleSupplier encoderSpeed, Clock clock, Log log) {
        this.targetSpeed = targetSpeed;
        this.encoderPos = encoderPos;
        this.encoderSpeed = encoderSpeed;
        calc = new PositionCalc(encoderPos.getAsDouble(), encoderSpeed.getAsDouble(), maxJerk, clock, log);
        log.register(true, () -> calc.getSpeed(), "%s/targetSpeed", name)
           .register(true, () -> calc.getPosition(), "%s/targetPos", name);
    }

    public void setVAPID(double kV, double kA, double kP, double kI, double kD) {
        this.kV = kV;
        this.kA = kA;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void reset() {
        calc.reset(encoderPos.getAsDouble(), encoderSpeed.getAsDouble());
        lastError = 0;
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public double getMotorPower() {
        if (!enabled) return 0;
        calc.setTargetSpeed(targetSpeed.getAsDouble());
        double targetPosition = calc.update();
        double error = targetPosition - encoderPos.getAsDouble();
        double power = kP * error +
            kD * (error - lastError) +
            kV * calc.getSpeed() + kA * calc.getAccel();
        lastError = error;
        return power;
    }
 }