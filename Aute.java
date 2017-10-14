import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.geometry.Insets;
import javafx.scene.Node;

/**Author: Mario Rodriguez
 * Username and Password Authenticator
 * */

public class Aute extends Application{
	@Override
	public void start (Stage primaryStage){
	
		//Loop( proram will continue until user name and password are entered correctly.)
	
		while(true){
		String name = "Mario";
		String pass = "Rodriguez";

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Authenticator");
		dialog.setHeaderText("Enter Username and Password");

		ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
		

		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(40);
		grid.setPadding(new Insets(20,150,10,10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username: "), 0,0);
		grid.add(username, 1,0);
		grid.add(new Label("Password: "), 0,1);
		grid.add(password, 1,1);

		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		username.textProperty().addListener((observable, oldValue, newValue)->{
		loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);
		Platform.runLater(()-> username.requestFocus());
		dialog.setResultConverter(dialogButton -> {

			if(dialogButton == loginButtonType){

			return new Pair<>(username.getText(), password.getText());
			
			}else{
			System.exit(0);
			return null;

			}

		});
		dialog.showAndWait();

		//welcome message if correct , error message if incorrect.
		if(username.getText().equals(name)&&password.getText().equals(pass)){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText(null);
			alert.setContentText("Welcome " + username.getText() + "!");
			alert.showAndWait();
			System.exit(0);
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("INFORMATION");
			alert.setHeaderText(null);
			alert.setContentText("Wrong Username or Password!");
			alert.showAndWait();
		}

		}

	
	}
}
