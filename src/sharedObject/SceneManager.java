package sharedObject;

import application.GameScreen;
import data.GameManager;
import exception.CharNotSelectException;
import exception.StageNotSelectException;
import gui.HoverButton;
import gui.ToggleHoverButton;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.Player;

public class SceneManager {
	private static final SceneManager instance = new SceneManager();
	private static GameScreen gm;
	private static Group intro;
	private static Group stageSelect;
	private static Group charSelect;
	private static Group gameRoot;
	private static Group menuRoot;
	private static Group howtoplayRoot;
	private static Group gameEndRoot;
	private static Scene scene;
	private static int currentPage;
	private static Image currentTutorial;
	private static ImageView tutorialPage;
	static {
		initScene();
	}
	public SceneManager() {
		gm = new GameScreen(800,600);
	}
	public Scene getScene() {
		return scene;
	}
	public static void initScene() {
		intro = new Group();
		stageSelect = new Group();
		charSelect = new Group();
		gameRoot = new Group();
		menuRoot = new Group();
		gameEndRoot = new Group();
		howtoplayRoot = new Group();
		scene = new Scene(intro,800,600);
		Text gameEndText = new Text();
		Text scoreText = new Text();
		gameEndText.setFont(RenderableHolder.boldFont);
		MediaPlayer mp = new MediaPlayer(RenderableHolder.menubg);
		MediaView mv = new MediaView(mp);
		MediaPlayer stageMp = new MediaPlayer(RenderableHolder.stagebg);
		MediaView stageMv = new MediaView(stageMp);
		MediaPlayer charMp = new MediaPlayer(RenderableHolder.charbg);
		MediaView charMv = new MediaView(charMp);
		MediaPlayer howtoplayMp = new MediaPlayer(RenderableHolder.stagebg);
		MediaView howtoplayMv = new MediaView(stageMp);
		GameLogic logic = new GameLogic();
		AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			gm.paintComponent();
			logic.logicUpdate();
			RenderableHolder.getInstance().update();
			InputUtility.updateInputState();		
			scoreText.setText(""+GameManager.getScore());
			if(GameManager.isGameLost() || GameManager.isGameWon()) {
				RenderableHolder.battleMusic.stop();
				RenderableHolder.bossMusic.stop();
				if (GameManager.isGameLost()) {
					gameEndText.setText("You Lost");
				}
				if (GameManager.isGameWon()) {
					gameEndText.setText("You Won");
				}
				scene.setRoot(gameEndRoot);
			}
			}
		};	
		
		//Intro
		HoverButton introBtn = new HoverButton("Start Game", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		introBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				RenderableHolder.mainBgMusic.play();
				scene.setRoot(menuRoot);
				mp.play();
			}
		});
		HoverButton exitBtn = new HoverButton("Exit", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
				System.exit(0);
			}
		});
		introBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		introBtn.setLayoutY(400 - RenderableHolder.buttonSprite.getWidth()/2);
		ImageView introSign = new ImageView(RenderableHolder.titleSignSprite);
		introSign.setLayoutX(400 - RenderableHolder.titleSignSprite.getWidth()/2);
		introSign.setLayoutY(150 - RenderableHolder.titleSignSprite.getHeight()/2);
		//Menu
		HoverButton backBtn = new HoverButton("", RenderableHolder.backButtonSprite, RenderableHolder.backButtonHighlightSprite);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(menuRoot);
			}
		});
		HoverButton back2Btn = new HoverButton("", RenderableHolder.backButtonSprite, RenderableHolder.backButtonHighlightSprite);
		back2Btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(menuRoot);
			}
		});
		HoverButton stageBtn = new HoverButton("Select Stage", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		stageBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(stageSelect);
				stageMp.play();
			}
		});
		HoverButton charBtn = new HoverButton("Select Character", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		charBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(charSelect);
				charMp.play();
			}
		});
		HoverButton howtoplayBtn = new HoverButton("How to play", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		howtoplayBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(howtoplayRoot);
				mp.stop();
				howtoplayMp.play();
			}
		});
		HoverButton gameBtn = new HoverButton("Play Game", RenderableHolder.buttonSprite, RenderableHolder.buttonHighlightSprite);
		gameBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (GameManager.getChar() == 0) {
						throw new CharNotSelectException();
					}
					if (GameManager.getStage() == 0) {
						throw new StageNotSelectException();
					}
					RenderableHolder.mainBgMusic.stop();
					
					RenderableHolder.battleMusic.setPriority(100000000);
					RenderableHolder.battleMusic.play();
					//Reset everything
					mp.stop();
					charMp.stop();
					stageMp.stop();
					RenderableHolder.getInstance().getEntities().clear();
					logic.resetList();
					InputUtility.clearInput();
					GameManager.setGameLost(false);
					GameManager.setGameWon(false);
					Player.health = 3;
					GameManager.resetScore();
					scene.setRoot(gameRoot);
					gm.requestFocus();
					animation.start();
				}
				catch (CharNotSelectException e) {
					Rectangle b = new Rectangle(0,0,800,600);
					b.setFill(Color.BLACK);
					ImageView err = new ImageView(RenderableHolder.errorBox);
					Text errText = new Text("You gotta select someone!");
					errText.setFont(Font.loadFont(ClassLoader.getSystemResource("Otaku_Rant.ttf").toString(), 10));
					HoverButton okerr = new HoverButton("", RenderableHolder.okSprite, RenderableHolder.okHighlightSprite);
					okerr.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							menuRoot.getChildren().removeAll(b,err,errText,okerr);
						}
					});
					err.setLayoutX(400-RenderableHolder.buttonSprite.getWidth()/2);
					err.setLayoutY(200-RenderableHolder.buttonSprite.getHeight()/2);
					errText.setLayoutX(430 - RenderableHolder.buttonSprite.getWidth()/2);
					errText.setLayoutY(200);
					okerr.setLayoutX(400-RenderableHolder.okSprite.getWidth()/2);
					okerr.setLayoutY(400-RenderableHolder.okSprite.getHeight()/2);
					menuRoot.getChildren().addAll(b,err,errText,okerr);
				}
				catch (StageNotSelectException e) {
					Rectangle b = new Rectangle(0,0,800,600);
					b.setFill(Color.BLACK);
					ImageView err = new ImageView(RenderableHolder.errorBox);
					Text errText = new Text("You gotta select some stage!");
					errText.setFont(Font.loadFont(ClassLoader.getSystemResource("Otaku_Rant.ttf").toString(), 10));
					HoverButton okerr = new HoverButton("", RenderableHolder.okSprite, RenderableHolder.okHighlightSprite);
					okerr.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							menuRoot.getChildren().removeAll(b,err,errText,okerr);
						}
					});
					err.setLayoutX(400-RenderableHolder.buttonSprite.getWidth()/2);
					err.setLayoutY(200-RenderableHolder.buttonSprite.getHeight()/2);
					errText.setLayoutX(430 - RenderableHolder.buttonSprite.getWidth()/2);
					errText.setLayoutY(200);
					okerr.setLayoutX(400-RenderableHolder.okSprite.getWidth()/2);
					okerr.setLayoutY(400-RenderableHolder.okSprite.getHeight()/2);
					menuRoot.getChildren().addAll(b,err,errText,okerr);
				}
			}
		});
		HoverButton back4Btn = new HoverButton("", RenderableHolder.backButtonSprite, RenderableHolder.backButtonHighlightSprite);
		back4Btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(intro);
			}
		});
		//Char select
		ImageView charSign = new ImageView(RenderableHolder.charSelectSign);
		ToggleHoverButton char1 = new ToggleHoverButton("", RenderableHolder.char1Sprite, RenderableHolder.char1HoveredSprite, RenderableHolder.char1SelectedSprite);
		ToggleHoverButton char2 = new ToggleHoverButton("", RenderableHolder.char2Sprite, RenderableHolder.char2HoveredSprite, RenderableHolder.char2SelectedSprite);
		char1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.setChar(1);
				char2.resetToggled();
			}
		});
		
		char2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.setChar(2);
				char1.resetToggled();
			}
		});
		//Stage select
		ImageView stageSign = new ImageView(RenderableHolder.stageSelectSign);
		ToggleHoverButton stage1 = new ToggleHoverButton("", RenderableHolder.stage1Sprite, RenderableHolder.stage1SelectedSprite, RenderableHolder.stage1SelectedSprite);
		ToggleHoverButton stage2 = new ToggleHoverButton("", RenderableHolder.stage2Sprite, RenderableHolder.stage2SelectedSprite, RenderableHolder.stage2SelectedSprite);
		stage1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.setStage(1);
				stage2.resetToggled();
			}
		});
		
		stage2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.setStage(2);
				stage1.resetToggled();
			}
		});
		
		//return button
		HoverButton back3Btn = new HoverButton("", RenderableHolder.homeButton, RenderableHolder.homeHighlightButton);
		back3Btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				animation.stop();
				scene.setRoot(intro);

				RenderableHolder.mainBgMusic.play();
				//Resetgamemanager
				GameManager.resetSelected();
				char1.resetToggled();
				char2.resetToggled();
				stage1.resetToggled();
				stage2.resetToggled();
			}
		});
		//Name Button
