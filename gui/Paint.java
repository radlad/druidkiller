package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import methods.Methods;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

import tasks.Looting;

import combat.Combat;

import data.Data;

public class Paint {
	
	 public static RenderingHints antialiasing = new RenderingHints(
	            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	 public static Color color1 = new Color(0, 0, 0, 180);
	 public static Color color2 = new Color(0, 0, 0);
	 public static Color color3 = new Color(237, 237, 237, 226);
	 public static Color color4 = new Color(169, 169, 169, 226);
	 public static Color color5 = new Color(237, 237, 237, 160);
	 public static Color color6 = new Color(255, 255, 255, 200);
	 public static Color color7 = new Color(255, 0, 0, 200);
	 public static Color color8 = new Color(0, 0, 0, 160);
	
	 public static Color color_skill = new Color(0, 0, 0, 255);
	 public static Color color_const = new Color(255, 43, 43, 170);
	 public final static Color color_mouse = new Color(250, 250, 250, 200);
	 public final static Color color_mouse_opa = new Color(222, 222, 222, 150);
	 public static Color color_item = new Color(107, 216, 255, 225);
	 public static Color color_item2 = new Color(120, 180, 202, 100);
	 public static Color color_druid = new Color(124, 252, 0, 225);
	 public static Color color_druid2 = new Color(124, 252, 0, 100);
	 public static Color color_player = new Color(255, 110, 0, 225);
	 public static Color color_player2 = new Color(235, 159, 102, 220);
	 public static Color color_player3 = new Color(255, 34, 41, 225);
	 public static Color color_player4 = new Color(240, 107, 111, 220);
	
	
	 public final static BasicStroke stroke2 = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	 public static BasicStroke stroke1 = new BasicStroke(1);
	
	 public static Font font1 = new Font("Myriad Pro", 1, 12);
	 public static Font font2 = new Font("Myriad Pro", 1, 10);
	 public static Font font3 = new Font("Myriad Pro", 0, 10);
	 
	 public static Font font4 = new Font("Myriad Pro", 1, 32);
	 public static Font font5 = new Font("Myriad Pro", 0, 16);
	
	public static void doPaint(Graphics g1) {
		
       Graphics2D g = (Graphics2D)g1;
	    g.setRenderingHints(antialiasing);
	    
      //  GroundItem item = GroundItems.getNearest(Looting.ItemFilter);
       // NPC n = NPCs.getNearest(Combat.MobFilter);
        if (Data.toggleDrawing) {
	        for (NPC n : NPCs.getLoaded(Combat.MobFilter)) {
		        if (n != null) {
		        	Methods.drawTile(g1, n.getLocation(), color_druid, 225);
		        	Methods.fillTile(g1, n.getLocation(), color_druid2, 100);
	        		//g1.setFont(font2); 	        		
		        	//g1.setColor(Color.BLACK);
		        	//g1.drawString(n.getName(), (int) n.getCentralPoint().getX()+26, (int) (n.getCentralPoint().getY()+1));
		        	//g1.setColor(color_druid);
		        	//g1.drawString(n.getName(), (int) n.getCentralPoint().getX()+25, (int) (n.getCentralPoint().getY()));
		        }	 
	        }
        	for (GroundItem item : GroundItems.getLoaded(Looting.ItemFilter)) {
		        if (item != null) {	        	
		        	Methods.drawTile(g1, item.getLocation(), color_item, 225);
		        	Methods.fillTile(g1, item.getLocation(), color_item2, 100);
		        	g1.setFont(font2);	  
		        	g1.setColor(Color.BLACK);
		        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(),
		        			(int) item.getCentralPoint().getX()+26, (int) (item.getCentralPoint().getY()+1));
		        	g1.setColor(color_item);
		        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(),
		        			(int) item.getCentralPoint().getX()+25, (int) (item.getCentralPoint().getY()));
		        }	
        	}

        }
		
	  	final Point p = Mouse.getLocation(); 
	    g.setColor(color_mouse_opa);
	    g.drawLine(0, p.y, Game.getDimensions().width, p.y);
	    g.drawLine(p.x, 0, p.x, Game.getDimensions().height);
	    g.setColor(Mouse.isPressed() ? Color.RED : color_mouse);    	    
	    if (!Mouse.isPressed()) {    	    
	    g.setStroke(stroke2);
	    g.drawLine(Mouse.getX(), Mouse.getY() + 8, Mouse.getX(), Mouse.getY() - 8);
	    g.drawLine(Mouse.getX() + 8, Mouse.getY(), Mouse.getX() - 8, Mouse.getY());
	    } else {
	    	g.fillRect(Mouse.getX() - 6, Mouse.getY() + 6, 3, 3);
	    	g.fillRect(Mouse.getX() + 6, Mouse.getY() + 6, 3, 3);
	    	g.fillRect(Mouse.getX() - 6, Mouse.getY() - 6, 3, 3);
	    	g.fillRect(Mouse.getX() + 6, Mouse.getY() - 6, 3, 3);
	    }

