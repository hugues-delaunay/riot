package io.steria.pox3.got.objectives;

import java.util.List;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Domain;

public class OccupyDomainCard extends ObjectiveCard {

	List<Domain> domains;

	boolean succeed(House house) {
		return false;
	}

}