//		HoverButton okBtn = new HoverButton("", RenderableHolder.okSprite, RenderableHolder.okHighlightSprite);
//		okBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				scene.setRoot(menuRoot);
//			}
//		});
		//Howtoplay
		currentTutorial = new Image(ClassLoader.getSystemResource("tutorial1.png").toString());
		currentPage = 1;
		tutorialPage = new ImageView(currentTutorial);
		HoverButton homeBtn = new HoverButton("", RenderableHolder.homeButton, RenderableHolder.homeHighlightButton);
		homeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				scene.setRoot(intro);
				howtoplayMp.stop();
				mp.play();
			}
		});
		HoverButton backwardBtn = new HoverButton("", RenderableHolder.backButtonSprite, RenderableHolder.backButtonHighlightSprite);
		HoverButton forwardBtn = new HoverButton("", RenderableHolder.forwardButton, RenderableHolder.forwardButtonHighlight);
		backwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (currentPage > 1) {
					currentPage--;
					currentTutorial = new Image(ClassLoader.getSystemResource("tutorial" + currentPage + ".png").toString());
					tutorialPage.setImage(currentTutorial);
					if (currentPage == 4) {
						howtoplayRoot.getChildren().add(forwardBtn);
					}
					if (currentPage == 1) {
						RenderableHolder.mouseClicked.play();
						howtoplayRoot.getChildren().removeAll(backwardBtn);
					}
				}
			}
		});
		
		forwardBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (currentPage < 5) {
					currentPage++;
					currentTutorial = new Image(ClassLoader.getSystemResource("tutorial" + currentPage + ".png").toString());
					tutorialPage.setImage(currentTutorial);
					if (currentPage == 2) {
						howtoplayRoot.getChildren().add(backwardBtn);
					}
					if (currentPage == 5) {
						RenderableHolder.mouseClicked.play();
						howtoplayRoot.getChildren().removeAll(forwardBtn);
					}
				}
			}
		});
		
		backwardBtn.setLayoutX(200);
		backwardBtn.setLayoutY(550);
		forwardBtn.setLayoutX(600);
		forwardBtn.setLayoutY(550);
		homeBtn.setLayoutX(400);
		homeBtn.setLayoutY(550);
