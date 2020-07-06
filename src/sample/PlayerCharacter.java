package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class PlayerCharacter implements Serializable {

    //Vital traits
    private String race;
    private String profession;
    //Statistics
    private int strength;
    private int dexterity;
    private int endurance;
    private int intelligence;
    private int charisma;
    private int perception;

    public PlayerCharacter() {
        // bazowe statystyki
        this.strength = 1;
        this.dexterity = 1;
        this.endurance = 1;
        this.intelligence = 1;
        this.charisma = 1;
        this.perception = 1;


    }

    public PlayerCharacter(String race, String profession, int strength, int dexterity, int endurance, int intelligence, int charisma, int perception) {
        this.race = race;
        this.profession = profession;
        this.strength = 1;
        this.dexterity = 1;
        this.endurance = 1;
        this.intelligence = 1;
        this.charisma = 1;
        this.perception = 1;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getPerception() {
        return perception;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }
}
