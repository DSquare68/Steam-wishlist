package gui;

import data.Settings;
import data.Window;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class FirstRun extends StackPane{
	Label scaling = new Label();
	Slider scale = new Slider();
	Central central=null;
	public FirstRun(Central central) {
		init();
		initComponents();
		this.central=central;
	}


	private void init() {
		// TODO Auto-generated method stub
		
	}
	private void initComponents() {
		
		scaling.setText("Scale");
		scaling.setFont(new Font("ARIAL", Window.welcome.height/12));
		scaling.setAlignment(Pos.CENTER);
		scaling.setPrefSize(Window.firstRun.width/3, Window.firstRun.height/5);
		scaling.setTranslateX(0);
		scaling.setTranslateY(-2*Window.firstRun.height/5);
		
		scale.setMin(-2);
		scale.setMax(2);
		scale.setMinorTickCount(10);
		scale.setMajorTickUnit(1);
		scale.setShowTickMarks(true);
		scale.setShowTickLabels(true);
		scale.setSnapToTicks(true);
		scale.setValue(0);
		scale.setMaxSize(4*Window.firstRun.width/5, Window.firstRun.height/5);
		scale.setPrefWidth(4*Window.firstRun.width/5);
		scale.setTranslateX(0);
		scale.setTranslateY(-1*Window.firstRun.height/5);
		scale.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                        Settings.setScaleWindwo(new_val.doubleValue());
                        central.scaleAllNodes();
                }
		});
		
		this.getChildren().addAll(scaling, scale);
		
	}

}
