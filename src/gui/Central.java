package gui;

import Resources.ResourceLoader;
import data.Game;
import data.Language;
import data.Window;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.FooterLogic;
import logic.TableLogic;
import logic.TopLogic;

public class Central extends BorderPane {

	public static TableView<Game> table = new TableView<Game>();
	HBox top = new HBox();
	HBox footer = new HBox();
	
	public Central() {
		init();
		initTop();
		initTable();
		initFooter();
		scaleAllNodes();
	}
	private void init() {
		this.setTop(top);
		this.setCenter(table);
		this.setBottom(footer);
	}
	private void initTop() {
		top.setAlignment(Pos.CENTER_LEFT);
		
		String[] labelsName= {Language.texts.getString("home"),Language.texts.getString("quickshop"),Language.texts.getString("picking"),Language.texts.getString("advanceShop"),Language.texts.getString("filtr"),Language.texts.getString("search")};
		String[] idNames = {"home", "quickShop", "advanceShop","picking", "filtr","search" };
		for(int i=0;i<4;i++) {
			Button home = new Button();
			home.setId(idNames[i]);
			home.setTooltip(new Tooltip(labelsName[i]));
			home.setGraphic(new ImageView(ResourceLoader.getImage(idNames[i]+".png")));
			home.setStyle("-fx-background-color: transparent;");
			top.getChildren().add(home);
		}
		TopLogic.setTopButtonsStyle(top, 0, 4);
		TopLogic.setTopButtonsAction(top,0,4);
		
		Button buttonFiltr = new Button(labelsName[4]);
		buttonFiltr.setId(idNames[4]);
		top.getChildren().add(buttonFiltr);
		TopLogic.setFiltrAction(buttonFiltr);
		
		
		TextField textField = new TextField();
		textField.setPromptText(labelsName[5]);
		textField.setId(idNames[5]);
		top.getChildren().add(textField);
		scaleTop();
		
	}
	private void scaleTop() {
		top.setSpacing(Window.central.width/120);
		top.setTranslateX(Window.central.width/40);
		top.setPadding(new Insets(Window.central.height/70, 0, Window.central.height/70, 0));
		for(int i=0;i<4;i++) {
			Button button =(Button) top.getChildren().get(i);
			button.setPrefWidth(Window.central.height/6);
			button.setPrefHeight(Window.central.height/6);
			button.setFont(Font.font ("Verdana", Window.central.height/40));
			ImageView imageView =(ImageView) button.getGraphic();
			imageView.setFitHeight(Window.central.height/6);
			imageView.setFitWidth(Window.central.height/6);
			button.setGraphic(imageView);
		}
		Button radioButton =(Button) top.getChildren().get(4);
		radioButton.setPrefWidth(Window.central.width/6);
		radioButton.setPrefHeight(Window.central.height/10);
		radioButton.setFont(Font.font ("Verdana", Window.central.height/20));
		
		TextField textField =(TextField) top.getChildren().get(5);
		textField.setPrefWidth(Window.central.width/8);
		textField.setPrefHeight(Window.central.height/15);
		textField.setFont(Font.font ("Verdana", Window.central.height/30));

	}
	private void initTable() {
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
		FooterLogic.setFooterStyle(footer);
		FooterLogic.setFooterAction(footer);
		scaleFooter();
		
		
	}
	
	public void scaleAllNodes() {
		scaleTop();
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
		footer.setPrefHeight(Window.central.height/45);
		footer.setPrefWidth(Window.central.width);
		footer.setPadding(new Insets(Window.central.height/80, 0, Window.central.height/80, 0));
		footer.getChildren().forEach(e->{
			((Label) e).setPrefHeight(Window.central.height/45);
			((Label) e).setPrefWidth(Window.central.width/12);
			((Label) e).setFont(new Font("ARIAL",Window.central.height/45));
		});
		
	}
}
