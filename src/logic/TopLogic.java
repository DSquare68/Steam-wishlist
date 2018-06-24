package logic;

import java.awt.Dimension;
import java.awt.print.Printable;

import data.Settings;
import game.Games;
import gui.Central;
import gui.Filtr;
import gui.Welcome;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import sql.Sql;

public class TopLogic {
	public static void setTopButtonsStyle(HBox top, int start, int end) {
		for(int i=start;i<end;i++) {
			Button button =(Button) top.getChildren().get(i);
			button.setOnMouseEntered(e->{button.setStyle("-fx-background-color: #AAAAAA;");});
			button.setOnMouseExited(e->{button.setStyle("-fx-background-color: TRANSPARENT;");});
		}
	}

	public static void setTopButtonsAction(HBox top, int start, int end) {
		for(int i=start;i<end;i++) {
			Button button =(Button) top.getChildren().get(i);
			switch(i) {
				case 0:
					homeAction(button);
					break;
				case 1:
					quickShopAction(button);
					break;
				case 2:
					advanceShopAction(button);
					break;
				case 3:
					pickingAction(button);
					break;
				default:
					System.out.println("Wrong number of buttons in top");
					break;
			}
		}
		
	}
	private static void homeAction(Button button) {
		// TODO Auto-generated method stub
		
	}

	private static void quickShopAction(Button button) {
		// TODO Auto-generated method stub
		
	}

	private static void advanceShopAction(Button button) {
		// TODO Auto-generated method stub
		
	}

	private static void pickingAction(Button button) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void setFiltrAction(Button filtrButton) {
		final Filtr filtr=  new Filtr();
		filtrButton.setOnAction(e->{
			Task<Integer> filtrTask= new Task<Integer>() {
	            @Override
	            public Integer call() throws Exception {
	            	setFiltrAction(filtr);
					filtr.setLocation(new Dimension((int) (filtrButton.getLayoutX()+filtrButton.getWidth()/2),(int) (filtrButton.getLayoutY()+filtrButton.getHeight())));
					((Central) filtrButton.getScene().getRoot()).getChildren().add(filtr);
	    			succeeded();
	                return 0;
	            }
	            
	        };
	        filtrTask.setOnSucceeded(f->{filtr.setAction();});
			filtrTask.run();
		});
	}

	private static void setFiltrAction(Filtr filtr) {
		// TODO Auto-generated method stub
		
	}
}
