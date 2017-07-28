package io.steria.pox3.got.tile;

import java.util.Optional;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.war.Direction;

public class World {

	Tile[][] tiles = new Tile[12][11];
	boolean winter = false;
	int winterLattitude = 0;

	public World() {

	}

	public Domain getWinterfell7() {
		return (Domain) this.get(3, 3);
	}

	public Domain getTheEyrie1() {
		return (Domain) this.get(3, 4);
	}

	public Domain getThrone() {
		return (Domain) this.get(4, 7);
	}

	public Domain getMeereen() {
		return (Domain) this.get(8, 9);
	}

	public void generate() {
		assignFreeDomain(1, 0, 4, 2, "North");
		assignFreeDomain(8, 2, 2, 2, "Braavos");
		assignFreeDomain(8, 4, 2, 2, "Pentos");
		assignFreeDomain(8, 6, 2, 2, "Volantis");
		assignFreeDomain(10, 4, 1, 6, "Red Waste");

		House stark = new HouseFactory().getStark();
		this.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);

		House greyjoy = new HouseFactory().getGreyjoy();
		this.assignDomainWithHouse(1, 4, 2, 2, "Iron Island", greyjoy);

		House arryn = new HouseFactory().getArryn();
		this.assignDomainWithHouse(3, 4, 2, 2, "The Eyrie", arryn);

		House lannister = new HouseFactory().getLannister();
		this.assignDomainWithHouse(1, 6, 2, 2, "Casterly Rock", lannister);

		House baratheon = new HouseFactory().getBaratheon();
		this.assignDomainWithHouse(3, 6, 2, 2, "Kings Landing", baratheon);

		House tyrell = new HouseFactory().getTyrell();
		this.assignDomainWithHouse(1, 8, 2, 2, "High Garden", tyrell);

		House martell = new HouseFactory().getGreyjoy();
		this.assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);

		House targaryen = new HouseFactory().getTargaryen();
		this.assignDomainWithHouse(8, 8, 2, 2, "Meereen", targaryen);

		fillWater();
	}

	void assignFreeDomain(int xOrigin, int yOrigin, int xSize, int ySize, String name) {

		assignDomainWithHouse(xOrigin, yOrigin, xSize, ySize, name, null);

	}

	void assignDomainWithHouse(int xOrigin, int yOrigin, int xSize, int ySize, String name, House house) {

		int number = 1;

		for (int y = yOrigin; y < yOrigin + ySize; y++) {

			for (int x = xOrigin; x < xOrigin + xSize; x++) {

				Domain domain = new Domain(x, y, name + "-" + number);

				tiles[x][y] = domain;
				domain.house = house == null ? Optional.empty() : Optional.of(house);

				number++;
			}

		}
	}

	void fillWater() {

		for (int x = 0; x <= 11; x++) {
			for (int y = 0; y <= 10; y++) {

				Tile tile = this.tiles[x][y];

				if (tile == null) {
					tiles[x][y] = new WaterTile(x, y);
				}

			}
		}
	}

	 public boolean allowMove(Tile destination, boolean boat) {

		if (destination instanceof WaterTile) {
			return boat;
		} else {
			return true;
		}
	}

	boolean isWinter() {
		return false;
	}

	void startWinter() {
		this.winter = true;
	}

	public void display() {

		for (int y = 0; y <= 10; y++) {

			for (int x = 0; x <= 11; x++) {
				Tile tile = this.tiles[x][y];
				System.out.println(tile);

			}

		}

	}

	public static void main(String[] args) {

		World map = new World();
		map.generate();
		map.display();

	}

	public Tile get(int x, int y) {

		return this.tiles[x][y];
	}

	public Optional<Tile> neighbour(Tile tile, Direction direction) {

		int x = tile.x;
		int y = tile.y;
		switch (direction) {

		case NORTH:
			y--;
			break;

		case SOUTH:
			y++;
			break;

		case WEST:
			x--;
			break;

		case EAST:
			x++;
			break;

		default:
			throw new IllegalStateException();
		}

		if (x < 0 || x >= this.tiles.length || y < 0 || y >= this.tiles[0].length) {
			return Optional.empty();
		}
		Tile neighbour = this.tiles[x][y];
		return Optional.of(neighbour);
	}

}
