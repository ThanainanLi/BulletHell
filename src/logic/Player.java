package logic;

import data.GameManager;
import input.InputUtility;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class Player extends CollidableEntity{
	private double invframe = 0;
	public static int health = 3;
	private double speed;
	private int damage;
	private boolean shooting = false;
	private Rectangle2D playerHitbox;
	public Player(int x, int y, int charid) {
		this.x = x;
		this.y = y;
		this.z = -100;
		this.radius = 1;
		this.spriteHeight = RenderableHolder.playerSprite.getHeight();
		this.spriteWidth = RenderableHolder.playerSprite.getWidth();
		this.playerHitbox = new Rectangle2D(this.x + (spriteHeight-10)/2, this.y + (spriteWidth-10)/2, 10,10);
		if (charid == 1) {
			this.speed = 3;
			this.damage = 2;
		} else if(charid == 2) {
			this.speed = 7;
			this.damage = 1;
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if (invframe % 5 == 0) {
			gc.drawImage(RenderableHolder.playerSprite,x,y);
		}
	}

	public void update() {
		this.playerHitbox = new Rectangle2D(this.x +(spriteHeight-10)/2,this.y + (spriteWidth-10)/2, 10,10);
		if (invframe > 0) {
			invframe--;
		}
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			if (this.y >= 0) {
				this.y -= speed;
			}
		} else if (InputUtility.getKeyPressed(KeyCode.S)) {
			if (this.y + this.spriteHeight < 600) {
				this.y += speed;
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			if (this.x >= 0) {
				this.x -= speed;
			}
		} else if (InputUtility.getKeyPressed(KeyCode.D)) {
			if (this.x + this.spriteWidth < 800) {
				this.x += speed;
			}
		}
		if (InputUtility.isLeftDown()) {
			this.shooting = true;
		} else {
			this.shooting = false;
		}
	}

	public void receiveDamage() {
		if (invframe == 0) {
			RenderableHolder.damagedSound.play();
			System.out.println(Player.health);
			invframe = 10*60;
			Player.health -= 1;
			if (Player.health < 0) {
				this.destroyed = true;
				GameManager.setGameLost(true);
				RenderableHolder.gameOverBgm.play();
			}
		}
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public boolean isShooting() {
		return this.shooting;
	}
	public Rectangle2D getPlayerHitBox() {
		return playerHitbox;
	}

	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
}
