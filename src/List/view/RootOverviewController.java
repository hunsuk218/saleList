package List.view;

import List.MainApp;
import javafx.fxml.FXML;

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
