package nrcCourtSheets;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class NRCMain extends Application
{

	private Stage window;
	private final double MIN_WINDOW_WIDTH = 1014.0;
	private final double MIN_WINDOW_HEIGHT = 600;
	
	final private String mainLayoutFileName = "MainLayout.fxml";
	final private String editCourtFileName = "EditCourt.fxml";
	
	Stage popUpWindow;
	
	public static void main(String[] args)
	{
		launch(NRCMain.class, (java.lang.String[])null);
	}
	
	public void start(Stage stage)
	{
		try
		{
			window = stage;
			window.setMinHeight(MIN_WINDOW_HEIGHT);
			window.setMinWidth(MIN_WINDOW_WIDTH);
			window.setTitle("NRC Court Sheets");
			
			gotoCourtSheet(mainLayoutFileName);
			
			window.show();
		}
		catch(Exception ex)
		{
			System.out.println("FXML loader failed.");
			ex.printStackTrace();
		}
	}
	
	public void gotoCourtSheet(String fxml) throws IOException
	{
		try
		{
			MainLayoutController courtSheetController = (MainLayoutController) replaceSceneContent(mainLayoutFileName);
			courtSheetController.setApp(this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void gotoEditCourt(CourtBooking booking, MainLayoutController mlc) throws IOException
	{

		try
		{
			EditCourtController editCourtController = (EditCourtController) gotoNewWindow(editCourtFileName);
			editCourtController.setApp(this);
			editCourtController.init(booking);
			editCourtController.mlc = mlc;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Initializable gotoNewWindow(String fxml/*, SavedValues values*/) throws Exception
	{
		
		FXMLLoader loader = new FXMLLoader();
		InputStream in = NRCMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(NRCMain.class.getResource(fxml));
		VBox page;
		try
		{
			page = (VBox) loader.load(in);
		}
		finally
		{
//			in.close();
		}
		
		popUpWindow = new Stage();
		Scene editCourtScene = new Scene(page);
		popUpWindow.setScene(editCourtScene);
		popUpWindow.show();
		popUpWindow.sizeToScene();
		return (Initializable) loader.getController();
	}
	
	private Initializable replaceSceneContent(String fxml) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		InputStream in = NRCMain.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(NRCMain.class.getResource(fxml));
		VBox page;
		try
		{
			page = (VBox) loader.load(in);
		}
		finally
		{
//			in.close();
		}
		Scene scene = new Scene(page);
		window.setScene(scene);
		window.sizeToScene();
		return (Initializable) loader.getController();
	}

	public void closePopUpWindow()
	{
		popUpWindow.close();
	}
	
}
