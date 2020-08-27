package com.javafx.list;
/*
Put header here


 */

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLController implements Initializable
{

	@FXML
	private ListView<Show> listview;

	@FXML
	private ImageView imageview;

	@FXML
	private Label title;

	@FXML
	private TextArea txtSynopsis;

	@FXML
	private Label lblScore;

	@FXML
	private Label airDate;

	@FXML
	private void btnClickAction(ActionEvent event)
	{
		String message = "";
		ObservableList<Show> temp;

		temp = listview.getSelectionModel().getSelectedItems();
		title.setText(temp.get(0).getTitle());
		txtSynopsis.setText(temp.get(0).getSynopsis());
		imageview.setImage(new Image(temp.get(0).getImage()));
		lblScore.setText("Score: " + temp.get(0).getScore());
		airDate.setText("Air Date: " + temp.get(0).getAirdate());

	}

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		txtSynopsis.setWrapText(true);

		listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ArrayList<Show> temp = new ArrayList<Show>();
		Main.method1(temp);

		for (Show s : temp)
		{
			listview.getItems().add(s);
		}
	}

	public void imageClicked()
	{
		// System.out.println(listview.getSelectionModel().getSelectedItem().getUrl());

		try
		{
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
			{

				Desktop.getDesktop().browse(listview.getSelectionModel().getSelectedItem().getUrl().toURI());
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void menuClose()
	{
		MainApp.end();
	}

	public void menuAddToWatch()
	{

	}

	public void menuAboutPage()
	{

	}

}
