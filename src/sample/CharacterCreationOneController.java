package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationOneController implements Initializable {

    @FXML private ComboBox raceComboBox;
    @FXML private ImageView raceImage;
    @FXML private TextArea raceDescription;
    @FXML private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        raceComboBox.getItems().addAll("Człowiek", "Przybysz", "Mutant", "Android");
        this.raceDescription.setText("Wybierz rasę powyżej");
        this.nextButton.setDisable(true);
    }

    //Scene Changer
    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {

        //Save character race

        PlayerCharacter playerCharacter = new PlayerCharacter();
        String chosenRace = raceComboBox.getValue().toString();

        playerCharacter.setRace(chosenRace);
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> tempCharacterList = new ArrayList<>();

        File tmpDir = new File("save.txt");
        boolean exists = tmpDir.exists();
        if (exists) {
            tempCharacterList = characterManager.load("save.txt");

        }
        tempCharacterList.add(playerCharacter);
        characterManager.save(tempCharacterList, "save.txt");


        //characterManager.saveCharacterList(playerCharacter);


        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationTwo.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void setRaceChoiceBoxPicked() {
        String raceChoice = raceComboBox.getValue().toString();
        this.nextButton.setDisable(false);
        if (raceChoice == "Człowiek") {
            this.raceDescription.setText(
                    "Najbardziej dominująca rasa pustkowii jak i oryginalnie jedyni inteligentni mieszkańcy ziemii. \n\nAtuty: \n+ do charyzmy"
            );
            raceImage.setImage(new Image("Human.jpg"));
        } else if (raceChoice == "Przybysz") {
            this.raceDescription.setText(
                    "Istoty, które przybyły wraz z obcymi, nikt nie wie czy to jedna ze zniewolonych przez nich ras czy zostali przez nich stworzeni. \n\nAtuty: \n+ do wytrzymałości)"
            );
            raceImage.setImage(new Image("Alien.jpg"));
        } else if (raceChoice == "Mutant") {
            this.raceDescription.setText(
                    "Ewoluowali od ludzi w trakcie wielkiej wojny. Większość z nich zmarła w trakcie przemiany, lecz najsilniejsi przetrwali. \n\nAtuty: \n+ do siły"
            );
            raceImage.setImage(new Image("Mutant.jpg"));
        } else if (raceChoice == "Android") {
            this.raceDescription.setText(
                    "Konstrukty stworzone do walki z obcymi najeźdźcami w trakcie wielkiej wojny przez ludzi. \nWkrótce jednak zyskali świadomość i się przeciwko nim odwrócili. \n\nAtuty: \n+ do intelektu"
            );
            raceImage.setImage(new Image("Robot.jpg"));
        } else {
            this.raceDescription.setText(
                    "Wybierz rasę powyżej"
            );
        }
    }

}