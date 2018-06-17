package initliziers;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.localization.RelicStrings;
import enums.AbstractCardEnum;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import enums.ThePainCharacterEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import relics.ShatteredGlass;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class ThePainInitializer implements
        EditCardsSubscriber, EditRelicsSubscriber,
        EditStringsSubscriber, EditCharactersSubscriber{

    public static final String DEFAULT_CARD_IMAGE_PATH = "images/cards/beta_purple.png";
    public static final String DEFEND_CARD_IMAGE_PATH = "images/cards/defend_purple.png";
    public static final String ATTACK_CARD_IMAGE_PATH = "images/cards/strike_purple.png";
    public static final String BLEED_CARD_IMAGE_PATH = "images/cards/bleed.png";

    private static Logger logger;


    public ThePainInitializer(){
        BaseMod.subscribe(this);
    }

    //Method needed for @SpireInitializer
    public static void initialize(){
        logger = LogManager.getLogger(ThePainInitializer.class.getName());

        logger.log(Level.INFO, "-------------Initializing: " + AbstractCardEnum.THE_PAIN_PURPLE.toString() + "----------------------");

        BaseMod.addColor(AbstractCardEnum.THE_PAIN_PURPLE.toString(), Color.PURPLE, Color.DARK_GRAY, Color.PURPLE, Color.BLACK, Color.LIGHT_GRAY, Color.BLUE, Color.PURPLE,
                "images/ui/bg_attack_purple.png", "images/ui/bg_skill_purple.png",
                "images/ui/bg_power_purple.png", "images/ui/card_purple_orb.png",
                "images/ui/bg_attack_purple_p.png", "images/ui/bg_skill_purple_p.png",
                "images/ui/bg_power_purple_p.png", "images/ui/card_purple_orb_p.png");

        ThePainInitializer mod = new ThePainInitializer();


        logger.log(Level.INFO, "-------------Finished Initializing: " + AbstractCardEnum.THE_PAIN_PURPLE.toString() + "----------------------");


    }

    public static Texture getStarterRelicTexture(){
        return new Texture("images/relics/arcanosphere.png");
    }

    @Override
    public void receiveEditCards() {

        logger.log(Level.INFO, "Adding new cards");

        //Basic Starter Cards
        BaseMod.addCard(new Strike_Pain());     //Attack
        BaseMod.addCard(new Defend_Pain());     //Skill
        BaseMod.addCard(new CutDry());          //Skill

        //Common Cards
        BaseMod.addCard(new Bleed());           //Skill
        BaseMod.addCard(new Cuts());            //Attack
        BaseMod.addCard(new Bandaid());         //Skill
        BaseMod.addCard(new Cripple());         //Skill
        BaseMod.addCard(new SpikedShield());    //Skill
        BaseMod.addCard(new Flatten());         //Attack

        //UnCommon Cards


        //Rare Cards
        BaseMod.addCard(new DropOfBlood());     //Skill

        logger.log(Level.INFO, "Finished adding new cards");
    }

    @Override
    public void receiveEditRelics() {

        //Both options cause null pointers

        BaseMod.addRelicToCustomPool(new ShatteredGlass(), AbstractCardEnum.THE_PAIN_PURPLE.toString());
        //RelicLibrary.add(new ShatteredGlass());
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStrings(RelicStrings.class, Gdx.files.internal("localization/eng/thepain-relics.json").readString(String.valueOf(StandardCharsets.UTF_8)));
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(CharacterPain.class, "The Pain", "The Pain Class", AbstractCardEnum.THE_PAIN_PURPLE.toString(),
                "The Pain", "images/select.png", "images/portrait.png", ThePainCharacterEnum.THE_PAIN.toString());
    }
}
