package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Data {
	
	public static Tile[] BankToLog = new Tile[] { new Tile(2616, 3333, 0), new Tile(2614, 3338, 0), new Tile(2609, 3339, 0), 
			new Tile(2607, 3334, 0), new Tile(2602, 3334, 0) };
	
	public static Tile[] LogToTower = new Tile[] { new Tile(2597, 3336, 0), new Tile(2594, 3340, 0), new Tile(2589, 3340, 0), 
			new Tile(2585, 3343, 0), new Tile(2581, 3346, 0), new Tile(2576, 3348, 0), 
			new Tile(2573, 3352, 0), new Tile(2569, 3355, 0), new Tile(2566, 3355, 0) };
	
	public static Area BankArea = new Area(new Tile[] { new Tile(2610, 3337, 0), new Tile(2622, 3337, 0), new Tile(2622, 3329, 0), 
			new Tile(2610, 3329, 0) });
	
	public static Area BankLogArea = new Area(new Tile[] { new Tile(2599, 3341, 0), new Tile(2622, 3341, 0), new Tile(2622, 3335, 0), 
			new Tile(2610, 3335, 0), new Tile(2610, 3329, 0), new Tile(2608, 3329, 0), 
			new Tile(2608, 3321, 0), new Tile(2599, 3321, 0), new Tile(2599, 3329, 0), 
			new Tile(2600, 3329, 0), new Tile(2600, 3333, 0) });
	
	public static Area TowerLogArea = new Area(new Tile[] { new Tile(2565, 3358, 0), new Tile(2576, 3358, 0), new Tile(2583, 3348, 0), 
			new Tile(2596, 3341, 0), new Tile(2600, 3339, 0), new Tile(2600, 3329, 0), 
			new Tile(2599, 3322, 0), new Tile(2599, 3321, 0), new Tile(2591, 3321, 0), 
			new Tile(2591, 3329, 0), new Tile(2587, 3332, 0), new Tile(2579, 3340, 0), 
			new Tile(2568, 3345, 0), new Tile(2565, 3345, 0) });
	
	public static Area TowerArea = new Area(new Tile[] { new Tile(2558, 3361, 0), new Tile(2565, 3361, 0), new Tile(2565, 3351, 0), 
			new Tile(2558, 3351, 0) });
	
	public static Area OutsideTower = new Area(new Tile[] { new Tile(2564, 3358, 0), new Tile(2569, 3358, 0), new Tile(2569, 3352, 0), 
			new Tile(2564, 3352, 0) });
	
	public static ArrayList<Integer> lootItems = new ArrayList<Integer>();
	public static int[] lootItemIDs = {1617,1631,1615,987,985,1247,1249,1216,1201,2366,1149,892,9342,2362,452,258,2999,3001,
		270,6686,5315,5316,5289,5300,5304,1516,1392,574,570,20667,7937,561,560,565,1441,1443,372,533,446,454,450,199,201,203,205,207,
		209,211,213,215,217,219,2485,3051,9142,563,556,12158,12159,12160,12163,1215,1631,9143,533,2999,258,3001,270,454,450,7937,1441,
		1443,1444,372,384,5321,9342,892,1392,574,570,452,2362,2364,5315,5316,5289,5304,5300,1516,1216,6686,20667};
	
	public static HashMap<Integer, Integer> itemprices = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> lootedlist = new HashMap<Integer, Integer>();
	
	public static int bankBooth = 34752;
	public static Tile insideTower = new Tile(2564, 3358, 0);
	public static int chaosDruid = 181;
	public static boolean lockPicked = false;
	
	public static String status = "Waiting...";
	
	public static boolean showSkillMenu;
	public static String skillString = "Not Chosen!";
	public static int startLevel;	
	public static int chosenSkill;
	public static int startAtkLevel;
	public static int startStrLevel;
	public static int startDefLevel;
	public static int startRngLevel;
	public static int startMagLevel;
	public static int startConstLevel;
	
	public static Timer runTime = new Timer(0);	
	public static Timer chatTimer = new Timer(0);	
	public static long startTime;
	public static boolean paused;
	public static boolean toggleDrawing = true;;
	public static boolean skillSelectOpen;
	public static boolean chatWarning = false;
	
	public static int totalProfit = 0;
	public static int itemPrice = 0;
	public static int stackSize;
	public static int lootTries;
	public static int itemID;
	public static int oldItemCount = 0;
	public static int oldInvCount = 0;
	
	public static Timer lootCheck;
	
	public static int killCounter;
	public static int oldKills;
	
	public static WidgetChild eocMaximise = Widgets.get(640, 3);
	public static WidgetChild eocMinimise = Widgets.get(640, 30);
	public static WidgetChild eocButton1 = Widgets.get(640, 36);

}
