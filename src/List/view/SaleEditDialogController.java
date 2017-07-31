package List.view;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import List.model.SaleItem;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
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
	private TextField phoneMaleField;
	@FXML
	private TextField phoneFemaleField;
	@FXML
	private TextField phone2MaleField;
	@FXML
	private TextField phone2FemaleField;
	@FXML
	private TextField priceField;
	@FXML
	private TextArea rmksField;

	private Stage dialogStage;
	private SaleItem sale;
	private boolean okClicked = false;
	private boolean isNew = false;

	@FXML
	private void initialize(){
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setIsNew() {
		this.isNew = true;
	}
	public void setSale(SaleItem sale){
		this.sale = sale;

		complexField.setText(sale.getcomplex());
		dongField.setText(sale.getDong());
		hoField.setText(sale.getHo());
		phoneMaleField.setText(sale.getPhoneMale());
		phoneFemaleField.setText(sale.getPhoneFemale());
		phone2MaleField.setText(sale.getPhone2Male());
		phone2FemaleField.setText(sale.getPhone2Female());
		priceField.setText(sale.getPrice());
		rmksField.setText(sale.getRmks());
	}

	public boolean isOkClicked(){
		return okClicked;
	}

	@FXML
	private void handleOk(){
		//DB
		//DB UPDATE
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
		SaleItem sale_new = new SaleItem(complexField.getText(),dongField.getText(),hoField.getText(),phoneMaleField.getText(),
				phoneFemaleField.getText(),phone2MaleField.getText(),phone2FemaleField.getText(),priceField.getText(),rmksField.getText());
		Map<String, Object> saleItemValues = sale_new.toMap();
		if(isNew) {
			ref.child("sale-list").push().setValue(saleItemValues);
		}
		else {
		String key = sale.getKey();
		Map<String, Object> childUpdates = new HashMap<>();
		childUpdates.put("/sale-list/" + key ,saleItemValues);
		System.out.println("check" + saleItemValues);
		ref.updateChildren(childUpdates);
		}
		dialogStage.close();
	}
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
