package logic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class FooterLogic {

	public static void setFooterStyle(HBox footer) {
		footer.getChildren().forEach(e->{
			e.setOnMouseEntered(f->((Label) e).setTextFill(new Color(0.9, 0.3,0.3,1.0)));
			e.setOnMouseExited(f->((Label) e).setTextFill(new Color(0.3,0.3,0.8, 1.0)));
			
		});
		
	}

	public static void setFooterAction(HBox footer) {
		footer.getChildren().forEach(e->{
			switch (footer.getChildren().indexOf(e)) {
			case 0:
				infoAction(e);
				break;
			case 1:
				authorAction(e);
				break;
			case 2:
				settingsAction(e);
				break;
				default: System.out.println("No such label in footer."); break;
			}
		});
	}

	private static void infoAction(Node e) {
		// TODO Auto-generated method stub
		
	}

	private static void authorAction(Node e) {
		// TODO Auto-generated method stub
		
	}

	private static void settingsAction(Node e) {
		// TODO Auto-generated method stub
		
	}

}
