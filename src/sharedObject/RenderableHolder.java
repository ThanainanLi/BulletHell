package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.text.Font;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image playerSprite;
	public static Image bulletSprite;
	public static Image enemyASprite;
	public static Image heroBulletSprite;
	public static Image bossSprite;
	public static Image mapSprite;
	public static Image introbg;
	public static Image titleSignSprite;
	public static Media menubg;
	public static Media charbg;
	public static Media stagebg;
	public static Image buttonSprite;
	public static Image buttonHighlightSprite;
	public static Image backButtonSprite;
	public static Image backButtonHighlightSprite;
	public static Image deathSprite;
	public static Font defFont;
	public static Font boldFont;
	public static AudioClip mouseHover;
	public static AudioClip mouseClicked;
	public static Image char1Sprite;
	public static Image char1HoveredSprite;
	public static Image char1SelectedSprite;
	public static Image char2Sprite;
	public static Image char2HoveredSprite;
	public static Image char2SelectedSprite;
	public static Image charSelectSign;
	public static Image stageSelectSign;
	public static Image stage1Sprite;
	public static Image stage1SelectedSprite;
	public static Image stage2Sprite;
	public static Image stage2SelectedSprite;
	public static AudioClip shootingSound;
	public static AudioClip damagedSound;
	public static Image okSprite;
	public static Image okHighlightSprite;
	public static Image heartSprite;
	public static Image diamondSprite;
	public static Image enemyBSprite;
	public static Image gameOverScreen;
	public static Image errorBox;
	public static AudioClip mainBgMusic;
	public static AudioClip bossMusic;
	public static AudioClip battleMusic;
	public static Image homeButton;
	public static Image homeHighlightButton;
	public static Image forwardButton;
	public static Image forwardButtonHighlight;
	public static AudioClip gameOverBgm;
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		playerSprite = new Image(ClassLoader.getSystemResource("player1.png").toString());
		bulletSprite = new Image(ClassLoader.getSystemResource("bullet.png").toString());
		enemyASprite = new Image(ClassLoader.getSystemResource("enemyA1.png").toString());
		heroBulletSprite = new Image(ClassLoader.getSystemResource("heroBullet1.png").toString());
		bossSprite = new Image(ClassLoader.getSystemResource("boss1.png").toString());
		mapSprite = new Image(ClassLoader.getSystemResource("map1.png").toString());
		introbg = new Image(ClassLoader.getSystemResource("intro.png").toString());
		titleSignSprite = new Image(ClassLoader.getSystemResource("title.png").toString());
		buttonSprite = new Image(ClassLoader.getSystemResource("buttonNormal.png").toString());
		buttonHighlightSprite = new Image(ClassLoader.getSystemResource("buttonHighlight.png").toString());
		backButtonSprite = new Image(ClassLoader.getSystemResource("backButtonNormal.png").toString());
		backButtonHighlightSprite = new Image(ClassLoader.getSystemResource("backButtonHighlight.png").toString());
		deathSprite = new Image(ClassLoader.getSystemResource("deathImage1.png").toString());
		defFont = Font.loadFont(ClassLoader.getSystemResource("Otaku_Rant.ttf").toString(), 20);
		boldFont = Font.loadFont(ClassLoader.getSystemResource("Otaku-Rant-Bold.ttf").toString(), 20);
		menubg = new Media(ClassLoader.getSystemResource("menubg.mp4").toString());
		mouseHover = new AudioClip(ClassLoader.getSystemResource("mouseIn.mp3").toString());
		mouseClicked = new AudioClip(ClassLoader.getSystemResource("mouseClicked.mp3").toString());
		char1Sprite = new Image(ClassLoader.getSystemResource("char1.png").toString());
		char1HoveredSprite = new Image(ClassLoader.getSystemResource("char1Hovered.png").toString());
		char1SelectedSprite = new Image(ClassLoader.getSystemResource("char1Selected.png").toString());
		char2Sprite = new Image(ClassLoader.getSystemResource("char2.png").toString());
		char2HoveredSprite = new Image(ClassLoader.getSystemResource("char2Hovered.png").toString());
		char2SelectedSprite = new Image(ClassLoader.getSystemResource("char2Selected.png").toString());
		charSelectSign = new Image(ClassLoader.getSystemResource("charSign.png").toString());
		stageSelectSign = new Image(ClassLoader.getSystemResource("worldSign.png").toString());
		stage1Sprite = new Image(ClassLoader.getSystemResource("world1.png").toString());
		stage1SelectedSprite = new Image(ClassLoader.getSystemResource("world1Selected.png").toString());
		stage2Sprite = new Image(ClassLoader.getSystemResource("world2.png").toString());
		stage2SelectedSprite = new Image(ClassLoader.getSystemResource("world2Selected.png").toString());
		charbg = new Media(ClassLoader.getSystemResource("selectCharbg.mp4").toString());
		stagebg = new Media(ClassLoader.getSystemResource("selectStagebg.mp4").toString());
		shootingSound = new AudioClip(ClassLoader.getSystemResource("shootingSound.mp3").toString());
		damagedSound = new AudioClip(ClassLoader.getSystemResource("damageSound.mp4").toString());
		okSprite = new Image(ClassLoader.getSystemResource("okSprite.png").toString());
		okHighlightSprite = new Image(ClassLoader.getSystemResource("okHighlightSprite.png").toString());
		heartSprite = new Image(ClassLoader.getSystemResource("heartSprite.png").toString());
		diamondSprite = new Image(ClassLoader.getSystemResource("diamondSprite.png").toString());
		enemyBSprite = new Image(ClassLoader.getSystemResource("enemyB1.png").toString());
		gameOverScreen = new Image(ClassLoader.getSystemResource("gameOverScreen.png").toString());
		errorBox = new Image(ClassLoader.getSystemResource("errorBox.png").toString());
		bossMusic = new AudioClip(ClassLoader.getSystemResource("World1BossSound.mp3").toString());
		mainBgMusic = new AudioClip(ClassLoader.getSystemResource("BGMain.mp3").toString());
		battleMusic = new AudioClip(ClassLoader.getSystemResource("World1BG.mp3").toString());
		homeButton = new Image(ClassLoader.getSystemResource("homeSprite.png").toString());
		homeHighlightButton = new Image(ClassLoader.getSystemResource("homeHighlightSprite.png").toString());
		forwardButton = new Image(ClassLoader.getSystemResource("forwardButtonNormal.png").toString());
		forwardButtonHighlight = new Image(ClassLoader.getSystemResource("forwardButtonHighlight.png").toString());
		gameOverBgm = new AudioClip(ClassLoader.getSystemResource("GameOverBGM.mp3").toString());
	}

	public void add(IRenderable entity) {
		//System.out.println(entities.size());
		//System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		//for(IRenderable x: entities){
		//	if(x instanceof Player) System.out.println("player");
		//	if(x instanceof Enemy) System.out.println("Enemy");
		//	}
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
