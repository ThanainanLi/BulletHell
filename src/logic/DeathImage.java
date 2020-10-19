package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class DeathImage extends Entity{
	private int deathTick;
	public DeathImage(double x, double y) {
		this.z = -50;
		this.x = x;
		this.y = y;
		this.deathTick = 60*1;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.deathSprite, x, y);
		this.deathTick--;
		if (this.deathTick < 0) {
			this.destroyed = true;
		}
	}
	
}
