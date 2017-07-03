package List.view;

import javafx.fxml.FXML;
import List.MainApp;
import List.model.Sale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOverviewController {
	@FXML
	private TableView<Sale> saleTable;
	@FXML
	private TableColumn<Sale, String> complexColumn;
	@FXML
	private TableColumn<Sale, String> dongColumn;
	@FXML
	private TableColumn<Sale, String> hoColumn;
	
	@FXML
	private Label complexLabel;
	@FXML
	private Label dongLabel;
	@FXML
	private Label hoLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label phone2Label;
	@FXML
	private Label rmksLabel;
	
	private MainApp mainApp;
	
	public ListOverviewController(){
	}
	
	@FXML
	private void handleDeleteSale() {
	    int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
	    saleTable.getItems().remove(selectedIndex);
	}
	
	@FXML
	private void initialize(){
		complexColumn.setCellValueFactory(cellData -> cellData.getValue().complexProperty());
		dongColumn.setCellValueFactory(cellData -> cellData.getValue().dongProperty());
		hoColumn.setCellValueFactory(cellData -> cellData.getValue().hoProperty());
		
		showSaleDetails(null);
		
		saleTable.getSelectionModel().selectedItemProperty().addListener(
				(observable,oldValue,newValue) -> showSaleDetails(newValue));
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
		saleTable.setItems(mainApp.getSaleData());
	}
	
	
	@FXML
	private void handleNewSale() {
	    Sale tempSale = new Sale();
	    boolean okClicked = mainApp.showSaleEditDialog(tempSale);
	    if (okClicked) {
	        mainApp.getSaleData().add(tempSale);
	    }
	}
	@FXML
	private void handleEditSale() {
	    Sale selectedSale = saleTable.getSelectionModel().getSelectedItem();
	    if (selectedSale != null) {
	        boolean okClicked = mainApp.showSaleEditDialog(selectedSale);
	        if (okClicked) {
	            showSaleDetails(selectedSale);
	        }

	    } else {
	        // 아무것도 선택하지 않았다.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
	
	private void showSaleDetails(Sale sale){
		if(sale != null){
			complexLabel.setText(sale.getcomplex());
			priceLabel.setText(sale.getPrice());
			dongLabel.setText(sale.getDong());
			hoLabel.setText(sale.getHo());
			phoneLabel.setText(sale.getPhone());;
			phone2Label.setText(sale.getPhone2());;
			rmksLabel.setText(sale.getRmks());;
		}else{
			complexLabel.setText("");
			priceLabel.setText("");
			dongLabel.setText("");
			hoLabel.setText("");
			phoneLabel.setText("");
			phone2Label.setText("");
			rmksLabel.setText("");
		}
	}

}
