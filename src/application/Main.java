package application;



import javafx.application.Application;
import javafx.stage.Stage;
import sharedObject.SceneManager;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		//Game Window
		primaryStage.setTitle("Bullet Hell");
		primaryStage.setScene(SceneManager.getInstance().getScene());
		//Init static var
		//Group root = new Group();
		//Scene mainScene = new Scene(root);
		//GameScreen gameScreen = new GameScreen(800,600);
		//root.getChildren().add(gameScreen);
		//GameLogic logic = new GameLogic();
		//primaryStage.setScene(mainScene);
	    //gameScreen.requestFocus();
	    primaryStage.setResizable(false);
		primaryStage.show();
		//AnimationTimer animation = new AnimationTimer() {
		//	public void handle(long now) {
		//		gameScreen.paintComponent();
		//		logic.logicUpdate();
		//		RenderableHolder.getInstance().update();
		//		InputUtility.updateInputState();
		//	}
		//};
		//animation.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
