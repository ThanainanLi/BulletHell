package logic;

import java.util.ArrayList;

import data.GameManager;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Boss extends Enemy{
	private int phase = 1;
	
	public Boss(double x, double y) {
		this.bullets = new ArrayList<Bullet>();
		this.x = x;
		this.y = y;
		this.z = -100;
		this.radius = 200;
		this.spriteHeight = RenderableHolder.bossSprite.getHeight();
		this.spriteWidth = RenderableHolder.bossSprite.getWidth();
		this.health = 200*3;
		this.shootingDelay = 60/2;
		this.x = x - this.spriteHeight/2;
		this.y = y - this.spriteWidth/2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.bossSprite, x, y);
	}

	@Override
	protected ArrayList<Bullet> generateBullet() {
		//TODO Create Bullet pattern
		if (this.health >= 400) {
			this.bullets = new ArrayList<Bullet>();
			this.shootingDelay--;
			if (this.shootingDelay <= 0) {
				for (int i = 1; i < 4; i++) {
					Bullet b = new Bullet(this.x + this.spriteWidth/2, this.y + this.spriteHeight, Math.PI*i/4);
					bullets.add(b);
				}
				this.shootingDelay = 60/2;
			}
		} else if (this.health >= 200) {
			this.bullets = new ArrayList<Bullet>();
			this.shootingDelay--;
			if (this.shootingDelay <= 0) {
				for (int i = 1; i < 6; i++) {
					Bullet b = new Bullet(this.x + this.spriteWidth/2, this.y + this.spriteHeight, Math.PI*i/6);
					bullets.add(b);
				}
				this.shootingDelay = 60;
			}
		} else if (this.health >= 0) {
			this.bullets = new ArrayList<Bullet>();
			this.shootingDelay--;
			if (this.shootingDelay <= 0) {
				for (int i = 1; i < 4; i++) {
					Bullet b = new Bullet(this.x, this.y + this.spriteHeight, Math.PI*i/4);
					bullets.add(b);
					Bullet b2 = new Bullet(this.x + this.spriteWidth, this.y + this.spriteHeight, Math.PI*i/4);
					bullets.add(b2);
				}
				this.shootingDelay = 60/2;
			}
		}
		return this.bullets;
	}
	@Override
	public void drawDeath() {
		DeathImage d = new DeathImage(this.x-this.spriteWidth/2,this.y-this.spriteHeight/2);
		RenderableHolder.getInstance().add(d);
		GameManager.addScore(1000);
		RenderableHolder.gameOverBgm.play();
	}
}
