package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Bullet extends CollidableEntity{
	
	private double angle = 0;
	private final int speed = 2;
	
	public Bullet(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.z = -100;
		this.radius = 1;
		this.angle = angle;
		this.spriteHeight = RenderableHolder.bulletSprite.getHeight();
		this.spriteWidth = RenderableHolder.bulletSprite.getWidth();
	}
	
	public void draw(GraphicsContext gc) {
		this.x += Math.cos(angle)*speed;
		this.y += Math.sin(angle)*speed;
		gc.drawImage(RenderableHolder.bulletSprite,x,y);
	}
	
}
