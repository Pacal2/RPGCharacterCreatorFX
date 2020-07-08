package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerCharacter implements Serializable {

    //Vital traits
    private String race;
    private String profession;
    //Statistics
    Map<String, Integer> stats = new LinkedHashMap<>();


    public PlayerCharacter() {
        // bazowe statystyki
        this.stats.put("Strength", 1);
        this.stats.put("Dexterity", 1);
        this.stats.put("Endurance", 1);
        this.stats.put("Intelligence", 1);
        this.stats.put("Charisma", 1);
        this.stats.put("Perception", 1);

    }

    public PlayerCharacter(String race, String profession, int strength, int dexterity, int endurance, int intelligence, int charisma, int perception) {
        this.race = race;
        this.profession = profession;
        // bazowe statystyki
        this.stats.put("Strength", 1);
        this.stats.put("Dexterity", 1);
        this.stats.put("Endurance", 1);
        this.stats.put("Intelligence", 1);
        this.stats.put("Charisma", 1);
        this.stats.put("Perception", 1);
    }

    public PlayerCharacter(String race, String profession) {
        this.race = race;
        this.profession = profession;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<String, Integer> stats) {
        this.stats = stats;
    }

    public void setSpecificStat(Map<String, Integer> stats, String statName, Integer numberToReplace) {
        this.stats.replace(statName, numberToReplace);
    }
}
