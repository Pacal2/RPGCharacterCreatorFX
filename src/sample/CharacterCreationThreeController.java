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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

public class CharacterCreationThreeController implements Initializable {

    //Spinners
    @FXML private Spinner strengthSpinner;
    @FXML private Spinner dexteritySpinner;
    @FXML private Spinner enduranceSpinner;

    @FXML private Spinner intelligenceSpinner;
    @FXML private Spinner charismaSpinner;
    @FXML private Spinner perceptionSpinner;

    //Spinner list
    private ArrayList<Spinner> attributeSpinners = new ArrayList<>();

    @FXML private Label numberOfPointsLeftLabel;


    //Set up spending points
    private final int maxPointsToSpend = 10;
    private final int maxAddStatistic = 4;
    private final IntegerProperty pointsRemaining = new SimpleIntegerProperty(maxPointsToSpend);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set up Spinner List
        attributeSpinners.add(strengthSpinner);
        attributeSpinners.add(dexteritySpinner);
        attributeSpinners.add(enduranceSpinner);
        attributeSpinners.add(intelligenceSpinner);
        attributeSpinners.add(charismaSpinner);
        attributeSpinners.add(perceptionSpinner);

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

        // Assign racial bonuses
        String playerRace = playerCharacter.getRace();
        if (playerRace.equals("Człowiek")) attributeBonus(playerCharacter, "Charisma", 1);
        if (playerRace.equals("Mutant")) attributeBonus(playerCharacter, "Strength", 1);
        if (playerRace.equals("Przybysz")) attributeBonus(playerCharacter, "Endurance", 1);
        if (playerRace.equals("Android")) attributeBonus(playerCharacter, "Intelligence", 1);

        // Points Left
        numberOfPointsLeftLabel.textProperty().bind(Bindings.format("%d", pointsRemaining));

        // Spinner controller
        int i = 0;
        for(Map.Entry<String, Integer> attribute : playerCharacter.getStats().entrySet()) {
            attributeSpinnerMethod(attributeSpinners.get(i), attribute.getValue());
            System.out.println("Integer is " + i + attributeSpinners.get(i) + " and attribute is: " +attribute.getKey().toString() + "/n");
            i++;
        }

    }

    private void attributeSpinnerMethod(Spinner spinner, int baseValue) {
        final int maxAttribute = baseValue + maxAddStatistic;
        IntegerSpinnerValueFactory valueFactory = new IntegerSpinnerValueFactory(baseValue, maxAttribute, baseValue);
        valueFactory.valueProperty().addListener((obs, oldValue, newValue) -> pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        valueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> Math.min(maxAttribute, valueFactory.getValue() + pointsRemaining.get()),
                pointsRemaining, valueFactory.valueProperty()));
        spinner.setValueFactory(valueFactory);

    }

    private void attributeBonus(PlayerCharacter playerCharacter, String attributeToIncrease, Integer valueToAdd) {
        Integer originalValue = playerCharacter.getStats().get(attributeToIncrease);
        playerCharacter.getStats().replace(attributeToIncrease, originalValue + valueToAdd);
    }

    //Scene Changer
    public void nextButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException {

        //Save character race


        CharacterManager characterManager = new CharacterManager();
        ArrayList<PlayerCharacter> playerCharacterList = characterManager.load("save.txt");
        PlayerCharacter playerCharacter = playerCharacterList.get(playerCharacterList.size()-1);

        // Add modified attributes
        int i = 0;
        for(Map.Entry<String, Integer> attribute : playerCharacter.getStats().entrySet()) {
            System.out.println(attributeSpinners.get(i).getValue().toString());
            attribute.setValue((Integer) attributeSpinners.get(i).getValue());
            i++;
        }


        characterManager.saveCharacterList(playerCharacter);

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //Get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
