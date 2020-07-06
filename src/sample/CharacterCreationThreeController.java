package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CharacterCreationThreeController implements Initializable {

    @FXML private Spinner strengthSpinner;
    @FXML private Spinner enduranceSpinner;
    @FXML private Spinner dexteritySpinner;
    @FXML private Spinner intelligenceSpinner;
    @FXML private Spinner perceptionSpinner;
    @FXML private Spinner charismaSpinner;



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

        //Set up spending points
        int pointsLeft = 12;
        int maxAddStatistic = 5;

        //Strength Spinner
        SpinnerValueFactory<Integer> strengthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(baseStrength, baseStrength + maxAddStatistic, baseStrength);
        this.strengthSpinner.setValueFactory(strengthValueFactory);
        strengthSpinner.setEditable(true);

    }
}
