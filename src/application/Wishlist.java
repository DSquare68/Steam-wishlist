package application;
	
import data.Language;
import data.SetSettings;
import data.Settings;
import data.Window;
import game.Games;
import gui.Central;
import gui.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;
import sql.Sql;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Wishlist extends Application {
	static Sql sql;
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene=null;
			if(Settings.ID.equals("id")&&Settings.profile==123) {
				Welcome root = new Welcome();
				scene = new Scene(root,Window.welcome.width,Window.welcome.height);
			} else {
				Central centralPane = new Central();
				scene = new Scene(centralPane,Window.central.width,Window.central.height);
			}
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SetSettings.read();
		//Settings.setID("id"); Settings.setProfile(Long.valueOf(123)); Settings.switchFirstOpen();Settings.switchShowWelcome();
		Sql.init();
		sql = new Sql();
		sql.create();
		new Language(Settings.language);
		new Window();
		launch(args);
	}
}
