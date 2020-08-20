package com.kitsu;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Show> startingList;

	public Panel() {
		super(new GridLayout(2, 5));
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		startingList = new ArrayList<Show>();

		Main.method1(startingList);

		for (Show s : startingList) {
			System.out.println(s);
			this.add(new ShowPanel(s));
		}

	}

	public static BufferedImage resize(Image image, int height, int width) {
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resized.createGraphics();
		g.drawImage(tmp, 0, 0, null);
		g.dispose();
		return resized;
	}
}
