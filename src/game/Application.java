package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.*;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature());

			List<String> map = Arrays.asList(
					"..........................................##..........+.........................",
					"............+............+..................#...................................",
					"............................................#...................................",
					".............................................##......................+..........",
					"...............................................#................................",
					"................................................#...............................",
					".................+................................#.............................",
					".......................................###.......##.............................",
					".......................................#.#......##..............................",
					".........+.......................+.....###____####.................+............",
					".......................................+#_____###++.............................",
					".......................................+#______###..............................",
					"........................................+#_____###..............................",
					"........................+........................##.............+...............",
					"...................................................#............................",
					"....................................................#...........................",
					"...................+.................................#..........................",
					"......................................................#.........................",
					".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor mario = new Player("Mario", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));
			gameMap.at(44, 10).addItem(new PowerStar());
		gameMap.at(46, 10).addItem(new SuperMushroom());

			gameMap.at(40, 8).addActor(new Toad());
			gameMap.at(35, 10).addActor(new Koopa());

			world.run();

	}
}
