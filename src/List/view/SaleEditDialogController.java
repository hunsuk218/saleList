package List.view;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import List.model.SaleItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaleEditDialogController {
	@FXML
	private TextField ComplexField;
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
	@FXML
	private ComboBox combo_Complex;

	private Stage dialogStage;
	private SaleItem sale;
	private boolean okClicked = false;
	private boolean isNew = false;
	private boolean isETC = false;
	private ObservableList<String> list = FXCollections.observableArrayList("포스홈타운", "동아솔레시티", "자이2차", "상록데시앙",
			"한화 꿈에그린", "기타");

	@FXML
	private void initialize() {
		combo_Complex.setItems(list);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setIsNew() {
		this.isNew = true;
	}

	public void setSale(SaleItem sale) {
		this.sale = sale;

		if (list.contains(sale.getcomplex())) {
			combo_Complex.setValue(sale.getcomplex());
		} else {
			combo_Complex.setValue("기타");
			ComplexField.setText(sale.getcomplex());
			combo_Complex.setEditable(true);
			isETC = true;
		}
		dongField.setText(sale.getDong());
		hoField.setText(sale.getHo());
		phoneMaleField.setText(sale.getPhoneMale());
		phoneFemaleField.setText(sale.getPhoneFemale());
		phone2MaleField.setText(sale.getPhone2Male());
		phone2FemaleField.setText(sale.getPhone2Female());
		priceField.setText(sale.getPrice());
		rmksField.setText(sale.getRmks());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		// DB
		// DB UPDATE
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
		SaleItem sale_new;
		if (isETC) {
			sale_new = new SaleItem(ComplexField.getText(), dongField.getText(), hoField.getText(),
					phoneMaleField.getText(), phoneFemaleField.getText(), phone2MaleField.getText(),
					phone2FemaleField.getText(), priceField.getText(), rmksField.getText());
		} else {
			sale_new = new SaleItem(combo_Complex.getValue().toString(), dongField.getText(), hoField.getText(),
					phoneMaleField.getText(), phoneFemaleField.getText(), phone2MaleField.getText(),
					phone2FemaleField.getText(), priceField.getText(), rmksField.getText());
		}
		Map<String, Object> saleItemValues = sale_new.toMap();
		if (isNew) {
			ref.child("sale-list").push().setValue(saleItemValues);
		} else {
			String key = sale.getKey();
			Map<String, Object> childUpdates = new HashMap<>();
			childUpdates.put("/sale-list/" + key, saleItemValues);
			System.out.println("check" + saleItemValues);
			ref.updateChildren(childUpdates);
		}
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public void comboChanged(ActionEvent event) {
		if (combo_Complex.getValue().toString().equals("기타")) {
			ComplexField.setEditable(true);
			isETC = true;
		} else {
			ComplexField.setEditable(false);
		}
	}

}
