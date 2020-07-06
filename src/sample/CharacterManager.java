package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterManager implements Serializable {

    transient Scanner input;
    private ArrayList<PlayerCharacter> characters;

    public CharacterManager(){
        this.input = new Scanner(System.in);
        this.characters = new ArrayList();
    }


    public void save (ArrayList<PlayerCharacter> characterList, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(characterList);
        oos.close();
    }

    public void saveCharacterList(PlayerCharacter playerCharacter) throws IOException {
        this.characters.add(playerCharacter);
        save(characters, "save.txt");
    }

    public ArrayList<PlayerCharacter> load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (ArrayList<PlayerCharacter>) ois.readObject();
    }


}
