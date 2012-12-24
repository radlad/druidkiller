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

public class ToTower extends Node {

	@Override
	public boolean activate() {		
		return !Data.TowerArea.contains(Players.getLocal().getLocation()) && !Inventory.isFull() && Players.getLocal().getAnimation() == -1;
	}

	@Override
	public void execute() {		

		SceneObject logEast = SceneEntities.getNearest(35999);
		SceneObject unopenedDoor = SceneEntities.getNearest(2554);
		
		if (!logEast.isOnScreen() && !Data.TowerLogArea.contains(Players.getLocal().getLocation())) {
			Walking.newTilePath(Data.BankToLog).traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
			Task.sleep(Random.nextInt(600, 800));
		} else if (logEast.isOnScreen() && Players.getLocal().getAnimation() == -1 && Data.BankLogArea.contains(Players.getLocal().getLocation())) {
			logEast.interact("Walk-across");
			Task.sleep(Random.nextInt(600, 800));
		} else if (Data.TowerLogArea.contains(Players.getLocal().getLocation()) && !Data.OutsideTower.contains(Players.getLocal().getLocation())) {
			Walking.newTilePath(Data.LogToTower).traverse(EnumSet.of(Path.TraversalOption.SPACE_ACTIONS));
			Task.sleep(Random.nextInt(600, 800));		
		} else if (Data.OutsideTower.contains(Players.getLocal().getLocation())) {
			if (!Data.TowerArea.contains(Players.getLocal().getLocation())) {
				if (unopenedDoor != null) {
					unopenedDoor.interact("Pick-lock");
					Task.sleep(Random.nextInt(1000, 1300));
				}				
			}
		}
		
	}

}
