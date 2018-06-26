package initliziers;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import characters.CharacterPain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.*;
import enums.AbstractCardEnum;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import enums.ThePainCharacterEnum;
import helpers.CardHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import relics.ShatteredGlass;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;


@SpireInitializer
public class ThePainInitializer implements
        EditCardsSubscriber, EditRelicsSubscriber,
        EditStringsSubscriber, EditCharactersSubscriber,
        EditKeywordsSubscriber{


    private static Logger logger;
    private static ArrayList<PainCustomCard> cards;


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

        CardHelper.getInstance().addAllCards();

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
        BaseMod.loadCustomStrings(PowerStrings.class, Gdx.files.internal("localization/eng/thepain-powers.json").readString(String.valueOf(StandardCharsets.UTF_8)));
        //BaseMod.loadCustomStrings(KeywordStrings.class, Gdx.files.internal("localization/eng/thepain-keywords.json").readString(String.valueOf(StandardCharsets.UTF_8)));
        BaseMod.loadCustomStrings(CardStrings.class, Gdx.files.internal("localization/eng/thepain-cards.json").readString(String.valueOf(StandardCharsets.UTF_8)));

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(CharacterPain.class, "The Pain", "The Pain Class", AbstractCardEnum.THE_PAIN_PURPLE.toString(),
                "The Pain", "images/characters/select.png", "images/characters/portrait.png", ThePainCharacterEnum.THE_PAIN.toString());
    }

    @Override
    public void receiveEditKeywords() {
        //https://github.com/twanvl/sts-mad-science-mod
        Type typeToken = new TypeToken<Map<String, Keyword>>(){}.getType();
        Gson gson = new Gson();
        String strings = Gdx.files.internal("localization/eng/thepain-keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        @SuppressWarnings("unchecked")
        Map<String,Keyword> keywords = (Map<String,Keyword>)gson.fromJson(strings, typeToken);
        for (Keyword kw : keywords.values()) {
            BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
        }
    }
}