        g.setColor(color1);
        g.fillRect(1, 355, 517, 32);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(1, 355, 517, 32);
        g.setColor(color1);
        g.fillRect(6, 340, 61, 15);
        g.setColor(color2);
        g.drawRect(6, 340, 61, 15);
        g.setColor(color1);
        g.fillRect(73, 340, 69, 15);
        g.setColor(color2);
        g.drawRect(73, 340, 69, 15);
        g.setColor(color1);
        g.fillRect(443, 340, 69, 15);
        g.setColor(color2);
        g.drawRect(443, 340, 69, 15);
        g.setColor(color1);
        g.setColor(color_skill);
        g.fillRect(318, 356, (int) Methods.getExpBarLength(Data.chosenSkill, 200), 15);
        g.setColor(color2);
        g.drawRect(317, 355, 201, 16);
        g.setColor(color1);
        g.setColor(color_const);
        g.fillRect(318, 372, (int) Methods.getExpBarLength(3, 200), 15);
        g.setColor(color2);
        g.drawRect(317, 371, 201, 16);
        g.setFont(font1);
        g.drawString("MondoDruids", 9, 370);
        g.setColor(color3);
        g.drawString("MondoDruids", 8, 369);
        g.setFont(font2);
        g.setColor(color2);
        g.drawString("v1.0", 90, 370);
        g.setColor(color7);
        g.drawString("v1.0", 89, 369);
        g.setFont(font2);
        g.setColor(color4);
        g.drawString("Pause", 22, 351);
        g.drawString("Toggle Draw", 79, 351);
        g.drawString("Skill to Track", 449, 351);
        if (Data.skillSelectOpen) {
	        g.setColor(color1);
	        g.fillRect(425, 320, 87, 14);
	        g.setColor(color2);
	        g.drawRect(425, 320, 87, 14);
	        g.setColor(color1);
	        g.fillRect(425, 300, 87, 14);
	        g.setColor(color2);
	        g.drawRect(425, 300, 87, 14);
	        g.setColor(color1);
	        g.fillRect(425, 280, 87, 14);
	        g.setColor(color2);
	        g.drawRect(425, 280, 87, 14);
	        g.setColor(color1);
	        g.fillRect(425, 260, 87, 14);
	        g.setColor(color2);
	        g.drawRect(425, 260, 87, 14);
	        g.setColor(color1);
	        g.fillRect(425, 240, 87, 14);
	        g.setColor(color2);
	        g.drawRect(425, 240, 87, 14);
	        g.setFont(font2);
	        g.setColor(color4);
	        g.drawString("Attack", 455, 331);
	        g.drawString("Strength", 450, 311);
	        g.drawString("Defence", 451, 291);
	        g.drawString("Magic", 454, 271);
	        g.drawString("Range", 455, 251);
        }
        g.setFont(font3);
        g.setColor(color5);
        g.drawString("Run Time: " + Data.runTime.toElapsedString(), 8, 382);
        g.drawString("Killed: " + Data.killCounter + " ( " + Methods.getPerHour(Data.killCounter) + " p/h )", 100, 382);
        g.drawString("Profit: " + Methods.kFormat(Data.totalProfit) + " ( " + Methods.kFormat(Methods.getPerHour(Data.totalProfit)) + " p/h )", 205, 382);
        g.drawString("Status: " + Data.status, 135, 369);
        g.setColor(color6);
        if (Data.skillString == "Not Chosen!") {
	        g.drawString("Choose a skill by clicking \"Skill to Track\"", 322, 367);
        } else {
	        g.drawString("[ " + Data.skillString + ": " + Skills.getRealLevel(Data.chosenSkill) + " (+" + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) + ") | " + Methods.kFormat(Methods.getXpHr(Data.chosenSkill)) + " xp/h | TTL: " + Methods.getTTNL(Data.chosenSkill) + " ]", 322, 367);
        }
        g.drawString("[ Constitution: " + Skills.getRealLevel(3) + " (+" + (Skills.getRealLevel(3) - Data.startConstLevel) + ") | " + Methods.kFormat(Methods.getXpHr(3)) + " | TTL: " + Methods.getTTNL(3) + " ]", 322, 383);
        
        if (Data.chatWarning && Data.chatTimer.isRunning()) {
	        g.setColor(color8);
	        g.fillRect(236, 414, 255, 67);
	        g.setColor(color7);
	        g.setStroke(stroke1);
	        g.drawRect(236, 414, 255, 67);
	        g.setFont(font4);
	        g.drawString("WARNING", 299, 449);
	        g.setFont(font5);
	        g.drawString("Last Message: " + Data.chatTimer.toElapsedString() + " ago", 278, 467);
	        g.drawLine(474, 420, 484, 430);
	        g.drawLine(474, 430, 484, 420);
        }
		
	}

}
