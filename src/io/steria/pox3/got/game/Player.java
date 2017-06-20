package io.steria.pox3.got.game;

import io.steria.pox3.got.objectives.ObjectiveCard;
import io.steria.pox3.got.story.House;

public class Player {

	String name;
	House house;
	ObjectiveCard card;
	PlayerState state = PlayerState.PLAYING;
	RoundState roundState = RoundState.WAITING;

	// Bidirection, aie aie aie

	Game game;
	Round currentRound;

	int moves = 3;

	public void decreaseMoves() {
		if (this.roundState == RoundState.WAITING) {
			throw new PlayerRoundedException();
		}
		this.moves--;
	}

	public Player(String name, House house) {
		this.name = name;
		this.house = house;
		//bidirection
		this.house.setPlayer(this);
		//but this link never moves. Not so bad
	}

	boolean chooseName(String pName) {

		this.name = pName;
		return true;
	}

	boolean chooseHouse(House pHouse) {

		this.house = pHouse;
		return true;

	}

	ObjectiveCard selectCard() {
		return null;
	}

}
