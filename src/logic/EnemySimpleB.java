package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class EnemySimpleB extends Enemy{

	public EnemySimpleB(double x, double y) {
		this.bullets = new ArrayList<Bullet>();
		this.z = -100;
		this.radius = 10;
		this.health = 10;
		this.shootingDelay = 60*3/2;
		this.spriteHeight = RenderableHolder.enemyBSprite.getHeight();
		this.spriteWidth = RenderableHolder.enemyBSprite.getWidth();
		this.x = x - this.spriteHeight/2;
		this.y = y - this.spriteWidth/2;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.enemyBSprite, x, y);
	}
	
	@Override
	public ArrayList<Bullet> generateBullet() {
		this.shootingDelay--;
		this.bullets = new ArrayList<Bullet>();
		if(shootingDelay < 0) {
			for (int i = 0; i < 8 ; i++) {
				Bullet b = new Bullet(this.x+this.spriteWidth/2, this.y+this.spriteHeight/2, Math.PI*i/4);
				bullets.add(b);
			}
			this.shootingDelay = 60*3;
		}
		return bullets;
	}
	@Override
	public String getName() {
		return "EnemySimpleB";
	}
}
