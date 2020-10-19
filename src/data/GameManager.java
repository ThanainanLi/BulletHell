package data;


import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import sharedObject.RenderableHolder;

public class GameManager {
	private static final GameManager instance = new GameManager();
	private static boolean gameWon;
	private static boolean gameLost;
	private static int charId;
	private static int stageId;
	private static int score;
	private static String playerName;
	static {
		init();
	}
	public GameManager() {
	}
	private static void init() {
		gameWon = false;
		gameLost = false;
		charId = 0;
		stageId = 0;
		playerName = "";
		score = 0;
	}
	public static boolean isGameLost() {
		return gameLost;
	}
	public static boolean isGameWon() {
		return gameWon;
	}
	public static void setChar(int charid) {
		charId = charid;
		RenderableHolder.playerSprite = new Image(ClassLoader.getSystemResource("player" + charid + ".png").toString());
		RenderableHolder.heroBulletSprite = new Image(ClassLoader.getSystemResource("heroBullet" + charid + ".png").toString());
		if (charid == 1) {
			
		}
	}
	public static void setStage(int stageid) {
		stageId = stageid;
		RenderableHolder.mapSprite = new Image(ClassLoader.getSystemResource("map" + stageid + ".png").toString());
		RenderableHolder.enemyASprite = new Image(ClassLoader.getSystemResource("enemyA" + stageid + ".png").toString());
		RenderableHolder.enemyBSprite = new Image(ClassLoader.getSystemResource("enemyB" + stageid + ".png").toString());
		RenderableHolder.bossSprite = new Image(ClassLoader.getSystemResource("boss" + stageid + ".png").toString());
		RenderableHolder.battleMusic = new AudioClip(ClassLoader.getSystemResource("World" + stageid + "BG.mp3").toString());
		RenderableHolder.bossMusic = new AudioClip(ClassLoader.getSystemResource("World" + stageid + "BossSound.mp3").toString());
		RenderableHolder.deathSprite = new Image(ClassLoader.getSystemResource("deathImage" + stageid + ".png").toString());
	}
	public static void setGameLost(boolean b) {
		gameLost = b;
	}
	public static void setGameWon(boolean b) {
		gameWon = b;
	}
	public static int getChar() {
		return charId;
	}
	public static int getStage() {
		return stageId;
	}
	public static void addScore(int scoreAdd) {
		score += scoreAdd;
	}
	public static int getScore() {
		return score;
	}
	public static void resetScore() {
		score = 0;
	}
	public static String getName() {
		return playerName;
	}
	public static void resetSelected() {
		stageId = 0;
		charId = 0;
	}
}
