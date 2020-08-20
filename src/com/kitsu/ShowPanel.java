package com.kitsu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowPanel extends JPanel {

	private Show show;
	private BufferedImage cover;

	public ShowPanel(Show show) {
		super(new BorderLayout());
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		this.cover = show.getCoverImg();
		this.show = show;

		JButton btn = new JButton();
		if (cover != null) {
			Image img = Panel.resize(cover, 200, 200);

			btn.setIcon(new ImageIcon(img));
		}

		this.add(btn, BorderLayout.CENTER);
		this.add(new JLabel(show.getTitle()), BorderLayout.PAGE_END);

	}
}
