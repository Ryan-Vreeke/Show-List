package com.kitsu;

import java.awt.image.BufferedImage;

public class Show {
	private BufferedImage coverImg;
	private BufferedImage bannerImg;
	private String title;
	private String synopsis;

	public Show() {

		bannerImg = null;
		coverImg = null;
		title = null;
		synopsis = null;

	}

	public Show(BufferedImage coverImg, BufferedImage bannerImg, String title, String synopsis) {
		this.coverImg = coverImg;
		this.bannerImg = bannerImg;
		this.title = title;
		this.synopsis = synopsis;
	}

	public BufferedImage getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(BufferedImage coverImg) {
		this.coverImg = coverImg;
	}

	public BufferedImage getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(BufferedImage bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getsynopsis() {
		return synopsis;
	}

	public void setsynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String toString() {
		return "Show [title=" + title + ", synopsis=" + synopsis + "]";
	}

}