//		okBtn.setLayoutX(400-RenderableHolder.okSprite.getWidth()/2);
//		okBtn.setLayoutY(500-RenderableHolder.okSprite.getHeight()/2);
		//GameEndScreen
		ImageView gameEndScreen = new ImageView(RenderableHolder.gameOverScreen);
		scoreText.setLayoutX(400);
		scoreText.setLayoutY(300);
		backBtn.setLayoutX(100 - RenderableHolder.backButtonSprite.getWidth()/2);
		backBtn.setLayoutY(500 - RenderableHolder.backButtonSprite.getHeight()/2);
		back2Btn.setLayoutX(100 - RenderableHolder.backButtonSprite.getWidth()/2);
		back2Btn.setLayoutY(500 - RenderableHolder.backButtonSprite.getHeight()/2);
		stageBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		stageBtn.setLayoutY(150 - RenderableHolder.buttonSprite.getHeight()/2);
		charBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		charBtn.setLayoutY(300 - RenderableHolder.buttonSprite.getHeight()/2);
		gameBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		gameBtn.setLayoutY(450 - RenderableHolder.buttonSprite.getHeight()/2);
		char1.setLayoutX(200 - RenderableHolder.char1Sprite.getWidth()/2);
		char1.setLayoutY(300 - RenderableHolder.char1Sprite.getHeight()/2);
		char2.setLayoutX(600 - RenderableHolder.char2Sprite.getWidth()/2);
		char2.setLayoutY(300 - RenderableHolder.char2Sprite.getHeight()/2);
		charSign.setLayoutX(400 - RenderableHolder.charSelectSign.getWidth()/2);
		charSign.setLayoutY(100 - RenderableHolder.charSelectSign.getHeight()/2);
		stage1.setLayoutX(200 - RenderableHolder.stage1Sprite.getWidth()/2);
		stage1.setLayoutY(300 - RenderableHolder.stage1Sprite.getHeight()/2);
		stage2.setLayoutX(600 - RenderableHolder.stage2Sprite.getWidth()/2);
		stage2.setLayoutY(300 - RenderableHolder.stage2Sprite.getHeight()/2);
		stageSign.setLayoutX(400 - RenderableHolder.stageSelectSign.getWidth()/2);
		stageSign.setLayoutY(100 - RenderableHolder.stageSelectSign.getHeight()/2);
		gameEndText.setLayoutX(300);
		gameEndText.setLayoutY(200);
		back3Btn.setLayoutX(400);
		back3Btn.setLayoutY(500);
		howtoplayBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		howtoplayBtn.setLayoutY(500 - RenderableHolder.buttonSprite.getWidth()/2);
		exitBtn.setLayoutX(400 - RenderableHolder.buttonSprite.getWidth()/2);
		exitBtn.setLayoutY(600 - RenderableHolder.buttonSprite.getWidth()/2);
		back4Btn.setLayoutX(100 - RenderableHolder.backButtonSprite.getWidth()/2);
		back4Btn.setLayoutY(500 - RenderableHolder.backButtonSprite.getHeight()/2);
		intro.getChildren().add(new ImageView(RenderableHolder.introbg));
		intro.getChildren().addAll(introBtn, howtoplayBtn, exitBtn);
		intro.getChildren().add(introSign);
		menuRoot.getChildren().add(mv);
		menuRoot.getChildren().addAll(stageBtn,charBtn,gameBtn, back4Btn);
		charSelect.getChildren().add(charMv);
		charSelect.getChildren().addAll(charSign, char1, char2, backBtn);
		stageSelect.getChildren().add(stageMv);
		stageSelect.getChildren().addAll(stageSign, stage1, stage2, back2Btn);
		gameRoot.getChildren().add(gm);
		howtoplayRoot.getChildren().addAll(howtoplayMv, tutorialPage,homeBtn, forwardBtn);
		gameEndRoot.getChildren().addAll(gameEndScreen, gameEndText, scoreText, back3Btn);
	}
	public static SceneManager getInstance() {
		return instance;
	}
}
