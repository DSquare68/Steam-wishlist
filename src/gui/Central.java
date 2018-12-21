package gui;

import java.sql.Savepoint;
import java.util.ArrayList;

import Resources.ResourceLoader;
import data.Language;
import data.Window;
import game.GameTable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.FooterLogic;
import logic.MenuLogic;
import logic.TableLogic;
import logic.TopLogic;

public class Central extends BorderPane {

	public static TableView<GameTable> table = new TableView<GameTable>();
	HBox top = new HBox();
	HBox footer = new HBox();
	MenuBar menuBar = new MenuBar();
	
	public Central() {
		init();
		initMenu();
		initTop();
		initTable();
		initFooter();
		scaleAllNodes();
	}
	
	private void init() {
		this.setTop(new VBox(menuBar,top));
		this.setCenter(table);
		this.setBottom(footer);
	}
	private void initMenu() {
		menuBar.setPickOnBounds(true);
        Menu menuFile = new Menu(Language.texts.getString("wishlist"));
        Menu menuBase = new Menu(Language.texts.getString("dataBase"));
        Menu menuView = new Menu(Language.texts.getString("view"));
        Menu menuSettings = new Menu(Language.texts.getString("settings"));
        
        MenuItem changeUser = new MenuItem(Language.texts.getString("changeUser"));
        changeUser.setOnAction(e->MenuLogic.changeUser());
        MenuItem reload = new MenuItem(Language.texts.getString("reload"));
        reload.setOnAction(e->MenuLogic.reload());
        MenuItem home = new MenuItem(Language.texts.getString("home"));
        home.setOnAction(e->MenuLogic.home());
        menuFile.getItems().addAll(changeUser,reload,home);
        
        MenuItem reloadDB = new MenuItem(Language.texts.getString("reload"));
        reloadDB.setOnAction(e->MenuLogic.reload());
        MenuItem saveDiscount = new MenuItem(Language.texts.getString("saveDiscount"));
        saveDiscount.setOnAction(e->MenuLogic.saveDiscount(new ArrayList<GameTable>( table.getItems())));
        MenuItem saveState = new MenuItem(Language.texts.getString("saveState"));
        saveState.setOnAction(e->MenuLogic.saveState(new ArrayList<GameTable>( table.getItems())));
        menuBase.getItems().addAll(reloadDB,saveDiscount,saveState);
        
        menuBar.getMenus().addAll(menuFile, menuBase, menuView,menuSettings);

		
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
		String[] COLUMNS = GameTable.toTableNames();
		for(int i=0;i<COLUMNS.length;i++) {
			TableColumn<GameTable,DoubleProperty> columnDouble = new TableColumn<>(COLUMNS[i]);
			TableColumn<GameTable,IntegerProperty> columnInt = new TableColumn<>(COLUMNS[i]);
			TableColumn<GameTable,String> columnStr = new TableColumn<>(COLUMNS[i]);
			TableColumn<GameTable, BooleanProperty>columnBool =new TableColumn<>(COLUMNS[i]);
			switch(i) {
				case 0:
					columnInt.setSortType(TableColumn.SortType.ASCENDING);
					columnInt.setCellValueFactory(new PropertyValueFactory<GameTable,IntegerProperty>("hash"));
					columnInt.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnInt);
					break;
				case 1:
					columnStr.setCellValueFactory(new PropertyValueFactory<GameTable,String>("name"));
					table.getColumns().add(columnStr);
					break;
				case 2:
					columnDouble.setCellValueFactory(new PropertyValueFactory<GameTable,DoubleProperty>("price"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 3:
					columnDouble.setCellValueFactory(new PropertyValueFactory<GameTable,DoubleProperty>("originalPrice"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 4:
					columnStr.setCellValueFactory(new PropertyValueFactory<GameTable,String>("discountS"));
					columnStr.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnStr);
					break;
				case 5:
					columnStr.setCellValueFactory(new PropertyValueFactory<GameTable,String>("reliseDate"));
					table.getColumns().add(columnStr);
					break;
				case 6:
					columnDouble.setCellValueFactory(new PropertyValueFactory<GameTable,DoubleProperty>("wishNumber"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 7:
					columnDouble.setCellValueFactory(new PropertyValueFactory<GameTable,DoubleProperty>("rate"));
					columnDouble.setStyle("-fx-alignment: center ;");
					table.getColumns().add(columnDouble);
					break;
				case 8:
					columnStr.setCellValueFactory(new PropertyValueFactory<GameTable,String>("addDate"));
					table.getColumns().add(columnStr);
					break;
				case 9:
					columnStr.setCellValueFactory(new PropertyValueFactory<GameTable,String>("tagsS"));
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
		table.getSortOrder().add(table.getColumns().get(0));
	}
	@SuppressWarnings( "unchecked" )
	private void scaleTable() {
		table.setPrefWidth(9*Window.central.width/10);
		table.setMaxWidth(9*Window.central.width/10);
		table.setPrefHeight(7*Window.central.height/10);
		table.setTranslateX(0);
		table.setLayoutX(Window.central.width/20);
		final double columnWidth = Window.central.width/(80);
		table.getColumns().forEach(e->{
			TableColumn<GameTable,Object> columnO = (TableColumn<GameTable, Object>) e;
			columnO.setCellFactory(f->new TableCell<GameTable,Object>() {
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
				e.setPrefWidth(3*columnWidth);
				break;
			case 1:
				//title
				e.setPrefWidth(24*columnWidth);
				break;
			case 2:
				//price
				e.setPrefWidth(6*columnWidth);
				break;
			case 3:
				//original price
				e.setPrefWidth(6*columnWidth);
				break;
			case 4:
				//discount
				e.setPrefWidth(5*columnWidth);
				break;
			case 5:
				//released date
				e.setPrefWidth(7*columnWidth);
				break;
			case 6:
				//wish number
				e.setPrefWidth(6*columnWidth);
				break;
			case 7:
				//rate
				e.setPrefWidth(6*columnWidth);
				break;
			case 8:
				//add date
				e.setPrefWidth(7*columnWidth);
				break;
			case 9:
				//tags
				e.setPrefWidth(24*columnWidth);
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
