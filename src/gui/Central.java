package gui;

import data.Game;
import data.Language;
import data.Window;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.TableLogic;

public class Central extends BorderPane {

	public static TableView<Game> table = new TableView<Game>();
	HBox footer = new HBox();
	
	public Central() {
		init();
		initTop();
		initTable();
		initFooter();
		scaleAllNodes();
	}
	private void init() {
		
	}
	private void initTop() {
		// TODO Auto-generated method stub
		
	}
	private void initTable() {
		this.setCenter(table);
		table.setEditable(true);
		String[] COLUMNS = Game.toTableNames();
		for(int i=0;i<COLUMNS.length;i++) {
			TableColumn<Game,DoubleProperty> columnDouble = new TableColumn<>(COLUMNS[i]);
			TableColumn<Game,IntegerProperty> columnInt = new TableColumn<>(COLUMNS[i]);
			TableColumn<Game,String> columnStr = new TableColumn<>(COLUMNS[i]);
			TableColumn<Game, BooleanProperty>columnBool =new TableColumn<>(COLUMNS[i]);
			switch(i) {
				case 0:
					columnInt.setCellValueFactory(new PropertyValueFactory<Game,IntegerProperty>("hash"));
					columnInt.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnInt);
					break;
				case 1:
					columnStr.setCellValueFactory(new PropertyValueFactory<Game,String>("name"));
					table.getColumns().add(columnStr);
					break;
				case 2:
					columnDouble.setCellValueFactory(new PropertyValueFactory<Game,DoubleProperty>("price"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 3:
					columnDouble.setCellValueFactory(new PropertyValueFactory<Game,DoubleProperty>("originalPrice"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 4:
					columnStr.setCellValueFactory(new PropertyValueFactory<Game,String>("discountS"));
					columnStr.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnStr);
					break;
				case 5:
					columnStr.setCellValueFactory(new PropertyValueFactory<Game,String>("reliseDate"));
					table.getColumns().add(columnStr);
					break;
				case 6:
					columnDouble.setCellValueFactory(new PropertyValueFactory<Game,DoubleProperty>("wishNumber"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 7:
					columnDouble.setCellValueFactory(new PropertyValueFactory<Game,DoubleProperty>("rate"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 8:
					columnStr.setCellValueFactory(new PropertyValueFactory<Game,String>("tagsS"));
					table.getColumns().add(columnStr);
					break;
				case 9:
					columnStr.setCellValueFactory(new PropertyValueFactory<Game,String>("addDate"));
					table.getColumns().add(columnStr);
					break;
					default:
						System.out.println("Error in columns number");
			}
		}
		//table.setOnMouseClicked(e-> initRightSideAdd(MODE_LEFT[1]));
		table.setOnKeyPressed(e->{if(e.getCode()==KeyCode.SHIFT)table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);});
		//table.setOnKeyReleased(e->{if(e.getCode()==KeyCode.SHIFT) table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);});
		TableLogic.addGameToTable();
		//table.setId(TABLE_ID);
		//table.getStyleClass().add(TABLE_ID);
		/*table.setRowFactory(new Callback<TableView<Anime>, TableRow<Anime>>() {
			
			@Override
			public TableRow<Anime> call(TableView<Anime> param) {
				final TableRow<Anime> row = new TableRow<Anime>() {
	                @Override
	                protected void updateItem(Anime row, boolean empty) {
	                    super.updateItem(row, empty);
	                    
	                    if (this.getIndex()%2==1)
	                        getStyleClass().add(ODD_ROWS);
	                    else
	                    	getStyleClass().add(EVEN_ROWS);
	      	                }
	            };
	            return row;
			}
		});*/	
		scaleTable();
	}
	@SuppressWarnings( "unchecked" )
	private void scaleTable() {
		table.setPrefWidth(9*Window.central.width/10);
		table.setMaxWidth(9*Window.central.width/10);
		table.setPrefHeight(7*Window.central.height/10);
		table.setTranslateX(0);
		table.setLayoutX(Window.central.width/20);
		
		table.getColumns().forEach(e->{
			TableColumn<Game,Object> columnO = (TableColumn<Game, Object>) e;
			columnO.setCellFactory(f->new TableCell<Game,Object>() {
				@Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);                   
                    setHeight(Window.central.height/25);
                    if(isEmpty())
                    {
                        setText("");
                    }
                    else
                    {
                        setFont(Font.font ("Verdana", Window.central.height/50));
                        setText(item.toString());
                    }
                }
			});
			switch(table.getColumns().indexOf(e)) {
			case 0:
				//hash
				e.setPrefWidth(Window.central.width/40);
				break;
			case 1:
				//title
				e.setPrefWidth(3*Window.central.width/11);
				break;
			case 2:
				//price
				e.setPrefWidth(Window.central.width/25);
				break;
			case 3:
				//original price
				e.setPrefWidth(Window.central.width/25);
				break;
			case 4:
				//discount
				e.setPrefWidth(Window.central.width/25);
				break;
			case 5:
				//released date
				e.setPrefWidth(Window.central.width/15);
				break;
			case 6:
				//wish number
				e.setPrefWidth(Window.central.width/40);
				break;
			case 7:
				//rate
				e.setPrefWidth(Window.central.width/30);
				break;
			case 8:
				//tags
				e.setPrefWidth(2*Window.central.width/7);
				break;
			case 9:
				//add date
				e.setPrefWidth(Window.central.width/15);
				break;
				default:
					System.out.println("Error in columns number");
			}
		});
		
	}
	private void initFooter() {
		this.setBottom(footer);
		String[] labelsStrings = new String[3];
		labelsStrings[0] = Language.texts.getString("info");
		labelsStrings[1] = Language.texts.getString("author");
		labelsStrings[2] = Language.texts.getString("settings");
		for(int i=0;i<labelsStrings.length;i++) {
			Label label = new Label(labelsStrings[i]);
			label.setTextFill( new Color(0.3,0.3,0.8, 1.0));
			label.setAlignment(Pos.CENTER);
			footer.getChildren().add(label);
		}
		footer.getChildren().forEach(e->{
			e.setOnMouseEntered(f->((Label) e).setTextFill(new Color(0.9, 0.3,0.3,1.0)));
			e.setOnMouseExited(f->((Label) e).setTextFill(new Color(0.3,0.3,0.8, 1.0)));
			switch (this.getChildren().indexOf(e)) {
			case 0:
				//TODO run info
				break;
			case 1:
				//TODO run author
				break;
			case 2:
				//TODO run settings
				break;
				default: System.out.println("No such label in footer."); break;
			}
		});
		scaleFooter();
		
		
	}
	
	public void scaleAllNodes() {
		if(this.getScene()!=null)scaleCentral();
		scaleFooter();
		scaleTable();
	}
	private void scaleCentral() {
		this.getScene().getWindow().setWidth(Window.central.width);
		this.getScene().getWindow().setHeight(Window.central.height);
		this.getScene().getWindow().centerOnScreen();
	}
	private void scaleFooter() {
		footer.setPrefHeight(Window.central.height/25);
		footer.setPrefWidth(Window.central.width);
		footer.getChildren().forEach(e->{
			((Label) e).setPrefHeight(Window.central.height/35);
			((Label) e).setPrefWidth(Window.central.width/8);
			((Label) e).setFont(new Font("ARIAL",Window.central.height/35));
		});
		
	}
}
