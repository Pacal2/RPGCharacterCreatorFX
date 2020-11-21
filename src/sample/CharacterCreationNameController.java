package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationNameController implements Initializable {



    @FXML private TextArea nameTextArea;


    @FXML private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.nextButton.setDisable(false);


    }


    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {
        //Save character race
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);


        playerCharacter.setName(nameTextArea.getText());

        // Set boolean as finished, so that program won't delete unfinished characters
        playerCharacter.setFinished(true);
        // finished

        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);
        characterManager.save(playerCharacterList, "save.txt");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationEquipment.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

}
