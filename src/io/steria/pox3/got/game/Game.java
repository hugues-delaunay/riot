package io.steria.pox3.got.game;

import java.util.ArrayList;
import java.util.List;

import io.steria.pox3.got.objectives.ObjectiveCard;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;

/**
 * starting class for launching game
 * 
 * @author AELION
 *
 */
public class Game {

	List<Player> players = new ArrayList<>();
	List<House> houses = new HouseFactory().getAllHouses();
	List<House> availableHouses = new HouseFactory().getAllHouses();
	List<ObjectiveCard> cards;
	List<Round> rounds = new ArrayList<>();

	public Game() {

	}

	public void init() {
		newRound();
	}

	List<House> getAvailableHouses() {
		return availableHouses;
	}

	void SavePlayer(Player player) {
		this.players.add(player);
		this.availableHouses.remove(player.house);
	}

	Round getCurrentRound() {
		return this.rounds.get(this.rounds.size() - 1);
	}

	void newRound() {

		this.rounds.add(new Round());

		boolean firstNotDead = true;
		for (Player player : this.players) {
			if (player.roundState != RoundState.DEAD) {
				player.moves = 3;
				
				if (firstNotDead) {
					player.roundState = RoundState.PLAYING;
					firstNotDead = false;
				} else {
					player.roundState = RoundState.WAITING;
				}

			} else {

				player.moves = 0;

			}
		}
	}
}
