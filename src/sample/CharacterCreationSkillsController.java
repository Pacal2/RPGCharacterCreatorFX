package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class CharacterCreationSkillsController implements Initializable {


    //Spinners
    @FXML private Spinner meleeSpinner;
    @FXML private Spinner marksmanshipSpinner;
    @FXML private Spinner athleticsSpinner;

    @FXML private Spinner medicineSpinner;
    @FXML private Spinner repairSpinner;
    @FXML private Spinner scienceSpinner;
    @FXML private Spinner vehiclesSpinner;

    @FXML private Spinner lockpickSpinner;
    @FXML private Spinner sneakSpinner;
    @FXML private Spinner survivalSpinner;

    @FXML private Spinner barterSpinner;
    @FXML private Spinner speechSpinner;

    //Spinner list
    private ArrayList<Spinner> skillSpinners = new ArrayList<>();

    @FXML private Label numberOfPointsLeftLabel;


    //Set up spending points
    private final int maxPointsToSpend = 150;
    private final int maxAddSkill = 40;
    private final IntegerProperty pointsRemaining = new SimpleIntegerProperty(maxPointsToSpend);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Add spinners to list
        skillSpinners.add(meleeSpinner);
        skillSpinners.add(marksmanshipSpinner);
        skillSpinners.add(athleticsSpinner);
        skillSpinners.add(medicineSpinner);
        skillSpinners.add(repairSpinner);
        skillSpinners.add(scienceSpinner);
        skillSpinners.add(vehiclesSpinner);
        skillSpinners.add(lockpickSpinner);
        skillSpinners.add(sneakSpinner);
        skillSpinners.add(survivalSpinner);
        skillSpinners.add(barterSpinner);
        skillSpinners.add(speechSpinner);

        // Set up player Character
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = null;
        try {
            playerCharacterList = characterManager.load("save.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        // Assign class bonuses
        String playerClass = playerCharacter.getProfession();
        int classBonus = 10;
        if (playerClass.equals("Pięść")) {
            skillBonus(playerCharacter, "Melee", classBonus);
            skillBonus(playerCharacter, "Athletics", classBonus);
        }
        if (playerClass.equals("Oczko")) {
            skillBonus(playerCharacter, "Masksmanship", classBonus);
            skillBonus(playerCharacter, "Survival", classBonus);
        }
        if (playerClass.equals("Mózg")) {
            skillBonus(playerCharacter, "Science", classBonus);
            skillBonus(playerCharacter, "Medicine", classBonus);
        }
        if (playerClass.equals("Rączka")) {
            skillBonus(playerCharacter, "Repair", classBonus);
            skillBonus(playerCharacter, "Vehicles", classBonus);
        }
        if (playerClass.equals("Cień")) {
            skillBonus(playerCharacter, "Sneak", classBonus);
            skillBonus(playerCharacter, "Lockpick", classBonus);
        }
        if (playerClass.equals("Buźka")) {
            skillBonus(playerCharacter, "Speech", classBonus);
            skillBonus(playerCharacter, "Barter", classBonus);
        }

        // Points Left
        numberOfPointsLeftLabel.textProperty().bind(Bindings.format("%d", pointsRemaining));

        // Spinner controller
        int i = 0;
        for(Map.Entry<String, Integer> skill : playerCharacter.getSkills().entrySet()) {
            skillSpinnerMethod(skillSpinners.get(i), skill.getValue());
            System.out.println("Integer is " + i + skillSpinners.get(i) + " and attribute is: " + skill.getKey().toString() + "/n");
            i++;
        }


    }

    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {

        //Alert when skill points left
        if (pointsRemaining.getValue() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("You still have points to spend");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        //Save character race
        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        // Add modified attributes
        int i = 0;
        for(Map.Entry<String, Integer> skill : playerCharacter.getSkills().entrySet()) {
            System.out.println(skillSpinners.get(i).getValue().toString());
            skill.setValue((Integer) skillSpinners.get(i).getValue());
            i++;
        }



        playerCharacterList.set(playerCharacterList.size()-1, playerCharacter);
        characterManager.save(playerCharacterList, "save.txt");

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationEquipment.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    private void skillBonus(PlayerCharacter playerCharacter, String skillToIncrease, Integer valueToAdd) {
        Integer originalValue = playerCharacter.getSkills().get(skillToIncrease);
        playerCharacter.getSkills().replace(skillToIncrease, originalValue + valueToAdd);
    }

    private void skillSpinnerMethod(Spinner spinner, int baseValue) {
        final int maxSkill = baseValue + maxAddSkill;
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(baseValue, maxSkill, baseValue);
        valueFactory.valueProperty().addListener((obs, oldValue, newValue) -> pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        valueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> Math.min(maxSkill, valueFactory.getValue() + pointsRemaining.get()),
                pointsRemaining, valueFactory.valueProperty()));
        spinner.setValueFactory(valueFactory);

    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CharacterCreationStats.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
