package List.view;

import javafx.fxml.FXML;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import List.MainApp;
import List.model.SaleItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListOverviewController {
	@FXML
	private TableView<SaleItem> saleTable;
	@FXML
	private TableColumn<SaleItem, String> complexColumn;
	@FXML
	private TableColumn<SaleItem, String> dongColumn;
	@FXML
	private TableColumn<SaleItem, String> hoColumn;
	
	@FXML
	private Label complexLabel;
	@FXML
	private Label dongLabel;
	@FXML
	private Label hoLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label phoneMaleLabel;
	@FXML
	private Label phoneFemaleLabel;
	@FXML
	private Label phone2MaleLabel;
	@FXML
	private Label phone2FemaleLabel;
	@FXML
	private Label rmksLabel;
	
	private MainApp mainApp;
	
	public ListOverviewController(){
	}
	
	@FXML
	private void handleDeleteSale() {
	    SaleItem selectedSale = saleTable.getSelectionModel().getSelectedItem();
	    String key = selectedSale.getKey();
	    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("/sale-list/" + key);
		ref.removeValue();
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
	   mainApp.showSaleNewDialog();
	}
	@FXML
	private void handleEditSale() {
	    SaleItem selectedSale = saleTable.getSelectionModel().getSelectedItem();
	    if (selectedSale != null) {
	        boolean okClicked = mainApp.showSaleEditDialog(selectedSale);
	        if (okClicked) {
	            showSaleDetails(selectedSale);
	        }

	    } else {
	        // �ƹ��͵� �������� �ʾҴ�.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
	
	private void showSaleDetails(SaleItem sale){
		if(sale != null){
			complexLabel.setText(sale.getcomplex());
			priceLabel.setText(sale.getPrice());
			dongLabel.setText(sale.getDong());
			hoLabel.setText(sale.getHo());
			phoneMaleLabel.setText(sale.getPhoneMale());;
			phoneFemaleLabel.setText(sale.getPhoneFemale());;
			phone2MaleLabel.setText(sale.getPhone2Male());;
			phone2FemaleLabel.setText(sale.getPhone2Female());;
			rmksLabel.setText(sale.getRmks());;
		}else{
			complexLabel.setText("");
			priceLabel.setText("");
			dongLabel.setText("");
			hoLabel.setText("");
			phoneMaleLabel.setText("");
			phoneFemaleLabel.setText("");
			phone2MaleLabel.setText("");
			phone2FemaleLabel.setText("");
			rmksLabel.setText("");
		}
	}

}
