package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationGenderController implements Initializable {


    @FXML private ToggleGroup genderChoiceGroup = new ToggleGroup();

    @FXML private ToggleButton maleChoiceButton;

    @FXML private ToggleButton femaleChoiceButton;


    @FXML private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maleChoiceButton.setToggleGroup(genderChoiceGroup);

        femaleChoiceButton.setToggleGroup(genderChoiceGroup);

        this.nextButton.setDisable(true);


    }

    public void genderButtonPushed (ActionEvent event) {
        if (maleChoiceButton.isSelected() || femaleChoiceButton.isSelected()) {
            this.nextButton.setDisable(false);
        }
        else {
            this.nextButton.setDisable(true);
        }
    }

    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {
        //Save character race
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        if (maleChoiceButton.isSelected()) {
            playerCharacter.setGender("Mężczyzna");
        }
        if (femaleChoiceButton.isSelected()) {
            playerCharacter.setGender("Kobieta");
        }

        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);
        characterManager.save(playerCharacterList, "save.txt");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationClass.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

}
