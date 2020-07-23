package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationClassController implements Initializable {

    @FXML private ComboBox classComboBox;
    @FXML private ImageView classImage;
    @FXML private TextArea classDescription;
    @FXML private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        classComboBox.getItems().addAll("Pięść", "Oczko", "Mózg", "Rączka", "Cień", "Buźka");
        this.classDescription.setText("Wybierz klasę powyżej");
        this.nextButton.setDisable(true);
    }

    //Scene Changer
    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {

        //Save character race
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        playerCharacter.setProfession(classComboBox.getValue().toString());

        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);
        characterManager.save(playerCharacterList, "save.txt");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationStats.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void setClassChoiceBoxPicked() {
        String classChoice = classComboBox.getValue().toString();
        this.nextButton.setDisable(false);
        if (classChoice == "Pięść") {
            this.classDescription.setText(
                    "Wojownicy, bandyci, dzikusy. Wszyscy polecający na sile swoich mięśni \n\nZdolności: \n+ do sprawności i walki wręcz \n\nEkwipunek: \nwłócznia i ciężka zbroja"
            );
            classImage.setImage(new Image("Warrior.jpg"));
        } else if (classChoice == "Oczko") {
            this.classDescription.setText(
                    "Tropiciele, strzelcy i gangsterzy. Wprawieni w broni dystansowej i znajdowaniu poszlak. \n\nZdolności: \n+ do broni dystansowej i przetrwania \n\nEkwipunek: \nstrzelba i lekka zbroja"
            );
            classImage.setImage(new Image("Hunter.jpg"));
        } else if (classChoice == "Mózg") {
            this.classDescription.setText(
                    "Naukowcy i lekarze. Polegający na sile swojego umysłu. \n\nZdolności: \n+ do medycyny i nauki \n\nEkwipunek: \nlekarstwa i książka z przed-wojenną wiedzą"
            );
            classImage.setImage(new Image("Doctor.jpg"));
        } else if (classChoice == "Rączka") {
            this.classDescription.setText(
                    "Inżynierzy i mechanicy. Operujący praktycznymi umiejętnościami. \n\nZdolności: \n+ do naprawy i pojazdów \n\nEkwipunek: \nsprzęt do napraw i motor"
            );
            classImage.setImage(new Image("Mechanic.jpg"));
        } else if (classChoice == "Cień") {
            this.classDescription.setText(
                    "Złodzieje i szpiedzy. Wolący pozostać w cieniu.   \n\nZdolności: \n+ do skradania i włamywania się \n\nEkwipunek: \nwytrychy i sztylet"
            );
            classImage.setImage(new Image("Thief.jpg"));
        } else if (classChoice == "Buźka") {
            this.classDescription.setText(
                    "Kupcy i dyplomaci. Ludzie polegający na retoryce.  \n\nZdolności: \n+ do mowy i handlu \n\nEkwipunek: \n30 monet i pistolet"
            );
            classImage.setImage(new Image("Merchant.jpg"));
        }else {
            this.classDescription.setText(
                    "Wybierz klasę powyżej"
            );
        }
    }

}
