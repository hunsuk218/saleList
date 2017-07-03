package List;

import java.io.IOException;

import List.model.Sale;
import List.view.ListOverviewController;
import List.view.SaleEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Sale> SaleData = FXCollections.observableArrayList();


	public MainApp(){
		SaleData.add(new Sale("Complex", "101","101"));
		SaleData.add(new Sale("Complex", "101","101"));
	}

	public ObservableList<Sale> getSaleData(){
		return SaleData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("List");
		initRootLayout();
		showListOverview();
	}

	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showListOverview(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ListOverview.fxml"));
			AnchorPane ListOverview = (AnchorPane)loader.load();

			rootLayout.setCenter(ListOverview);

			ListOverviewController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public boolean showSaleEditDialog(Sale sale){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SaleEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Sale");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SaleEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setSale(sale);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
