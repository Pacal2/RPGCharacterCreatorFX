package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerCharacter implements Serializable {

    //ID
    private int ID;
    //Vital traits
    private String name;
    private String race;
    private String gender;
    private String profession;
    //Statistics
    private Map<String, Integer> stats = new LinkedHashMap<>();
    private Map<String, Integer> skills = new LinkedHashMap<>();
    //Equipment
    private ArrayList<Item> equipment;
    //Finished
    private boolean finished = false;

    public PlayerCharacter() {
        statReset();
        skillReset();
    }

    public void skillReset() {
        // skills
        int baseSkillAmount = 10;
        // physical skins
        this.skills.put("Melee", baseSkillAmount);
        this.skills.put("Marksmanship", baseSkillAmount);
        this.skills.put("Athletics", baseSkillAmount);
        // knowledge skills
        this.skills.put("Medicine", baseSkillAmount);
        this.skills.put("Repair", baseSkillAmount);
        this.skills.put("Science", baseSkillAmount);
        this.skills.put("Vehicles", baseSkillAmount);
        // stealth skills
        this.skills.put("Lockpick", baseSkillAmount);
        this.skills.put("Sneak", baseSkillAmount);
        this.skills.put("Survival", baseSkillAmount);
        // rhetoric skills
        this.skills.put("Barter", baseSkillAmount);
        this.skills.put("Speech", baseSkillAmount);
        //equipment
        this.equipment = new ArrayList<>();
    }

    public void statReset() {
        // basic attributes
        int baseStatAmount = 1;
        this.stats.put("Strength", baseStatAmount);
        this.stats.put("Dexterity", baseStatAmount);
        this.stats.put("Endurance", baseStatAmount);
        this.stats.put("Intelligence", baseStatAmount);
        this.stats.put("Charisma", baseStatAmount);
        this.stats.put("Perception", baseStatAmount);
    }

    public PlayerCharacter(String name, String race, String profession, int strength, int dexterity, int endurance, int intelligence, int charisma, int perception) {
        this.name = name;
        this.race = race;
        this.profession = profession;
        // basic attributes
        int baseStatAmount = 1;
        this.stats.put("Strength", baseStatAmount);
        this.stats.put("Dexterity", baseStatAmount);
        this.stats.put("Endurance", baseStatAmount);
        this.stats.put("Intelligence", baseStatAmount);
        this.stats.put("Charisma", baseStatAmount);
        this.stats.put("Perception", baseStatAmount);
        // skills
        int baseSkillAmount = 10;
        // physical skins
        this.skills.put("Melee", baseSkillAmount);
        this.skills.put("Marksmanship", baseSkillAmount);
        this.skills.put("Athletics", baseSkillAmount);
        // knowledge skills
        this.skills.put("Medicine", baseSkillAmount);
        this.skills.put("Repair", baseSkillAmount);
        this.skills.put("Science", baseSkillAmount);
        this.skills.put("Vehicles", baseSkillAmount);
        // stealth skills
        this.skills.put("Lockpick", baseSkillAmount);
        this.skills.put("Sneak", baseSkillAmount);
        this.skills.put("Survival", baseSkillAmount);
        // rhetoric skills
        this.skills.put("Barter", baseSkillAmount);
        this.skills.put("Speech", baseSkillAmount);

    }

    public PlayerCharacter(String race, String profession) {
        this.race = race;
        this.profession = profession;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public void findItemAndAdd(String itemName, int amount) {

        boolean found = false;

        for(Item equipmentToList : equipment) {
            if (equipmentToList.getName().equals(itemName)) {
                equipmentToList.addAmount(amount);
                found = true;
            }
        }

        if (!found) {
            this.equipment.add(new Item(itemName, amount));
        }




    }

    public ArrayList<Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Item> equipment) {
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
