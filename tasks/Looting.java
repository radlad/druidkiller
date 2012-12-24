package tasks;

import methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.net.GeItem;
import org.powerbot.game.api.wrappers.node.GroundItem;

import data.Data;

public class Looting extends Node {
	
	public static Filter<GroundItem> ItemFilter = new Filter<GroundItem>(){
		@Override
		public boolean accept(GroundItem n) {
			for(Integer i : Data.lootItems) {
				return n != null && n.getLocation().canReach() && 
						Data.lootItems.contains(n.getId()) && 
						Data.TowerArea.contains(n);									
			}
			return false;
		}		
	};	

	@Override
	public boolean activate() {
		GroundItem item = GroundItems.getNearest(ItemFilter);
		return item != null && Players.getLocal().getInteracting() == null && 
				!Players.getLocal().isMoving() && !Inventory.isFull();
	}

	@Override
	public void execute() {
		int currentMouseX = Mouse.getX();
        int currentMouseY = Mouse.getY();
        int currentPitch = Camera.getPitch();
        int currentAngle = Camera.getYaw();
		GroundItem item = GroundItems.getNearest(ItemFilter);	
		Data.oldInvCount = Inventory.getCount(true);
		Data.stackSize = item.getGroundItem().getStackSize();
		
		if (!Data.itemprices.containsKey(item.getId())) {
			GeItem itemGE = GeItem.lookup(item.getId());	
			Data.itemprices.put(item.getId(), itemGE.getPrice());
		}		
		
		
		if (item != null) {
			if (!item.isOnScreen()) {
				Camera.setPitch(Random.nextInt(10, 25));
				Camera.turnTo(item);
			} else if (!Inventory.isFull()) {		
				System.out.println("Picking up " + item.getGroundItem().getName() + ".");
				if (Data.lootTries > 0) {
					System.out.println("Picking up " + item.getGroundItem().getName() + ". (more than 1 try)");
				}
				Data.status = "Picking up " + item.getGroundItem().getName() + ".";				
					if(item.click(false) && Menu.isOpen() && Menu.contains("Take")) {						
						Menu.select("Take", item.getGroundItem().getName());
							if (!Data.lootedlist.containsKey(item.getId())) {
								Data.lootedlist.put(item.getId(), item.getGroundItem().getStackSize());
							} else {
								Data.lootedlist.put(item.getId(), Data.lootedlist.get(item.getId())+item.getGroundItem().getStackSize());
							}
						Task.sleep(Random.nextInt(400, 600));
						
					}				
				Mouse.move(currentMouseX + Random.nextInt(-20, 20), currentMouseY + Random.nextInt(-20, 20));
				Camera.setPitch(currentPitch + Random.nextInt(20, 70));
		        Camera.setAngle(currentAngle + Random.nextInt(-200, 200));
			}		
		}		
		
		Methods.getProfit();		

	}

}
