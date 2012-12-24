package druidtower;

import java.util.EnumSet;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.map.Path;
import org.powerbot.game.api.wrappers.node.SceneObject;

import data.Data;

public class ToBank extends Node {

	@Override
	public boolean activate() {		
		return !Data.BankArea.contains(Players.getLocal().getLocation()) && Inventory.isFull() && Players.getLocal().getAnimation() == -1;
	}

	@Override
	public void execute() {		
		SceneObject logWest = SceneEntities.getNearest(35997);
		SceneObject unopenedDoor = SceneEntities.getNearest(2554);
		
		if (Data.TowerArea.contains(Players.getLocal().getLocation())) {
			if (unopenedDoor != null) {
				unopenedDoor.interact("Pick-lock");
				Task.sleep(Random.nextInt(700, 900));
			}	
		} else if (!logWest.isOnScreen() && (Data.TowerLogArea.contains(Players.getLocal().getLocation()))) {
			Walking.newTilePath(Data.LogToTower).reverse().traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
			Task.sleep(Random.nextInt(600, 800));
		} else if (logWest.isOnScreen() && Data.TowerLogArea.contains(Players.getLocal().getLocation())) {
			logWest.interact("Walk-across");
			Task.sleep(Random.nextInt(600, 800));
		} else if (Data.BankLogArea.contains(Players.getLocal().getLocation()) && !Data.BankArea.contains(Players.getLocal().getLocation())) {
			Walking.newTilePath(Data.BankToLog).reverse().traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
			Task.sleep(Random.nextInt(600, 800));
		}
		
	}

}
