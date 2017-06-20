package io.steria.pox3.got.objectives;

import io.steria.pox3.got.story.House;

public class AnnihilateHouseCard extends ObjectiveCard {

	House houseToBeDestroyed;
	// DJ: i think we'll need house sometimes

	boolean succeed(House house) {
		return false;
	}

}
