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

public class CharacterCreationEquipmentController implements Initializable {

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


        // Add class equipment
        System.out.println("Your equipment: " + playerCharacter.getEquipment());
        String playerClass = playerCharacter.getProfession();
        if (playerClass.equals("Pięść")) {
            playerCharacter.findItemAndAdd("Włócznia", 1);
            playerCharacter.findItemAndAdd("Ciężka zbroja", 1);
        }
        if (playerClass.equals("Oczko")) {
            playerCharacter.findItemAndAdd("Strzelba", 1);
            playerCharacter.findItemAndAdd("Lekka zbroja", 1);
        }
        if (playerClass.equals("Mózg")) {
            playerCharacter.findItemAndAdd("Lekarstwa", 3);
            playerCharacter.findItemAndAdd("Przed-wojenna książka", 1);
        }
        if (playerClass.equals("Rączka")) {
            playerCharacter.findItemAndAdd("Sprzęt do napraw", 3);
            playerCharacter.findItemAndAdd("Zepsuty przed-wojenny sprzęt", 1);
        }
        if (playerClass.equals("Cień")) {
            playerCharacter.findItemAndAdd("Wytrychy", 3);
            playerCharacter.findItemAndAdd("Sztylet", 1);
        }
        if (playerClass.equals("Buźka")) {
            playerCharacter.findItemAndAdd("Rewolwer", 1);
            playerCharacter.findItemAndAdd("Monety", 20);
        }


        // Add chosen equipment

        String chosenItem = itemComboBox.getValue().toString();

        if (chosenItem == "Prowiant") {
            playerCharacter.findItemAndAdd("Prowiant (dzienna porcja)", 7);
        }
        if (chosenItem == "Woda") {
            playerCharacter.findItemAndAdd("Zapas wody (dzienna porcja)", 7);
        }
        if (chosenItem == "Namiot i śpiwór") {
            playerCharacter.findItemAndAdd("Namiot i śpiwór", 1);
        }
        if (chosenItem == "Zapalniczka") {
            playerCharacter.findItemAndAdd("Zapalniczka", 1);
            playerCharacter.findItemAndAdd("Benzyna do zapalniczki (jedno napełnienie)", 7);
        }
        if (chosenItem == "Siekiera") {
            playerCharacter.findItemAndAdd("Siekiera", 1);
        }
        if (chosenItem == "Lina") {
            playerCharacter.findItemAndAdd("Lina", 7);
        }
        System.out.println("Your equipment: " + playerCharacter.getEquipment());

        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);

        characterManager.save(playerCharacterList, "save.txt");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationName.fxml"));
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
                    "Choć świat zdąrzył częściowo powstać z chaosu po katakliźmie to dalej jedzenie nie jest tak łatwe do zdobycia \n\nProwiant(dzienna porcja) x 7"
            );
            itemImage.setImage(new Image("Rations.jpg"));
        } else if (itemChoice == "Woda") {
            this.itemDescription.setText(
                    "Podstawa życia dla wszystkich istot inteligentnych poza androidami. \n\nZapas wody: x 7"
            );
            itemImage.setImage(new Image("Water.jpg"));
        } else if (itemChoice == "Namiot i śpiwór") {
            this.itemDescription.setText(
                    "Lepsze warunki do spania pozwalają szybciej regenerować zdrowie i kondycję \n\nNamiot i śpiwór x 1"
            );
            itemImage.setImage(new Image("Tent.jpg"));
        } else if (itemChoice == "Zapalniczka") {
            this.itemDescription.setText(
                    "Bardzo cenny przedmiot w post-apokaliptycznej rzeczywistości. \n\nZapalniczka x 1 \nBenzyna do zapalniczki (jedno napełnienie) x 7"
            );
            itemImage.setImage(new Image("Lighter.jpg"));
        } else if (itemChoice == "Siekiera") {
            this.itemDescription.setText(
                    "Może być użyta zarówno jako narzędzie i jako broń. \n\nSiekiera x 1"
            );
            itemImage.setImage(new Image("Axe.jpg"));
        } else if (itemChoice == "Lina") {
            this.itemDescription.setText(
                    "Kupcy i dyplomaci. Ludzie polegający na retoryce.  \n\nLina (1 m) x 7"
            );
            itemImage.setImage(new Image("Rope.jpg"));
        }else {
            this.itemDescription.setText(
                    "Wybierz dodatkowy przedmiot, który będzie częścią twojego ekwipunku"
            );
        }
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationSkills.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

}
