package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Region;

public class ToggleHoverButton extends HoverButton{
	private Image normalState;
	private boolean triggered = false;
	public ToggleHoverButton(String text, Image normal, Image hovered, Image toggled) {
		super(text, normal, hovered);
		this.normalState = normal;
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				triggered = true;
				((Region) arg0.getSource()).setBackground(new Background(new BackgroundImage(toggled, null, null, null, null)));
			}
		
		});
		this.setOnMouseExited(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (!triggered) {
					((Region) arg0.getSource()).setBackground(new Background(new BackgroundImage(normal, null, null, null, null)));
				} else {
					((Region) arg0.getSource()).setBackground(new Background(new BackgroundImage(toggled, null, null, null, null)));
				}
				
			}
		});
	}
	
	public void resetToggled() {
		triggered = false;
		this.setBackground(new Background(new BackgroundImage(this.normalState, null, null, null, null)));
	}
}
