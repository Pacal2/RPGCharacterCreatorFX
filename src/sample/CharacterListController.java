package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterListController implements Initializable {

    @FXML private TableView<PlayerCharacter> tableView;
    @FXML private TableColumn<PlayerCharacter, String> raceColumn;
    @FXML private TableColumn<PlayerCharacter, String> professionColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Column set up
        raceColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, String>("Race"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, String>("Profession"));

        //Data loading

        try {
            tableView.setItems(getCharacters());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public ObservableList<PlayerCharacter> getCharacters() throws IOException, ClassNotFoundException {
        ObservableList characters = FXCollections.observableArrayList();
        characters.add(new CharacterManager().load("save.txt").get(0));
        return characters;
    }


}
