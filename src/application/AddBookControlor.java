package application;

import java.awt.Window;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddBookControlor implements Initializable {
	@FXML
    private Button ajouter;

    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> temail;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TableColumn<Person, String> tnom;

    @FXML
    private TableColumn<Person, String> tprenom;
    private DataClass data = new DataClass();
    @FXML
    public void addColumn(ActionEvent event) {
    	
    	
    	if (tfnom.getText().isEmpty() && tfprenom.getText().isEmpty() && tfemail.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Form Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("entrez tous les champs.");
            alert.initOwner(owner);
            alert.show();
            return;
        if (!isEmailAdress(tfemail)) {
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Form Error!");
                alert1.setHeaderText(null);
                alert1.setContentText("Please enter a valid email address.");
                alert1.initOwner(owner);
                alert1.show();
                return;
            }
        }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tnom.setCellValueFactory(new PropertyValueFactory<Person,String>("nom"));
		tprenom.setCellValueFactory(new PropertyValueFactory<Person,String>("prenom"));
		temail.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));

	}
	public void remove(ActionEvent event)
	{
		Person selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
		
				}
	public void quitter(ActionEvent event){
		Platform.exit();
	}
	public void importbtn(ActionEvent event)
	{
		for (Person p :data.getImportList())
		{
			table.getItems().add(p);
			}
				
	}
	public void export(ActionEvent event)
	{
		data.setExportList(table.getItems());
	}
	public static boolean isEmailAdress(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$" );
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}
}
