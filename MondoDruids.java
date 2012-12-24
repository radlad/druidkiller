import gui.Paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import methods.Methods;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;

import tasks.Antiban;
import tasks.Looting;

import combat.Combat;

import data.Data;
import druidtower.ClimbUp;
import druidtower.DoBank;
import druidtower.ToBank;
import druidtower.ToTower;



@Manifest(authors = { "nootz" }, name = "MondoDruids", description = "Easy money at the Chaos Druid Tower.", version = 1.0 )
public class MondoDruids extends ActiveScript implements MessageListener, PaintListener, MouseListener {
	
	@Override
	public void onRepaint(Graphics g1) {		
			Paint.doPaint(g1);		
	}

    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;

    public synchronized final void provide(final Node... jobs) {
            for (final Node job : jobs) {
                    if(!jobsCollection.contains(job)) {
                            jobsCollection.add(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public synchronized final void revoke(final Node... jobs) {
            for (final Node job : jobs) {
                    if(jobsCollection.contains(job)) {
                            jobsCollection.remove(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public final void submit(final Job... jobs) {
            for (final Job job : jobs) {
                    getContainer().submit(job);
            }
    }

	@Override
	public int loop() {
		if (Data.paused) {
			Task.sleep(50);
		} else {
			if (jobContainer != null) {
	            final Node job = jobContainer.state();
	            if (job != null) {
	                    jobContainer.set(job);
	                    getContainer().submit(job);
	                    job.join();
	            }
			}	
		}
		
		if (Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().getAnimation() == 836) {
			if (Data.killCounter - Data.oldKills < 1) {
			Data.killCounter+=1;
			}
		}
		
		return Random.nextInt(50, 100);
	}
	
	public void onStart() {
		provide(new ClimbUp(), new ToTower(), new ToBank(), new DoBank(), new Looting(), new Combat(), new Antiban());
		Mouse.setSpeed(Mouse.Speed.FAST);
		for (int i : Data.lootItemIDs) {
			Data.lootItems.add(i);
		}
		Data.paused = false;
		Data.startAtkLevel = Skills.getRealLevel(Skills.ATTACK);
		Data.startStrLevel = Skills.getRealLevel(Skills.STRENGTH);
		Data.startDefLevel = Skills.getRealLevel(Skills.DEFENSE);
		Data.startRngLevel = Skills.getRealLevel(Skills.RANGE);
		Data.startMagLevel = Skills.getRealLevel(Skills.MAGIC);
		Data.startConstLevel = Skills.getRealLevel(Skills.CONSTITUTION);
		Data.startTime = System.currentTimeMillis();
		Data.oldKills = Data.killCounter;	
		Data.lootTries = 0;
	}
	
	public void onStop() {
		
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getId() != 2 && m.getMessage().contains("You manage to pick the lock")) {
			System.out.println("Picked the lock");
			Data.lockPicked = true;
		}
		
		if (m.getId() == 2) {
			Data.chatWarning = true;
			Data.chatTimer = new Timer(20000);	
		}
	}

	@Override
	public void mouseClicked(MouseEvent p) {
		final Rectangle pauseBot = new Rectangle(6, 340, 61, 15);
		final Rectangle toggleDraw = new Rectangle(73, 340, 69, 15);		
		final Rectangle skillSelect = new Rectangle(443, 340, 69, 15);
		final Rectangle skillAtk = new Rectangle(425, 320, 87, 14);
		final Rectangle skillStr = new Rectangle(425, 300, 87, 14);
		final Rectangle skillDef = new Rectangle(425, 280, 87, 14);
		final Rectangle skillMag = new Rectangle(425, 260, 87, 14);
		final Rectangle skillRng = new Rectangle(425, 240, 87, 14);
		final Rectangle closeWarning = new Rectangle(474, 420, 484, 430);
		
		if(pauseBot.contains(p.getPoint())) {
			Data.paused = !Data.paused;
			Data.status = Data.paused ? "Paused." : "Waiting...";
        }		
		if(toggleDraw.contains(p.getPoint())) {
			Data.toggleDrawing = !Data.toggleDrawing;
			System.out.println("HashMap itemprices: " + Data.itemprices);
			System.out.println("HashMap lootedlist: " + Data.lootedlist);
			System.out.println("Profit: " + Data.totalProfit);
        }
		if (skillSelect.contains(p.getPoint())) {
			Data.skillSelectOpen = !Data.skillSelectOpen;
		}
		if (skillAtk.contains(p.getPoint()) && Data.skillSelectOpen) {
			Data.skillString = "Attack";
			Data.chosenSkill = 0;
			Paint.color_skill = new Color(192, 46, 46, 170);
			Data.startLevel = Data.startAtkLevel;
		}
		if (skillStr.contains(p.getPoint()) && Data.skillSelectOpen) {
			Data.skillString = "Strength";
			Data.chosenSkill = 2;
			Paint.color_skill = new Color(46, 134, 55, 170);
			Data.startLevel = Data.startStrLevel;
		}
		if (skillDef.contains(p.getPoint()) && Data.skillSelectOpen) {
			Data.skillString = "Defence";
			Data.chosenSkill = 1;
			Paint.color_skill = new Color(89, 144, 255, 170);
			Data.startLevel = Data.startDefLevel;
		}
		if (skillMag.contains(p.getPoint()) && Data.skillSelectOpen) {
			Data.skillString = "Magic";
			Data.chosenSkill = 6;
			Paint.color_skill = new Color(35, 158, 210, 170);
			Data.startLevel = Data.startMagLevel;
		}
		if (skillRng.contains(p.getPoint()) && Data.skillSelectOpen) {
			Data.skillString = "Range";
			Data.chosenSkill = 4;
			Paint.color_skill = new Color(168, 108, 39, 170);
			Data.startLevel = Data.startRngLevel;
		}
		if (closeWarning.contains(p.getPoint()) && Data.chatWarning) {
			Data.chatWarning = !Data.chatWarning;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
