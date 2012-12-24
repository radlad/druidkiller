package druidtower;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class ClimbUp extends Node {

	@Override
	public boolean activate() {
		SceneObject ladder = SceneEntities.getNearest(32015);
		SceneObject ladder2 = SceneEntities.getNearest(1749);
		return ladder != null || ladder2 != null;
	}

	@Override
	public void execute() {
		SceneObject ladder = SceneEntities.getNearest(32015);
		SceneObject ladder2 = SceneEntities.getNearest(1749);
		
		if (ladder != null) {
			ladder.interact("Climb-up");
			Task.sleep(Random.nextInt(600, 800));
		} else if (ladder2 != null) {
			ladder2.interact("Climb-down");
			Task.sleep(Random.nextInt(600, 800));
		}
	}

}
