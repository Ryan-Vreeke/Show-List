package com.kitsu;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	private static HttpURLConnection connection;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Panel());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	public static void parse(String responseBody, ArrayList<Show> startingList) {
		Show show = null;
		try {
			JSONParser parse = new JSONParser();
			JSONObject jo = (JSONObject) parse.parse(responseBody);
			JSONArray json = (JSONArray) jo.get("data");

			for (int i = 0; i < json.size(); i++) {

				JSONObject data = (JSONObject) json.get(i);
				JSONObject attributes = (JSONObject) data.get("attributes");
				URL posterUrl;
				URL coverUrl;
				BufferedImage coverImage = null;
				BufferedImage posterImage = null;

				try {
					posterUrl = new URL((String) ((JSONObject) attributes.get("posterImage")).get("original"));
					coverUrl = new URL((String) ((JSONObject) attributes.get("coverImage")).get("original"));

				} catch (Exception e) {
					posterUrl = null;
					coverUrl = null;

				}
				String title = (String) attributes.get("canonicalTitle");
				String synopsis = (String) attributes.get("synopsis");
				if (coverUrl != null) {

					coverImage = ImageIO.read(coverUrl);
				}
				if (posterUrl != null) {

					posterImage = ImageIO.read(posterUrl);
				}

				show = new Show(posterImage, coverImage, title, synopsis);
				startingList.add(show);

			}

		} catch (Exception e) {

			e.printStackTrace();
			show = new Show();
		}

	}

	public static void method1(ArrayList<Show> startingList) {
		// Method 1: Java.net.HttpUrlConnection
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		try {
			// ?filter[text]=bleach
			URL url = new URL("https://kitsu.io/api/edge/anime");
			connection = (HttpURLConnection) url.openConnection();
			// Request setup
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			int status = connection.getResponseCode();

			System.out.println(status);

			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
					responseContent.append("\n");

				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}

			parse(responseContent.toString(), startingList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}
}
