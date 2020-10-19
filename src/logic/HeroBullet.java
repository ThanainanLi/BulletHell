package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class HeroBullet extends CollidableEntity{
	private int angle = 0;
	private double speed = 5;
	public HeroBullet(double x, double y) {
		RenderableHolder.shootingSound.play();
		this.x = x;
		this.y = y;
		this.z = -100;
		this.radius = 1;
		this.spriteHeight = RenderableHolder.heroBulletSprite.getHeight();
		this.spriteWidth = RenderableHolder.heroBulletSprite.getWidth();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		this.x += Math.cos(Math.PI/2)*speed;
		this.y -= Math.sin(Math.PI/2)*speed;
		gc.drawImage(RenderableHolder.heroBulletSprite,x,y);
	}
	
}
