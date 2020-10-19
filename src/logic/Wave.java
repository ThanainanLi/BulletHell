package logic;

import java.util.ArrayList;

import sharedObject.RenderableHolder;


public class Wave {
	protected ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public Wave(int waveCount) {
		//TODO Enemy positioning and entering the scene
		enemies.clear();
		switch (waveCount) {
		case 1:
			this.enemies.add(new EnemySimpleA(300, 200));
			this.enemies.add(new EnemySimpleA(400, 200));
			this.enemies.add(new EnemySimpleA(500, 200));
			break;
		case 2:
			this.enemies.add(new EnemySimpleA(400, 200));
			this.enemies.add(new EnemySimpleB(300, 200));
			this.enemies.add(new EnemySimpleB(500, 200));
			break;
		case 3:
			this.enemies.add(new Boss(400,0));
			RenderableHolder.battleMusic.stop();
			RenderableHolder.bossMusic.setPriority(1000000000);
			RenderableHolder.bossMusic.play();
			break;
		}
	}
		
	public ArrayList<Enemy> getWaveInfo() {
		return this.enemies;
	}
	public int getWaveCountInfo() {
		return this.enemies.size();
	}
	public boolean isWaveDestroyed() {
		int deadEnemy = 0;
		for (Enemy e: enemies) {
			if (e.destroyed) {
				deadEnemy++;
			}
		}
		return deadEnemy == this.getWaveCountInfo();
	}
}
