package org.team3132.subsystems;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.strongback.mock.Mock;
import org.strongback.mock.MockTalonSRX;
import org.team3132.drive.routines.DriveRoutine;
import org.team3132.interfaces.DrivebaseInterface;
import org.team3132.interfaces.DrivebaseInterface.DriveRoutineParameters;
import org.team3132.interfaces.DrivebaseInterface.DriveRoutineType;
import org.team3132.mock.MockDashboard;
import org.team3132.mock.MockLog;

public class TestDrivebase {

	class MockDriveRoutine implements DriveRoutine {
        public String name;
		public int callCount = 0;
		public double leftSpeed = 0;
		public double rightSpeed = 0;
        
        public MockDriveRoutine(String name) {
            this.name = name;
        }
        
		@Override
		public DriveMotion getMotion() {
			callCount++;
			return new DriveMotion(leftSpeed, rightSpeed);
		}

		@Override
		public void enable() {
		}

		@Override
		public void disable() {
		}

        @Override
        public boolean hasFinished() {
            return true;
        }

        @Override
        public String getName() {
            return "mock";
        }
	}
	
    @Test
    public void testDriveRoutine() {
    	MockTalonSRX leftMotor = Mock.TalonSRXs.talonSRX(0);
    	MockTalonSRX rightMotor = Mock.TalonSRXs.talonSRX(0);
    	MockDriveRoutine arcade = new MockDriveRoutine("MockArcade");
        DrivebaseInterface drive = new Drivebase(leftMotor, rightMotor, new MockDashboard(), new MockLog(true));
        // Register this drive routine so it can be used.
        drive.registerDriveRoutine(DriveRoutineType.ARCADE, arcade);
        // Tell the drive subsystem to use it.
        drive.setDriveRoutine(new DriveRoutineParameters(DriveRoutineType.ARCADE));
        int expectedCallCount = 0;

        // Subsystems should start disabled, so shouldn't be calling the DrivedriveRoutine.
        assertEquals(expectedCallCount, arcade.callCount);
        drive.execute(0);
        assertEquals(expectedCallCount, arcade.callCount);
        assertEquals(0, leftMotor.getLastDemand(), 0.01);
        assertEquals(0, rightMotor.getLastDemand(), 0.01); 

        // Enable the drivebase
        arcade.leftSpeed = 0.5;
        arcade.rightSpeed = 0.75;
        drive.enable();
        drive.execute(0); // Should call getMotion() on driveRoutine.
        assertEquals(++expectedCallCount, arcade.callCount);
        // Check that the motors now have power.
        assertEquals(arcade.leftSpeed, leftMotor.getLastDemand(), 0.01);
        assertEquals(arcade.rightSpeed, rightMotor.getLastDemand(), 0.01);

        // Update the speed and see if the motors change.
        arcade.leftSpeed = -0.1;
        arcade.rightSpeed = 1;
        drive.execute(0); // Should call getMotion() on driveRoutine.
        assertEquals(++expectedCallCount, arcade.callCount);
        // Check that the motors now have power.
        assertEquals(arcade.leftSpeed, leftMotor.getLastDemand(), 0.01);
        assertEquals(arcade.rightSpeed, rightMotor.getLastDemand(), 0.01);
        
        // Change driveRoutine and see if the outputs are different
    	MockDriveRoutine cheesy = new MockDriveRoutine("MockCheesy");
    	cheesy.leftSpeed = 1;
    	cheesy.rightSpeed = -1;
        drive.registerDriveRoutine(DriveRoutineType.CHEESY, cheesy);
        // Tell the drive subsystem to use it.
        drive.setDriveRoutine(new DriveRoutineParameters(DriveRoutineType.CHEESY));
    	drive.execute(0);
        assertEquals(1, cheesy.callCount); // first time running this driveRoutine
        assertEquals(cheesy.leftSpeed, leftMotor.getLastDemand(), 0.01);
        assertEquals(cheesy.rightSpeed, rightMotor.getLastDemand(), 0.01);

        // Disable and confirm that the driveRoutine isn't called and the motors are stopped
        drive.disable();
        drive.execute(0); // Should no call getMotion() on driveRoutine.
        assertEquals(expectedCallCount, arcade.callCount);
        assertEquals(0, leftMotor.getLastDemand(), 0.01);
        assertEquals(0, rightMotor.getLastDemand(), 0.01);        
    }

}
