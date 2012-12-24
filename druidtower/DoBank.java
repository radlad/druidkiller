package druidtower;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

import data.Data;

public class DoBank extends Node {

	@Override
	public boolean activate() {
		return Data.BankArea.contains(Players.getLocal().getLocation()) && Inventory.isFull();
	}

	@Override
	public void execute() {
		
		SceneObject bankBooth = SceneEntities.getNearest(Data.bankBooth);
		
		if (!bankBooth.isOnScreen()) {
			Camera.turnTo(bankBooth);
		} else {
			Bank.open();
			Bank.depositInventory();
			Bank.close();
		}
		
	}

}