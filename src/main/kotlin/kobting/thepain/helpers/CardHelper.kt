package kobting.thepain.helpers

import basemod.BaseMod
import kobting.thepain.cards.*

import java.util.HashMap

object CardHelper {

    private var imagePaths: MHashMap? = null
    private val DEFAULT_IMAGE_NAME = "beta_purple.png"

    init {
        initImagePaths()
    }

    fun addAllCards() {

        //15 cards total in reward pool

        //Basic Starter Cards
        BaseMod.addCard(Strike_Pain())           //Attack
        BaseMod.addCard(Defend_Pain())           //Skill
        BaseMod.addCard(CutDry())                //Skill

        //Common Cards
        BaseMod.addCard(Cuts())                  //Attack
        BaseMod.addCard(Bandaid())               //Skill
        BaseMod.addCard(Cripple())               //Skill
        BaseMod.addCard(SpikedShield())          //Skill
        BaseMod.addCard(Flatten())               //Attack
        BaseMod.addCard(RepairBoot())            //Attack
        BaseMod.addCard(Dark())                  //Skill

        //UnCommon Cards
        BaseMod.addCard(Bleed())                 //Skill
        BaseMod.addCard(Sacrifice())             //Skill
        BaseMod.addCard(Lost())                  //Skill
        BaseMod.addCard(Light())                 //Skill
        BaseMod.addCard(Sad())                   //Skill
        BaseMod.addCard(BloodySkewer())          //Attack

        //Rare Cards
        BaseMod.addCard(DropOfBlood())           //Skill
        BaseMod.addCard(LegBreak())              //Skill
        BaseMod.addCard(DeepCut())               //Attack

        //Other Cards
        BaseMod.addCard(Happy())                 //Skill

    }

    private fun initImagePaths() {
        imagePaths = MHashMap()
        //There's probably a better way to do this.
        //Requires all IDs to match their class name.
        imagePaths!![Bandaid::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Bleed::class.java.name] = "bleed.png"
        imagePaths!![Cripple::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![CutDry::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Cuts::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Dark::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![DeepCut::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Defend_Pain::class.java.name] = "defend_purple.png"
        imagePaths!![DropOfBlood::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Flatten::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Happy::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![LegBreak::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Light::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Lost::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![RepairBoot::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Sacrifice::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Sad::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![SpikedShield::class.java.name] = DEFAULT_IMAGE_NAME
        imagePaths!![Strike_Pain::class.java.name] = "strike_purple.png"
    }


    fun getImagePath(ID: String): String {
        return imagePaths!!.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }


    private class MHashMap : HashMap<String, String>() {

        private val CARD_PACKAGE = "kobting.thapain.cards."
        private val IMAGES_PATH = "kobting/thepain/images/cards/"

        //If any card names start to collide with the game or other mods
        //make this a custom name.
        private val MOD_STRING = "pain:"

        override fun put(key: String, value: String): String? {
            return super.put(MOD_STRING + key.replace(CARD_PACKAGE, ""), IMAGES_PATH + value)
        }

        override fun getOrDefault(key: String, defaultValue: String): String {
            val _key = key
            return super.getOrDefault(MOD_STRING + _key, IMAGES_PATH + defaultValue)
        }

        override operator fun get(key: String): String? {
            val _key = key
            return super.get(MOD_STRING + _key)
        }
    }

}
