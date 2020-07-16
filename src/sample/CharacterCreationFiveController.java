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

public class CharacterCreationFiveController implements Initializable {

    @FXML
    private ComboBox itemComboBox;
    @FXML private ImageView itemImage;
    @FXML private TextArea itemDescription;
    @FXML private Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemComboBox.getItems().addAll("Prowiant", "Woda", "Namiot i śpiwór", "Zapalniczka", "Siekiera", "Lina");
        this.itemDescription.setText("Wybierz jeden z powyższych przedmiotów");
        this.nextButton.setDisable(true);
    }

    //Scene Changer
    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {

        //Save character race
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        playerCharacter.setProfession(itemComboBox.getValue().toString());
        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);
        characterManager.save(playerCharacterList, "save.txt");
        //characterManager.saveCharacterList(playerCharacter);


        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationThree.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void setItemChoiceBoxPicked() {
        String itemChoice = itemComboBox.getValue().toString();
        this.nextButton.setDisable(false);
        if (itemChoice == "Prowiant") {
            this.itemDescription.setText(
                    "Wojownicy, bandyci, dzikusy. Wszyscy polecający na sile swoich mięśni \n\nZdolności: \n+ do sprawności i walki wręcz \n\nEkwipunek: \nwłócznia i ciężka zbroja"
            );
            itemImage.setImage(new Image("Warrior.jpg"));
        } else if (itemChoice == "Woda") {
            this.itemDescription.setText(
                    "Tropiciele, strzelcy i gangsterzy. Wprawieni w broni dystansowej i znajdowaniu poszlak. \n\nZdolności: \n+ do broni dystansowej i przetrwania \n\nEkwipunek: \nstrzelba i lekka zbroja"
            );
            itemImage.setImage(new Image("Hunter.jpg"));
        } else if (itemChoice == "Namiot i śpiwór") {
            this.itemDescription.setText(
                    "Naukowcy i lekarze. Polegający na sile swojego umysłu. \n\nZdolności: \n+ do medycyny i nauki \n\nEkwipunek: \nlekarstwa i książka z przed-wojenną wiedzą"
            );
            itemImage.setImage(new Image("Doctor.jpg"));
        } else if (itemChoice == "Zapalniczka") {
            this.itemDescription.setText(
                    "Inżynierzy i mechanicy. Operujący praktycznymi umiejętnościami. \n\nZdolności: \n+ do naprawy i pojazdów \n\nEkwipunek: \nsprzęt do napraw i motor"
            );
            itemImage.setImage(new Image("Mechanic.jpg"));
        } else if (itemChoice == "Siekiera") {
            this.itemDescription.setText(
                    "Złodzieje i szpiedzy. Wolący pozostać w cieniu.   \n\nZdolności: \n+ do skradania i włamywania się \n\nEkwipunek: \nwytrychy i sztylet"
            );
            itemImage.setImage(new Image("Thief.jpg"));
        } else if (itemChoice == "Lina") {
            this.itemDescription.setText(
                    "Kupcy i dyplomaci. Ludzie polegający na retoryce.  \n\nZdolności: \n+ do mowy i handlu \n\nEkwipunek: \n30 monet i pistolet"
            );
            itemImage.setImage(new Image("Merchant.jpg"));
        }else {
            this.itemDescription.setText(
                    "Wybierz klasę powyżej"
            );
        }
    }

}
