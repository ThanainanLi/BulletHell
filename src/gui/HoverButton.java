package gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Region;
import sharedObject.RenderableHolder;

public class HoverButton extends Button{
	public HoverButton(String text, Image normal, Image hovered) {
		super(text);
		this.setPrefHeight(normal.getHeight());
		this.setPrefWidth(normal.getWidth());
		this.setFont(RenderableHolder.boldFont);
		this.setBackground(new Background(new BackgroundImage(normal, null, null, null, null)));
		//this.setGraphic(new ImageView(RenderableHolder.buttonSprite));
		this.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				((Region) arg0.getSource()).setBackground(new Background(new BackgroundImage(hovered, null, null, null, null)));
				RenderableHolder.mouseHover.play();
			}
		});
		this.setOnMouseExited(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				((Region) arg0.getSource()).setBackground(new Background(new BackgroundImage(normal, null, null, null, null)));
				
			}
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RenderableHolder.mouseClicked.play();
			}
		
		});
	}

}
