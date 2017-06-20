package io.steria.pox3.got.war;

import io.steria.pox3.got.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Tile;

public interface IArmy { // interface ne prend pas de m�moire, que des fonctions

	int getTotalTroops();
	int getMovedTroops();
	int getReadyTroops();


	boolean attack(IArmy ennemy);

	void move(int Troops, Direction direction);

	void move(Direction direction);

	Tile getPosition();

	House getHouse();

	ArmyState getState();
	
	Player getPlayer();

}
