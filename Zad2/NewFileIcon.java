package Zad2;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public class NewFileIcon implements Icon{
	int height = 32;
	int width = 32;
	
	public int getIconHeight() {
		return this.height;
	}

	public int getIconWidth() {
		return this.width;
	}

	public void paintIcon(Component arg0, Graphics arg1, int x, int y) {
		Graphics2D g2d = (Graphics2D)arg1.create();
		
		//kartka
		int[] xSet1 = {x+5,x+5,x+21,x+27,x+27};
		int[] ySet1 = {y+32,y+0,y+0,y+6,y+32};
		g2d.setColor(Color.WHITE);
		g2d.fillPolygon(xSet1,ySet1,xSet1.length);
		
		//róg
		int[] xSet2 = {x+21,x+27,x+21};
		int[] ySet2 = {y+0,y+6,y+6};
		g2d.setColor(Color.GRAY);
		g2d.fillPolygon(xSet2, ySet2, xSet2.length);
		
		//linie
		g2d.setColor(Color.GRAY);
		g2d.drawLine(x+7, y+4, x+20, y+4);
		for (int i=8; i<32; i+=4) g2d.drawLine(x+7, y+i, x+25, y+i);
		
		
		
		g2d.dispose();
	}
	
}