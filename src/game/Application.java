package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 *
 * @author Huang GuoYueYang, Kuah Jia Chen, Lim Fluoryynx
 */
public class Application {

	private static GameMap firstGameMap;
	private static GameMap secondGameMap;

	/**
	 * The main method to initialize the game map and add necessary instances to the game map
	 * @param args -
	 */
	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new WarpPipe());

		List<String> firstMap = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				"........................................##.......##.............................",
				".........................................#..C.C###..............................",
				".........+.......................+.....###____##.##................+............",
				"......................................#+#_____####+.............................",
				".......................................+#______###..............................",
				".......................................#+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		List<String> secondMap = Arrays.asList(
				"C...............L.......................##......................+..........",
				"..........L..........L..........L.........#..............L.................",
				"...........................................#...............................",
				".....L........+.....L........................#........L....................",
				".............................L.....##.......##.............................",
				"............L.......................#......##....................L.........",
				".......+....................+.....###____#####.......L........+............",
				"............L....................#+#_____###++...............L.............",
				"......................L...........+#______###....................L.........",
				"..................................#+#_____###.......L......................",
				"........L............+......................##.............+......L........");

		firstGameMap = new GameMap(groundFactory, firstMap);
		world.addGameMap(firstGameMap);

		secondGameMap = new GameMap(groundFactory, secondMap);
		world.addGameMap(secondGameMap);


		Actor mario = new Player("Mario", 'm', 100);
		world.addPlayer(mario, firstGameMap.at(42, 10));
		firstGameMap.at(44, 10).addItem(new PowerStar());
		firstGameMap.at(45, 10).addItem(new SuperMushroom());
		firstGameMap.at(44, 10).addItem(new Coin(10000));
		firstGameMap.at(42, 11).addActor(new Toad());
		firstGameMap.at(35, 10).addActor(new Koopa());
		//firstGameMap.at(45, 10).addItem(new FireFlower());
		secondGameMap.at(3,3).addActor(new PrincessPeach());
		secondGameMap.at(3,4).addActor(new Bowser());
		firstGameMap.at(48,9).addActor(new FlyingKoopa());
		world.run();

	}

	public static GameMap getFirstMap() {
		return firstGameMap;
	}

	public static GameMap getSecondGameMap() {
		return secondGameMap;
	}
}
