package logic;

import java.util.ArrayList;

import data.GameManager;
import sharedObject.RenderableHolder;



public abstract class Enemy extends CollidableEntity{
	protected int health = 0;
	protected ArrayList<Bullet> bullets;
	protected abstract ArrayList<Bullet> generateBullet();
	protected int shootingDelay;
	public String getName() {
		return null;
	}
	public void drawDeath() {
		DeathImage d = new DeathImage(this.x-this.spriteWidth/2,this.y-this.spriteHeight/2);
		RenderableHolder.getInstance().add(d);
		GameManager.addScore(100);
	}
}
