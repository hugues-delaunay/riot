package io.steria.pox3.got.tile;

import java.util.Optional;
import java.util.Random;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.war.IArmy;

public class Domain extends Tile {

	String name;
	int population;
	Optional<House> house = Optional.empty();
	static Random seed = new Random();

	public Domain(int x, int y, String name) {
		super(x,y);
		this.name = name;

		this.population = seed.nextInt(10) + 1;
	}

	IArmy createArmy() {
		return null;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
