package List.view;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import List.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FontDlgController {
	
	@FXML
	private TextField fontSizeField;
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private Label checkLbl;
	
	private Stage dialogStage;
	
	private MainApp mainapp;
	
	@FXML
	private void initialize(){
		fontSizeField.setText(Double.toString(fontSizeField.getFont().getSize()));
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void btnOk() throws IOException {
		try {
		BufferedWriter out2 = new BufferedWriter(new FileWriter("target\\Font-Type.CSS"));
		
		String txt = ".label {\r\n" + 
				"    -fx-font-size: "+ fontSizeField.getText() +"pt;\r\n" + 
				"    -fx-font-family: \"Segoe UI Semibold\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".text-field {\r\n" + 
				"    -fx-font-size: "+ fontSizeField.getText() +"pt;\r\n" + 
				"    -fx-font-family: \"Segoe UI Semibold\";\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".text-area {\r\n" + 
				"    -fx-font-size: "+ fontSizeField.getText() +"pt;\r\n" + 
				"    -fx-font-family: \"Segoe UI Semibold\";\r\n" + 
				"}";
		
		
		out2.write(txt);
		out2.flush();
		out2.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		dialogStage.close();
	};
	
	public void btnCancel() {
		
		dialogStage.close();
	};
	
	

}
