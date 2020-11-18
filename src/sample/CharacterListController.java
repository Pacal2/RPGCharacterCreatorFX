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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class CharacterListController implements Initializable {

    @FXML private TableView<PlayerCharacter> tableView;
    @FXML private TableColumn<PlayerCharacter, Integer> IDColumn;
    @FXML private TableColumn<PlayerCharacter, Boolean> finishedColumn;
    @FXML private TableColumn<PlayerCharacter, String> raceColumn;
    @FXML private TableColumn<PlayerCharacter, String> genderColumn;
    @FXML private TableColumn<PlayerCharacter, String> professionColumn;
    @FXML private TableColumn<PlayerCharacter, Map<String, Integer>> strengthColumn;
    @FXML private TableColumn<PlayerCharacter, Map<String, Integer>> skillColumn;
    @FXML private TableColumn<PlayerCharacter, Map<String, Integer>> equipmentColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Column set up
        IDColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, Integer>("ID"));
        finishedColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, Boolean>("Finished"));
        raceColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, String>("Race"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, String>("Gender"));
        professionColumn.setCellValueFactory(new PropertyValueFactory<PlayerCharacter, String>("Profession"));
        strengthColumn.setCellValueFactory(new PropertyValueFactory<>("Stats"));
        skillColumn.setCellValueFactory(new PropertyValueFactory<>("Skills"));
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("Equipment"));
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public ObservableList<PlayerCharacter> getCharacters() throws IOException, ClassNotFoundException {
        ObservableList characters = FXCollections.observableArrayList();

        ArrayList<PlayerCharacter> playerCharacterList = new CharacterManager().load("save.txt");
        CharacterManager.cleanUp(playerCharacterList);

        characters.addAll(
                playerCharacterList
        );
        return characters;
    }

    public void nextButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void deleteButtonPushed() throws IOException, ClassNotFoundException {
        ObservableList<PlayerCharacter> selectedRows, allCharacters;
        allCharacters = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        // Nadpisanie pliku
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> tempCharacterList = new ArrayList<>();

        File tmpDir = new File("save.txt");
        boolean exists = tmpDir.exists();
        if (exists) {
            tempCharacterList = characterManager.load("save.txt");

        }
        for (PlayerCharacter playerCharacter: selectedRows) {
            for (int i=0; i <tempCharacterList.size(); i++) {
                if (playerCharacter.getID() == tempCharacterList.get(i).getID()) {
                    tempCharacterList.remove(i);
                }
            }
            allCharacters.remove(playerCharacter);

        }
        characterManager.save(tempCharacterList, "save.txt");
    }


}
