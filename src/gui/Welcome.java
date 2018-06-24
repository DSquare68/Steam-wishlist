package gui;

import java.awt.Component;
import java.util.ArrayList;

import Resources.ResourceLoader;
import data.Language;
import data.Settings;
import data.Tree;
import data.Window;
import game.GameScanned;
import game.Games;
import game.Game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Scraper;
import sql.Sql;

public class Welcome extends AnchorPane{
	VBox componets= new VBox();
	HBox checkBoxes = new HBox();
	Label steamWishlist=new Label(), or=new Label();
	Button search= new Button(), help=new Button(), language= new Button();
	TextField id=new TextField(), profile= new TextField();
	ProgressBar pb = new ProgressBar(0);
	boolean rememberMeBool=false;
	public static boolean addToDateBaseBool=false;
	public static int  progressBarMax=1;
	public static IntegerProperty prograsssBar= new SimpleIntegerProperty(0);
	public Welcome() {
		init();
		initComponents();
	}

	private void init() {
		prograsssBar.set(0);
		final ChangeListener changeListener = new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {
				pb.setProgress(Double.valueOf(arg0.getValue().toString())/progressBarMax);				
			}
		
		};
		prograsssBar.addListener(changeListener);
		pb.indeterminateProperty();
		//pb.progressProperty().add(prograsssBar);
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		steamWishlist.setFont(new Font("ARIAL", Window.welcome.height/12));
		steamWishlist.setAlignment(Pos.CENTER);
		or.setFont(new Font("ARIAL", Window.welcome.height/14));
		or.setAlignment(Pos.CENTER);
		search.setFont(new Font("ARIAL", Window.welcome.height/14));
		search.setAlignment(Pos.CENTER);
		
		id.setPromptText("ID");
		profile.setPromptText("Profile");
		
		ImageView imagev=ResourceLoader.getImageView(ResourceLoader.HELP);
		help.setPrefSize(Window.welcome.width/12,Window.welcome.width/12);
		imagev.setFitHeight(help.getPrefHeight());
		imagev.setFitWidth(help.getPrefWidth());
		help.setGraphic(imagev);
		help.setBackground(null);
		help.setTranslateX(Window.welcome.width-help.getPrefWidth()*1.5);
		help.setTranslateY(Window.welcome.height-help.getPrefHeight()*1.5);
		
		Image image = ResourceLoader.getImage(ResourceLoader.LANGUAGE_PL);
		double width=image.getWidth();
		double height=image.getHeight();
		double scale=width/height;
		language.setPrefSize(Window.welcome.width/12,Window.welcome.width/(12*scale));
		imagev = new ImageView(image);
		language.setGraphic(imagev);
		imagev.setFitHeight(language.getPrefHeight());
		imagev.setFitWidth(language.getPrefWidth());
		language.setBackground(null);
		language.setTranslateX(help.getPrefWidth()*0.25);
		language.setTranslateY(Window.welcome.height-language.getPrefHeight()*2);
		language.setOnAction(e->{
			Settings.switchLanguage();
			new Language(Settings.language);
			ImageView imageview=ResourceLoader.getImageView(Settings.language+".jpg");
			imageview.setFitHeight(language.getPrefHeight());
			imageview.setFitWidth(language.getPrefWidth());
			language.setGraphic(imageview);
			setText();
		});
		componets.getChildren().addAll(steamWishlist,id, or, profile, search);
		componets.getChildren().forEach(e->{
			((Control) e).setFocusTraversable(false);
			((Control) e).setPrefWidth(Window.welcome.width/2);
			((Control) e).setPrefHeight(Window.welcome.height/10);
		});
		search.setOnAction(e->{
			pb.setPrefWidth(Window.welcome.width/3);
			pb.setLayoutX(0);
			pb.setLayoutY(0);
			Stage progress = new Stage();
			Scene progresS = new Scene(pb);
			progress.setScene(progresS);
			
			Task<Integer> dataLoadingTask= new Task<Integer>() {
	            @Override
	            public Integer call() throws Exception {
	                // mimic connecting to database
	            	Scraper scrap= new Scraper(id.getText(), Long.valueOf(profile.getText().equals("") ? "0" : profile.getText()));
	    			if(rememberMeBool) { Settings.setID(id.getText()); Settings.setProfile(Long.valueOf(profile.getText())); Settings.switchFirstOpen();Settings.switchShowWelcome();}
	    			if(addToDateBaseBool) {
	    				ArrayList<GameScanned> gS= scrap.wczytajSource();
	    				Tree<Double> treeWishList = Sql.get.getWishNumber(null);
	    				ArrayList<Game> game =  new ArrayList<Game>();
	    				gS.forEach(e->game.add(new Game(e,treeWishList==null ? 0.0 : treeWishList.find(treeWishList.getRoot(), e.getID()))));
	    				Sql.Insert.insertOrReplaceGameScanned(gS); 
	    				Sql.Insert.insertOrReplaceGames(game);
	    			}
	    			succeeded();
	                return 0;
	            }
	            
	        };
			
			progress.show();
			Thread t=  new Thread(dataLoadingTask);
			dataLoadingTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				
				@Override
				public void handle(WorkerStateEvent event) {
					Central centralPane = new Central();
					Stage cental = new Stage();
			        cental.setTitle("Stream wishlist");
					cental.setScene(new Scene(centralPane, Window.central.width, Window.central.height));
					cental.show();
					if(Settings.firstOpen) {
						FirstRun root = new FirstRun(centralPane);
						Stage stage = new Stage();
						stage.setTitle("Stream wishlist");
						stage.setScene(new Scene(root, Window.firstRun.width, Window.firstRun.height));
						stage.setAlwaysOnTop(true);
						stage.show();
					}	
					progress.hide();
				}
			});
			t.start();
			getScene().getWindow().hide();
		});
		componets.setTranslateY(Window.welcome.height/20);
		componets.setTranslateX(Window.welcome.width/4);
		componets.setSpacing(Window.welcome.height/50);
		CheckBox rememberMe = new CheckBox();
		rememberMe.setOnAction(f->rememberMeBool=rememberMe.isSelected());
		rememberMe.setPrefWidth(Window.welcome.width/4);
		CheckBox addToDatebase = new CheckBox();
		addToDatebase.setOnAction(f->addToDateBaseBool=addToDatebase.isSelected());
		addToDatebase.setPrefWidth(Window.welcome.width/4);
		checkBoxes.getChildren().addAll(rememberMe,addToDatebase);
		componets.getChildren().add(checkBoxes);
		setText();
		this.getChildren().addAll(componets,language,help);
		
	}

	private void setText() {
		steamWishlist.setText(Language.texts.getString("steam_wishlist"));
		or.setText(Language.texts.getString("or"));
		search.setText(Language.texts.getString("search"));
		((CheckBox) checkBoxes.getChildren().get(0)).setText(Language.texts.getString("remember_me"));
		((CheckBox) checkBoxes.getChildren().get(1)).setText(Language.texts.getString("add_to_datebase"));
	}
}
