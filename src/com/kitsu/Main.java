package com.kitsu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main
{
	private static HttpURLConnection connection;
	private static ArrayList<Show> shows;

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		shows = new ArrayList<Show>();
		method1(shows);
		JList<ShowPanel> showComp = new JList<ShowPanel>();
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(shows.size() / 4, 4);

		panel.setLayout(grid);

		for (Show s : shows)
		{
			panel.add(new ShowPanel(s, new Dimension(200, 200)).showPanel());
		}

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane sc = new JScrollPane(panel);

		sc.getVerticalScrollBar().setUnitIncrement(30);
		sc.setBackground(Color.BLACK);
		f.setSize(new Dimension(1500, 800));
		f.add(sc);

		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	public static String parse(String responseBody, ArrayList<Show> startingList)
	{
		Show show = null;
		String synopsis = null;
		String url = null;
		String title = null;
		String image_url = null;
		String airing_start = null;
		double score = 0;
		try
		{
			JSONParser parse = new JSONParser();
			JSONObject jo = (JSONObject) parse.parse(responseBody);
			JSONArray json = (JSONArray) jo.get("anime");

			for (int i = 0; i < 50; i++)
			{
				try
				{

					JSONObject anime = (JSONObject) json.get(i);
					synopsis = (String) anime.get("synopsis");
					url = (String) anime.get("url");
					title = (String) anime.get("title");
					image_url = (String) anime.get("image_url");
					airing_start = (String) anime.get("airing_start");
					score = (double) anime.get("score");
				} catch (NullPointerException e)
				{

				} catch (ClassCastException e)
				{
					score = 0;
				}

				show = new Show(title, synopsis, image_url, url, airing_start, score);
				startingList.add(show);
			}

		} catch (Exception e)
		{

			e.printStackTrace();
			show = new Show();
			startingList.add(show);
		}
		System.out.println("complete");

		return null;

	}

	public static void method1(ArrayList<Show> startingList)
	{
		// Method 1: Java.net.HttpUrlConnection
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try
		{
			// ?filter[text]=bleach
			URL url = new URL("https://api.jikan.moe/v3/season/2020/winter");

			connection = (HttpURLConnection) url.openConnection();

			// Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int status = connection.getResponseCode();

			System.out.println(status);

			if (status > 299)
			{
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
					responseContent.append("\n");

				}
				reader.close();
			} else
			{
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null)
				{
					responseContent.append(line);
				}
				reader.close();
			}
			System.out.println(responseContent.toString());
			parse(responseContent.toString(), startingList);
			// System.out.println(responseContent);
		} catch (Exception e)
		{
			e.printStackTrace();

		} finally
		{
			connection.disconnect();
		}
	}

}
