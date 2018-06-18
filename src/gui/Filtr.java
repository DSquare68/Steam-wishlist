package gui;

import java.awt.Dimension;

import javax.xml.stream.EventFilter;

import data.Language;
import data.Window;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.TopLogic;

public class Filtr extends StackPane{
	
	HBox main = new  HBox();
	VBox above = new VBox();
	VBox below = new VBox();
	VBox rate = new VBox();
	VBox wishNumber = new VBox();
	VBox tags = new VBox();
	Button filtr = new Button(Language.texts.getString("filtr"));
	EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			hide(event);
		}
		
	};
	public Filtr() {
		init();
		initAbove();
		initBelow();
		initRate();
		initWishNumber();
		initTags();
	}
	
	private void init() {
		this.setWidth(Window.filtr.width);
		this.setHeight(Window.filtr.height);
		this.setStyle("-fx-background-color: #FFFFFF ;");
		main.getChildren().addAll(above,below,rate,wishNumber,tags);
		main.setSpacing(Window.filtr.width/25);
		this.getChildren().add(main);
	}

	
	private void initAbove() {
		Label title = new Label(Language.texts.getString("above"));
		title.setPrefWidth(Window.filtr.width/8);
		title.setPrefHeight(Window.filtr.height/5);
		title.setFont(new Font("ARIAL",Window.filtr.height/8));
		above.getChildren().add(title);
		
		String[] checkLabels= {Language.texts.getString("5"),Language.texts.getString("10"),Language.texts.getString("20")};
		ToggleGroup group = new ToggleGroup();
		for(int i=0;i<3;i++) {
			RadioButton check = new RadioButton(checkLabels[i]);
			check.setToggleGroup(group);
			check.setFont(new Font("ARIAL",Window.filtr.height/45));
			check.setPrefWidth(Window.filtr.width/6);
			check.setPrefHeight(Window.filtr.height/5);
			check.setFont(new Font("ARIAL",Window.filtr.height/20));
			above.getChildren().addAll(check);
		}
		
		TextField field = new TextField();
		field.setPromptText(Language.texts.getString("$"));
		field.setPrefWidth(Window.filtr.width/8);
		field.setPrefHeight(Window.filtr.height/5);
		field.setFont(new Font("ARIAL",Window.filtr.height/20));
		above.getChildren().add(field);
		
	}

	private void initBelow() {
		Label title = new Label(Language.texts.getString("above"));
		title.setPrefWidth(Window.filtr.width/8);
		title.setPrefHeight(Window.filtr.height/5);
		title.setFont(new Font("ARIAL",Window.filtr.height/8));
		below.getChildren().add(title);
		
		String[] checkLabels= {Language.texts.getString("5"),Language.texts.getString("10"),Language.texts.getString("20")};
		ToggleGroup group = new ToggleGroup();
		for(int i=0;i<3;i++) {
			RadioButton check = new RadioButton(checkLabels[i]);
			check.setToggleGroup(group);
			check.setFont(new Font("ARIAL",Window.filtr.height/45));
			check.setPrefWidth(Window.filtr.width/6);
			check.setPrefHeight(Window.filtr.height/5);
			check.setFont(new Font("ARIAL",Window.filtr.height/20));
			below.getChildren().addAll(check);
		}
		
		TextField field = new TextField();
		field.setPromptText(Language.texts.getString("$"));
		field.setPrefWidth(Window.filtr.width/8);
		field.setPrefHeight(Window.filtr.height/5);
		field.setFont(new Font("ARIAL",Window.filtr.height/20));
		below.getChildren().add(field);
	}

	private void initRate() {
		Label title = new Label(Language.texts.getString("above"));
		title.setPrefWidth(Window.filtr.width/8);
		title.setPrefHeight(Window.filtr.height/5);
		title.setFont(new Font("ARIAL",Window.filtr.height/8));
		rate.getChildren().add(title);
		
		String[] checkLabels= {Language.texts.getString("5"),Language.texts.getString("10"),Language.texts.getString("20")};
		ToggleGroup group = new ToggleGroup();
		for(int i=0;i<3;i++) {
			RadioButton check = new RadioButton(checkLabels[i]);
			check.setToggleGroup(group);
			check.setPrefWidth(Window.filtr.width/8);
			check.setPrefHeight(Window.filtr.height/5);
			check.setFont(new Font("ARIAL",Window.filtr.height/20));
			rate.getChildren().addAll(check);
		}
		
		TextField field = new TextField();
		field.setPromptText(Language.texts.getString("$"));
		field.setPrefWidth(Window.filtr.width/8);
		field.setPrefHeight(Window.filtr.height/5);
		field.setFont(new Font("ARIAL",Window.filtr.height/20));
		rate.getChildren().add(field);
	}

	private void initWishNumber() {
		Label title = new Label(Language.texts.getString("above"));
		title.setPrefWidth(Window.filtr.width/8);
		title.setPrefHeight(Window.filtr.height/5);
		title.setFont(new Font("ARIAL",Window.filtr.height/8));
		wishNumber.getChildren().add(title);
		
		String[] checkLabels= {Language.texts.getString("5"),Language.texts.getString("10"),Language.texts.getString("20")};
		ToggleGroup group = new ToggleGroup();
		for(int i=0;i<3;i++) {
			RadioButton check = new RadioButton(checkLabels[i]);
			check.setToggleGroup(group);
			check.setPrefWidth(Window.filtr.width/5);
			check.setPrefHeight(Window.filtr.height/5);
			check.setFont(new Font("ARIAL",Window.filtr.height/20));
			wishNumber.getChildren().addAll(check);
		}
		
		TextField field = new TextField();
		field.setPromptText(Language.texts.getString("$"));
		field.setPrefWidth(Window.filtr.width/8);
		field.setPrefHeight(Window.filtr.height/5);
		field.setFont(new Font("ARIAL",Window.filtr.height/20));
		wishNumber.getChildren().add(field);
	}

	private void initTags() {
		Label title = new Label(Language.texts.getString("above"));
		title.setPrefWidth(Window.filtr.width/8);
		title.setPrefHeight(Window.filtr.height/5);
		title.setFont(new Font("ARIAL",Window.filtr.height/8));
		tags.getChildren().add(title);
		
		for(int i=0;i<4;i++) {
			AutoCompleteTextField auto= new AutoCompleteTextField();
			auto.setPromptText(Language.texts.getString("$"));
			auto.setPrefWidth(Window.filtr.width/8);
			auto.setPrefHeight(Window.filtr.height/5);
			auto.setFont(new Font("ARIAL",Window.filtr.height/20));
			tags.getChildren().add(auto);
		}
		
	}
	public void setLocation(Dimension location) {
		this.setLayoutX(location.getWidth()-this.getWidth()/2);
		this.setLayoutY(location.getHeight());
		
	}
	
	public void setAction() {
		//TODO when button pressed and we want to hide but setOnMouseExited was not used
		((Central) this.getParent()).addEventFilter(MouseEvent.MOUSE_CLICKED,event);
	}

	public void hide(MouseEvent e) {

		if((this.getLayoutX()<e.getX()&&e.getX()<(this.getLayoutX()+this.getWidth())&&this.getLayoutY()<e.getY()&&e.getY()<(this.getLayoutY()+this.getHeight()))) return;
		if(this.getParent()!=null) {
			this.getParent().removeEventFilter(MouseEvent.MOUSE_CLICKED, event);
			((Central) this.getParent()).getChildren().remove(this);
		}
		
		
	}
}
