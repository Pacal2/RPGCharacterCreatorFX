package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class CharacterCreationThreeController implements Initializable {

    @FXML private Spinner strengthSpinner;
    @FXML private Spinner enduranceSpinner;
    @FXML private Spinner dexteritySpinner;
    @FXML private Spinner intelligenceSpinner;
    @FXML private Spinner perceptionSpinner;
    @FXML private Spinner charismaSpinner;

    @FXML private Label numberOfPointsLeftLabel;


    //Set up spending points
    private int maxPointsToSpend = 10;
    private int maxAddStatistic = 4;
    private IntegerProperty pointsRemaining = new SimpleIntegerProperty(maxPointsToSpend);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        if (playerRace.equals("Człowiek")) playerCharacter.setCharisma(playerCharacter.getCharisma()+1);
        if (playerRace.equals("Mutant")) playerCharacter.setStrength(playerCharacter.getStrength()+1);
        if (playerRace.equals("Przybysz")) playerCharacter.setEndurance(playerCharacter.getEndurance()+1);
        if (playerRace.equals("Android")) playerCharacter.setIntelligence(playerCharacter.getIntelligence()+1);

        //Extract starting attributes:
        int baseStrength = playerCharacter.getStrength();
        int baseEndurance = playerCharacter.getEndurance();
        int baseDexterity = playerCharacter.getDexterity();
        int baseIntelligence = playerCharacter.getIntelligence();
        int basePerception = playerCharacter.getPerception();
        int baseCharisma = playerCharacter.getCharisma();

        //Points Left
        numberOfPointsLeftLabel.textProperty().bind(Bindings.format("%d", pointsRemaining));

        //Strength Spinner
        SpinnerValueFactory.IntegerSpinnerValueFactory strengthValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(baseStrength, baseStrength + maxAddStatistic, baseStrength);
        strengthValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + strengthValueFactory.getValue(),
                pointsRemaining, strengthValueFactory.valueProperty()));
        strengthValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.strengthSpinner.setValueFactory(strengthValueFactory);
        //Endurance Spinner
        IntegerSpinnerValueFactory enduranceValueFactory =
                new IntegerSpinnerValueFactory(baseEndurance, baseEndurance + maxAddStatistic, baseEndurance);
        enduranceValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + enduranceValueFactory.getValue(),
                pointsRemaining, enduranceValueFactory.valueProperty()));
        enduranceValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.enduranceSpinner.setValueFactory(enduranceValueFactory);
        //Dexterity Spinner
        IntegerSpinnerValueFactory dexterityValueFactory =
                new IntegerSpinnerValueFactory(baseDexterity, baseDexterity + maxAddStatistic, baseDexterity);
        dexterityValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + dexterityValueFactory.getValue(),
                pointsRemaining, dexterityValueFactory.valueProperty()));
        dexterityValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.dexteritySpinner.setValueFactory(dexterityValueFactory);
        //Intelligence Spinner
        IntegerSpinnerValueFactory intelligenceValueFactory =
                new IntegerSpinnerValueFactory(baseIntelligence, baseIntelligence + maxAddStatistic, baseIntelligence);
        intelligenceValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + intelligenceValueFactory.getValue(),
                pointsRemaining, intelligenceValueFactory.valueProperty()));
        intelligenceValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.intelligenceSpinner.setValueFactory(intelligenceValueFactory);
        //Perception Spinner
        IntegerSpinnerValueFactory perceptionValueFactory =
                new IntegerSpinnerValueFactory(basePerception, basePerception + maxAddStatistic, basePerception);
        perceptionValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + perceptionValueFactory.getValue(),
                pointsRemaining, perceptionValueFactory.valueProperty()));
        perceptionValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.perceptionSpinner.setValueFactory(perceptionValueFactory);
        //Charisma Spinner
        IntegerSpinnerValueFactory charismaValueFactory =
                new IntegerSpinnerValueFactory(baseCharisma, baseCharisma + maxAddStatistic, baseCharisma);
        charismaValueFactory.maxProperty().bind(Bindings.createIntegerBinding(
                () -> pointsRemaining.get() + charismaValueFactory.getValue(),
                pointsRemaining, charismaValueFactory.valueProperty()));
        charismaValueFactory.valueProperty().addListener((obs, oldValue, newValue) ->
                pointsRemaining.set(pointsRemaining.get() - newValue + oldValue));
        this.charismaSpinner.setValueFactory(charismaValueFactory);

    }


}
