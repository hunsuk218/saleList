package List;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import List.model.SaleItem;
import List.view.FontDlgController;
import List.view.ListOverviewController;
import List.view.SaleEditDialogController;
import List.view.RootOverviewController;
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
	private ObservableList<SaleItem> SaleData = FXCollections.observableArrayList();


	public MainApp(){
		try{
			// Initialize the app with a service account, granting admin privileges
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setDatabaseUrl("https://salelist-474d5.firebaseio.com")
					.setServiceAccount(new FileInputStream("salelist-474d5-firebase-adminsdk-07zy9-cef1007019.json"))
					.build();
			FirebaseApp.initializeApp(options);
		}catch(IOException e) {
			System.out.println(e);
		}

		DatabaseReference ref = FirebaseDatabase
				.getInstance()
				.getReference("sale-list");
		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				SaleItem saleItem = dataSnapshot.getValue(SaleItem.class);
				saleItem.setKey(dataSnapshot.getKey());
				SaleData.add(saleItem);
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				SaleItem saleItem = dataSnapshot.getValue(SaleItem.class);
				String key = dataSnapshot.getKey();
				synchronized(SaleData) {
					for(Iterator<SaleItem> it = SaleData.iterator(); it.hasNext();) {
						String value = it.next().getKey();
						if(value.equals(key)) {
							it.remove();
						}
					}
				}
				saleItem.setKey(dataSnapshot.getKey());
				SaleData.add(saleItem);
			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				String key = dataSnapshot.getKey();
				synchronized(SaleData) {
					for(Iterator<SaleItem> it = SaleData.iterator(); it.hasNext();) {
						String value = it.next().getKey();
						if(value.equals(key)) {
							it.remove();
						}
					}
				}
			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

			@Override
			public void onCancelled(DatabaseError databaseError) {}
		});
	}

	public ObservableList<SaleItem> getSaleData(){
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
			loader.setLocation(getClass().getResource("/src/List/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootOverviewController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void showListOverview(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/src/List/view/ListOverview.fxml"));
			AnchorPane ListOverview = (AnchorPane)loader.load();

			rootLayout.setCenter(ListOverview);

			ListOverviewController controller = loader.getController();
			controller.setMainApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public boolean showSaleEditDialog(SaleItem sale){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/src/List/view/SaleEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
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
	
	public void showSaleNewDialog(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/src/List/view/SaleEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SaleEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setIsNew();
			dialogStage.showAndWait();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showFontDlg() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/src/List/view/FontDlg.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			FontDlgController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

		}catch(IOException e){
			e.printStackTrace();
		}

	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
