package gui;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.text.ChangedCharSetException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AutoCompleteTextField extends TextField{
	//TOOO scroll pane
	private ArrayList<String> items;
	private StringProperty stringProperty = new SimpleStringProperty();
	private Results results;
	int nrHover=Integer.MIN_VALUE;
	
	public AutoCompleteTextField() {
		results = new Results(this);
		items = new ArrayList<String>();
	}
	public AutoCompleteTextField(String[] items) {
		this();
		int i=0;
		for(@SuppressWarnings("unused") String item : items) {
				this.items.add(items[i++]);
		}
		setOnTextChangedListener();
		setOnKeyListener();
	}
	private void setOnKeyListener() {
		this.setOnKeyPressed(e->{
			switch(e.getCode()) {
			case UP:
				boolean first=false;
				if(nrHover==Integer.MIN_VALUE) first=true;
				if(first) {
					nrHover=results.size()-1;
					results.result(nrHover).setStyle("-fx-background-color: #FFFF22;"
							+ "-fx-text-fill: #9976a3");
		
				} else {
					results.result(nrHover).setStyle("-fx-background-color: #FFFFFF;"
							+ "-fx-text-fill: #0076a3");
					if(nrHover>0) {
						nrHover=(nrHover-1)%(results.size());
					} else {
						nrHover=results.size()-1;
					}
					results.result(nrHover).setStyle("-fx-background-color: #FFFF22;"
							+ "-fx-text-fill: #9976a3");
				}
				results.setnrHover(nrHover);
				break;
			case DOWN: 
				boolean firstD=false;
				if(nrHover==Integer.MIN_VALUE) firstD=true;
				if(firstD) {
					nrHover=0;
					results.result(nrHover).setStyle("-fx-background-color: #FFFF22;"
							+ "-fx-text-fill: #9976a3");
				} else {
					results.result(nrHover).setStyle("-fx-background-color: #FFFFFF;"
							+ "-fx-text-fill: #0076a3");
					nrHover=(nrHover+1)%(results.size());
					results.result(nrHover).setStyle("-fx-background-color: #FFFF22;"
							+ "-fx-text-fill: #9976a3");
				}
				results.setnrHover(nrHover);
				break;
			case ENTER:
				if(nrHover!=Integer.MIN_VALUE) {
					this.setText(results.result(nrHover).getText()); 
					results.hide();
				}
				break;
			default:
				break;

			
			}
		});
		
	}
	//CHANE TO OBJECT WHEN UPDATE 
	public AutoCompleteTextField( ArrayList<String> animeNames) {
		this();
		setOnTextChangedListener();
		animeNames.forEach(i->items.add(i));
		setOnKeyListener();

	}
	public void setOnTextChangedListener() {
		AutoCompleteTextField auto = this;
		stringProperty.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue) {
				items.forEach(i->{
					if(i.contains(newValue)) {
					results.addResult(i);
					}
				});
				
			}
		});
		this.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(newValue==null||newValue.equals("")) {
					results.hide();
					
					
				} else {
					results.hide();
					results.deleteAll();
					stringProperty.set(newValue);
					results.show();
				}
			}
		});
	}
	public void setParent(Pane pane) {
		results.setParent(pane);
	}
	public void setNrHover(int nrHover) {
		this.nrHover=nrHover;
	}
	class Results extends StackPane{
		
		private ArrayList<TextField> results;
		private ScrollPane scroll= new ScrollPane();
		AutoCompleteTextField textField;
		Pane pane;
		int nrHover;
		public Results(AutoCompleteTextField textField) {
			this.textField=textField;	
			results= new ArrayList<TextField>();
		}
		public void setnrHover(int nr) {
			nrHover=nr;
		}
		public void deleteAll() {
			results.clear();
			
		}
		public void addResult(String result) {
			results.add(new TextField(result));
			results.sort(new ComparatorTextField());
		}
		public void setParent(Pane pane) {
			this.pane=pane;
		}
		public class ComparatorTextField implements Comparator<TextField> {
		    @Override
		    public int compare(TextField o1, TextField o2) {
		        return o1.getText().compareTo(o2.getText());
		    }
		}
		public void hide() {
			this.getChildren().remove(scroll);
			pane.getChildren().remove(this);
		}
		public TextField result(int index) {
			return results.get(index);
		}
		public void find(Object c) {
			
		}
		public void show() {
			Bounds bounds = textField.localToScene(textField.getBoundsInLocal());
			this.setWidth(textField.getWidth());
			this.setHeight(textField.getHeight()*3);
			this.setTranslateX(bounds.getMinX());
			this.setTranslateY(bounds.getMinY()-textField.getHeight());
			VBox vBox= new VBox(5);
			results.forEach(r->vBox.getChildren().add(r));
			scroll.setContent(vBox);
			scroll.setPrefSize(textField.getWidth(), textField.getHeight()*3);
			scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
			this.getChildren().add(scroll);
			this.setManaged(false);
			pane.getChildren().add(this);
			results.forEach(r->{
				r.setEditable(false);
				r.setFocusTraversable(false);
				r.setOnMouseClicked(e->{
					if(e.getButton()==MouseButton.PRIMARY&&e.getClickCount()==1) {
						results.get(nrHover).setStyle("-fx-background-color: #FFFFFF;"
								+ "-fx-text-fill: #0076a3");
						r.setStyle("-fx-background-color: #FFFF22;"
								+ "-fx-text-fill: #9976a3");
						nrHover=results.indexOf(r);
						textField.setNrHover(nrHover);
					}else if(e.getButton()==MouseButton.PRIMARY&&e.getClickCount()==2) {
						textField.setText(r.getText()); 
						hide();
					}
					
				});
			});
			
		}
		
		public int size() {
			return results.size();
		}
	}
}
