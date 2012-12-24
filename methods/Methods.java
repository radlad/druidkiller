package methods;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.SkillData.Rate;
import org.powerbot.game.api.wrappers.Tile;

import data.Data;

public class Methods {

	public static NumberFormat k = new DecimalFormat("###,###,###");
	public static NumberFormat z = new DecimalFormat("#");
	
	public static String kFormat(final int num) {
		return num / 1000 + "." + (num % 1000) / 100 + "K";
	}
	
	public static int getXpHr(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.experience(Rate.HOUR, skill);		
	}
	
	public static int getAdrPercent() {		
		return Settings.get(679) / 10;		
	}
	
	public static long getTTNL(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.timeToLevel(Rate.HOUR, skill);
	}
	
	public static double getExpBarLength(int skill, int maxlength) {
        int xpCurrent = Skills.getExperienceRequired(Skills.getRealLevel(skill));
        int xpNext = Skills.getExperienceRequired(Skills.getRealLevel(skill) + 1);        
        double xpBarLength = (xpCurrent / xpNext)*maxlength;
		return xpBarLength;   
	}
	
	public static int getPerHour(int integer) {
		int perHour = (int) (integer * 3600000D / (System.currentTimeMillis() - Data.startTime));
		return perHour;		
	}
	
    public static void drawTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.drawPolygon(p1);
                        }
        }
    }
    
    public static void fillTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.fillPolygon(p1);
                        }
        }
    }
    
	public static void useMomentum() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton1.validate()) {			
			System.out.println("Activating Momentum.");					
			Data.eocButton1.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	
	public static void getProfit() {
		Data.totalProfit = 0;
		for (int i : Data.lootItemIDs) {
			if (Data.lootedlist.containsKey(i) && Data.itemprices.containsKey(i)) {
				Data.totalProfit+=Data.lootedlist.get(i)*Data.itemprices.get(i);
			}
		}
	}
    
}
