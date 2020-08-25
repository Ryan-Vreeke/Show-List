package com.kitsu;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

public class Show
{
	private Image image;
	private String title;
	private String synopsis;
	private URL url;
	private OffsetDateTime airdate;
	private double score;

	public Show()
	{

		url = null;
		image = null;
		synopsis = null;
		title = null;
		airdate = null;

	}

	public Show(String title, String synopsis, String image, String url, String airdate, double score)
			throws IOException
	{
		this.title = title;
		this.synopsis = synopsis;
		this.score = score;
		this.image = ImageIO.read(new URL(image));
		this.url = new URL(url);
		this.airdate = OffsetDateTime.parse(airdate);
		
		System.out.println(airdate + " string");
		System.out.println(this.airdate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		

	}

	public Image getImage()
	{
		return image;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSynopsis()
	{
		return synopsis;
	}

	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}

	public URL getUrl()
	{
		return url;
	}

	public void setUrl(URL url)
	{
		this.url = url;
	}

	public OffsetDateTime getAirdate()
	{
		return airdate;
	}

	public void setAirdate(OffsetDateTime airdate)
	{
		this.airdate = airdate;
	}

	public double getScore()
	{
		return score;
	}

	public void setScore(double score)
	{
		this.score = score;
	}

	@Override
	public String toString()
	{
		return "Show [image=" + image + ", title=" + title + ", synopsis=" + synopsis + ", url=" + url + ", airdate="
				+ airdate + ", score=" + score + "]";
	}
	
	

}
