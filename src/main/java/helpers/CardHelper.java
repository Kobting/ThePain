package helpers;

import basemod.BaseMod;
import cards.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CardHelper {

    private MHashMap imagePaths;
    private final String DEFAULT_IMAGE_PATH = "beta_purple.png";

    private static CardHelper instance;

    private CardHelper(){
        initImagePaths();
    }

    public static CardHelper getInstance(){

        if(instance == null){
            instance = new CardHelper();
        }

        return instance;
    }

    public void addAllCards(){

        //Basic Starter Cards
        BaseMod.addCard(new Strike_Pain());     //Attack
        BaseMod.addCard(new Defend_Pain());           //Skill
        BaseMod.addCard(new CutDry());                //Skill

        //Common Cards
        BaseMod.addCard(new Cuts());                  //Attack
        BaseMod.addCard(new Bandaid());               //Skill
        BaseMod.addCard(new Cripple());               //Skill
        BaseMod.addCard(new SpikedShield());          //Skill
        BaseMod.addCard(new Flatten());               //Attack
        BaseMod.addCard(new RepairBoot());            //Attack
        BaseMod.addCard(new Dark());                  //Skill

        //UnCommon Cards
        BaseMod.addCard(new Bleed());                 //Skill
        BaseMod.addCard(new Sacrifice());             //Skill
        BaseMod.addCard(new Lost());                  //Skill
        BaseMod.addCard(new Light());                 //Skill
        BaseMod.addCard(new Sad());                   //Skill

        //Rare Cards
        BaseMod.addCard(new DropOfBlood());           //Skill
        BaseMod.addCard(new LegBreak());              //Skill
        BaseMod.addCard(new DeepCut());               //Attack

        //Other Cards
        BaseMod.addCard(new Happy());                 //Skill

    }

    private void initImagePaths(){
        imagePaths = new MHashMap();
        //There's probably a better way to do this.
        //Requires all IDs to match their class name.
        imagePaths.put(Bandaid.class.getName(),      DEFAULT_IMAGE_PATH);
        imagePaths.put(Bleed.class.getName(),        "bleed.png");
        imagePaths.put(Cripple.class.getName(),      DEFAULT_IMAGE_PATH);
        imagePaths.put(CutDry.class.getName(),       DEFAULT_IMAGE_PATH);
        imagePaths.put(Cuts.class.getName(),         DEFAULT_IMAGE_PATH);
        imagePaths.put(Dark.class.getName(),         DEFAULT_IMAGE_PATH);
        imagePaths.put(DeepCut.class.getName(),      DEFAULT_IMAGE_PATH);
        imagePaths.put(Defend_Pain.class.getName(),  "defend_purple.png");
        imagePaths.put(DropOfBlood.class.getName(),  DEFAULT_IMAGE_PATH);
        imagePaths.put(Flatten.class.getName(),      DEFAULT_IMAGE_PATH);
        imagePaths.put(Happy.class.getName(),        DEFAULT_IMAGE_PATH);
        imagePaths.put(LegBreak.class.getName(),     DEFAULT_IMAGE_PATH);
        imagePaths.put(Light.class.getName(),        DEFAULT_IMAGE_PATH);
        imagePaths.put(Lost.class.getName(),         DEFAULT_IMAGE_PATH);
        imagePaths.put(RepairBoot.class.getName(),   DEFAULT_IMAGE_PATH);
        imagePaths.put(Sacrifice.class.getName(),    DEFAULT_IMAGE_PATH);
        imagePaths.put(Sad.class.getName(),          DEFAULT_IMAGE_PATH);
        imagePaths.put(SpikedShield.class.getName(), DEFAULT_IMAGE_PATH);
        imagePaths.put(Strike_Pain.class.getName(),  "strike_purple.png");
    }



    public String getImagePath(String ID){
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_PATH);
    }


    private class MHashMap extends HashMap<String,String> {

        private final String CARD_PACKAGE = "cards.";
        private final String IMAGES_PATH = "images/cards/";

        //If any card names start to collide with the game or other mods
        //make this a custom name.
        private final String MOD_STRING = "pain:";

        @Override
        public String put(String key, String value) {
            return super.put(MOD_STRING + key.replace(CARD_PACKAGE, ""), IMAGES_PATH + value);
        }

        @Override
        public String getOrDefault(Object key, String defaultValue) {
            String _key = (String) key;
            return super.getOrDefault(MOD_STRING + _key, IMAGES_PATH + defaultValue);
        }

        @Override
        public String get(Object key) {
            String _key = (String) key;
            return super.get(MOD_STRING + _key);
        }
    }
}
