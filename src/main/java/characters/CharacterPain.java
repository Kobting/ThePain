package characters;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import enums.ThePainCharacterEnum;

import java.util.ArrayList;

public class CharacterPain extends AbstractPlayer {


    public CharacterPain(String name, PlayerClass setClass) {
        super(name, setClass);

        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass("images/main.png", "images/shoulder2.png",
                "images/shoulder.png",
                "images/characters/corpse.png",
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));

        initializeStarterRelics(this.chosenClass);

    }


    public static CharSelectInfo getLoadout() {

        CharSelectInfo info = new CharSelectInfo(
                "The Pain",
                "Use HP as a resource for killing",
                80,
                80,
                0,
                99,
                5,
                ThePainCharacterEnum.THE_PAIN,
                getStartingRelics(),
                getStartingDeck(),
                false);

        return info;

    }


    public static ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();

        deck.add("Strike_PAIN");
        deck.add("Strike_PAIN");
        deck.add("Strike_PAIN");
        deck.add("Strike_PAIN");
        deck.add("Strike_PAIN");
        deck.add("Defend_PAIN");
        deck.add("Defend_PAIN");
        deck.add("Defend_PAIN");
        deck.add("Defend_PAIN");
        deck.add("Defend_PAIN");
        deck.add("Cut_Dry");


        return deck;
    }

    public static ArrayList<String> getStartingRelics(){
        ArrayList<String> relics = new ArrayList<>();

        relics.add("Shattered_Glass");

        return relics;
    }
}
