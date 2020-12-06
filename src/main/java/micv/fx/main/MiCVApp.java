package micv.fx.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MiCVApp extends Application {

	private MainController mainController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainController = new MainController();
		
		Scene scene = new Scene(mainController.getRootView(), 800, 600);
		primaryStage.setTitle("MiCV");
		primaryStage.getIcons().add(new Image(getClass().getResource("/images/cv64x64.png").toString()));
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
