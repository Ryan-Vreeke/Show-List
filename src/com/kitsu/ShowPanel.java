package com.kitsu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowPanel implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Show show;
	private Image cover;
	JPanel panel = new JPanel();
	JLabel label = new JLabel();

	public ShowPanel(Show show, Dimension d)
	{
		super();

		label.setIcon(new ImageIcon(show.getImage()));

		this.show = show;

		panel.setLayout(new BorderLayout());
		panel.setSize(d);

		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		panel.add(label);
		panel.setBackground(Color.BLACK);
		panel.addMouseListener(this);

	}

	public JPanel showPanel()
	{
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println(show.getTitle());

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
