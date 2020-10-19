package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class EnemySimpleA extends Enemy{
	
	public EnemySimpleA(double x, double y) {
		this.bullets = new ArrayList<Bullet>();
		this.z = -100;
		this.radius = 10;
		this.health = 10;
		this.shootingDelay = 60*3;
		this.spriteHeight = RenderableHolder.enemyASprite.getHeight();
		this.spriteWidth = RenderableHolder.enemyASprite.getWidth();
		this.x = x - this.spriteHeight/2;
		this.y = y - this.spriteWidth/2;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.enemyASprite, x, y);
	}
	
	@Override
	public ArrayList<Bullet> generateBullet() {
		this.shootingDelay--;
		this.bullets = new ArrayList<Bullet>();
		if(shootingDelay < 0) {
			//reset bullet
			//create bullet and give to gamelogic
			//TODO Correct Bullet placement
			Bullet b = new Bullet(this.x+this.spriteWidth/2 - 2, this.y+this.spriteHeight, Math.PI/2);
			bullets.add(b);
			this.shootingDelay = 60*3;
		}
		return bullets;
	}
	@Override
	public String getName() {
		return "EnemySimpleA";
	}

}
