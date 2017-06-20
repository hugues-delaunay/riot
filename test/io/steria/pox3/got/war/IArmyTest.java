package io.steria.pox3.got.war;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.tile.Domain;
import io.steria.pox3.got.tile.World;

public class IArmyTest {

	IArmy army, bigArmy;

	World world;
	Domain winterfell3;
	HouseFactory factory = new HouseFactory();
	House stark = factory.getStark();
	House lannister = factory.getLannister();

	@Before
	public void setUp() throws Exception {

		world = new World();
		world.generate();

		winterfell3 = (Domain) world.get(3, 2);
		army = new Army(3, stark, winterfell3);
		winterfell3.setArmy(army);
		
		Domain winterfell8 = (Domain) world.get(4, 3);
		bigArmy = new Army(9, lannister, winterfell8);		
		bigArmy.move(9, Direction.WEST);
	}

	@Test
	public void testGetTroops() {
		assertEquals(3, army.getTotalTroops());
		assertEquals(3, army.getReadyTroops());
		assertEquals(3, army.getMovedTroops());

	}

	@Test
	public void testMoveIntDomain() {
		army.move(3, Direction.SOUTH);
		assertEquals(3, army.getPosition().getX());
		assertEquals(3, army.getPosition().getY());
		assertEquals(1, winterfell3.getArmy().getReadyTroops());
		Domain winterfell7 = (Domain) world.get(3, 3);
		assertEquals(2, winterfell7.getArmy().getMovedTroops());

	}

	@Test
	public void testMoveDomain() {
		army.move(Direction.SOUTH);
		assertEquals(3, army.getPosition().getX());
		assertEquals(3, army.getPosition().getY());
		assertEquals(0, winterfell3.getArmy().getReadyTroops());
		Domain winterfell7 = (Domain) world.get(3, 3);
		assertEquals(3, winterfell7.getArmy().getMovedTroops());
	}

	@Test
	public void testGetPosition() {
		assertEquals(winterfell3, army.getPosition());
	}

	@Test
	public void testGetHouse() {
		assertEquals(stark, army.getHouse());

	}

	@Test
	public void testGetState() {
		assertEquals(ArmyState.IDLE, army.getState());
		army.move(Direction.SOUTH);
		assertEquals(ArmyState.IDLE, army.getState());
	}

	@Test
	public void testAttack() {
		assertFalse(army.attack(bigArmy));
		assertEquals(ArmyState.DEAD, army.getState());
		assertEquals(ArmyState.WON, bigArmy.getState());

	}

}
