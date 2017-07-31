package List.view;

import List.MainApp;
import List.model.SaleItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RootOverviewController {
	private MainApp mainApp;
	
	public RootOverviewController() {};
	
	@FXML
	private void initialize(){}
	
	public void handleFontControllBtn() {
		mainApp.showFontDlg();
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
	}
}
