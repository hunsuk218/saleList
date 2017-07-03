package List.view;

import List.model.Sale;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaleEditDialogController {
	@FXML
	private TextField complexField;
	@FXML
	private TextField dongField;
	@FXML
	private TextField hoField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField phone2Field;
	@FXML
	private TextField priceField;
	@FXML
	private TextField rmksField;

	private Stage dialogStage;
	private Sale sale;
	private boolean okClicked = false;

	@FXML
	private void initialize(){
	}

	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	public void setSale(Sale sale){
		this.sale = sale;

		complexField.setText(sale.getcomplex());
		dongField.setText(sale.getDong());
		hoField.setText(sale.getHo());
		phoneField.setText(sale.getPhone());
		phone2Field.setText(sale.getPhone2());
		priceField.setText(sale.getPrice());
		rmksField.setText(sale.getRmks());
	}

	public boolean isOkClicked(){
		return okClicked;
	}

	@FXML
	private void handleOk(){
		//DB에다가 집어넣는 부분
		//DB UPDATE
	}
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (complexField.getText() == null || complexField.getText().length() == 0) {
			errorMessage += "No valid complex!\n";
		}
		if (dongField.getText() == null || dongField.getText().length() == 0) {
			errorMessage += "No valid dong!\n";
		}
		if (hoField.getText() == null || hoField.getText().length() == 0) {
			errorMessage += "No valid ho!\n";
		}

		if (phoneField.getText() == null || phoneField.getText().length() == 0) {
			errorMessage += "No valid phone!\n";
		}

		if (phone2Field.getText() == null || phone2Field.getText().length() == 0) {
			errorMessage += "No valid phone2!\n";
		}

		if (priceField.getText() == null || priceField.getText().length() == 0) {
			errorMessage += "No valid price!\n";
		}   
		if (rmksField.getText() == null || rmksField.getText().length() == 0) {
			errorMessage += "No valid rmks!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// 오류 메시지를 보여준다.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

}
