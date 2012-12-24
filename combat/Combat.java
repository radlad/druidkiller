package combat;

import methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.interactive.Player;

import data.Data;

public class Combat extends Node {
	
	public static Filter<NPC> MobFilter = new Filter<NPC>(){		
		@Override
		public boolean accept(NPC n) {
				return n != null && n.getId() == Data.chaosDruid && n.getLocation().canReach() 						 
						&& n.getHpPercent() > 0 && !n.getLocation().equals(Players.getLocal().getLocation())
						&& Data.TowerArea.contains(n.getLocation());

		}		
	};

	@Override
	public boolean activate() {
		return Players.getLocal() != null && Players.getLocal().getInteracting() == null && Data.TowerArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		NPC n = NPCs.getNearest(MobFilter);
		Player p = Players.getLocal();
		Data.oldKills = Data.killCounter;	
		Data.lootTries = 0;
		
		if (Widgets.get(1218, 73).isOnScreen()) {
			Widgets.get(1218, 73).click(true);
		}	

		if (Methods.getAdrPercent() == 100) {				
			Methods.useMomentum();						
		}
		
		
		if (n == null) {
			Task.sleep(50);		
			Data.status = "Waiting...";
		} else {			
			if (n.isOnScreen()) {
				if (p.getInteracting() == null && !p.isMoving()) {
					Data.status = "Fighting Chaos Druid!";
					n.interact("Attack");
					System.out.println("Attacking druid.");
					Task.sleep(Random.nextInt(400, 600));
				}					
			} else if (!n.isOnScreen()) {
				Camera.setPitch(Random.nextInt(1, 25));
				Camera.turnTo(n, Random.nextInt(-50, 50));
				System.out.println("Finding druid.");
			}
		}
		
	}	

}
