package logic;

import java.util.ArrayList;
import java.util.List;

import data.GameManager;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<CollidableEntity> gameObjectContainer;
	private List<Enemy> enemiesContainer;
	private Wave currentWave;
	private int waveCount = 0;
	private Player player = new Player(400,300, GameManager.getChar());
	private int shootingDelay = 10;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<CollidableEntity>();
		this.enemiesContainer = new ArrayList<Enemy>();
		//RenderableHolder.getInstance().add(field);
		resetList();
	}
	
	protected void addNewObject(CollidableEntity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
		if (entity instanceof Enemy) {
			enemiesContainer.add((Enemy) entity);
		}
	}
	
	public void logicUpdate(){
		player.update();
		//System.out.println(gameObjectContainer.size());
		if (player.isShooting()) {
			//TODO Correct Bullet
			shootingDelay--;
			if(shootingDelay <= 0) {
				addNewObject(new HeroBullet(player.getX() + RenderableHolder.playerSprite.getWidth()/2, player.getY()));
				shootingDelay = 10;
			}
		}
		for (Enemy e : enemiesContainer) {
			if (!e.destroyed) {
				ArrayList<Bullet> bullets = e.generateBullet();
				if (bullets != null) {
					for (Bullet b : bullets) {
						addNewObject(b);
					}
				}
			}
		}
		if (currentWave.isWaveDestroyed()) {
			spawnNewWave();
		}
		for (CollidableEntity ce : gameObjectContainer) {
			if(ce instanceof Bullet && player.getPlayerHitBox().intersects(ce.getHitBox()) && !ce.destroyed) {
				player.receiveDamage();
				ce.destroyed = true;
			}
			if(ce instanceof Enemy && player.collideWith(ce) && !ce.destroyed) {
				player.receiveDamage();
			}
			//if(ce instanceof Enemy) {
				//ArrayList<Bullet> bullets = ((Enemy) ce).generateBullet();
				//for(Bullet b : bullets) {
				//	addNewObject(b);
				//}
			//}
			//Bad hit detection
			if(ce instanceof HeroBullet) {
				for (Enemy e : enemiesContainer) {
					if (ce.collideWith(e) && !e.destroyed && !ce.destroyed) {
						//System.out.println("Hit");
						ce.destroyed = true;
						e.health -= player.getDamage();
						if (e.health <= 0) {
							e.destroyed = true;
							e.drawDeath();
						}
					}
				}
			}
			//Clean off bullet offscreen
			if(ce.x < -10 || ce.x > 810 || ce.y < -10 || ce.y > 610) {
				if(!(ce instanceof Player) && !(ce instanceof Enemy)) {
					ce.destroyed = true;
				}
		}}
		//Check Game State
		
	}
	public void spawnNewWave() {
		if (waveCount == 3) {
			GameManager.setGameWon(true);
		}
		waveCount++;
		currentWave = new Wave(waveCount);
		for(CollidableEntity ce : currentWave.getWaveInfo()) {
			addNewObject(ce);
		}
	}
	public void resetList() {
		gameObjectContainer.clear();
		enemiesContainer.clear();
		waveCount = 0;
		spawnNewWave();
		player = new Player(400,300, GameManager.getChar());
		addNewObject(player);
	}
}
