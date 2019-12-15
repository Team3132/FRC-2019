package org.team3132.mock;

import org.team3132.interfaces.VisionInterface;

public class MockVision implements VisionInterface {

	TargetDetails details = new TargetDetails();

	public MockVision() {
		details.targetFound = false;
		details.seenAtSec = 0;
	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public TargetDetails getTargetDetails() {
		return details;
	}
}
