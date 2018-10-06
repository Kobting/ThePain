package kobting.thepain.helpers

import basemod.BaseMod
import basemod.interfaces.EditCardsSubscriber
import com.sun.media.jfxmedia.logging.Logger
import kobting.thepain.cards.*

import java.util.HashMap

object CardHelper : EditCardsSubscriber{
    private var imagePaths = ImageMap("kobting.thepain.cards.", "kobting/thepain/images/cards/", "thepain:")
    private val DEFAULT_IMAGE_NAME = "beta_purple.png"

    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditCards() {

        initImagePaths()
        println("ThePain: Adding Cards")
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
        BaseMod.addCard(Relief())                //Power

        //Rare Cards
        BaseMod.addCard(DropOfBlood())           //Skill
        BaseMod.addCard(LegBreak())              //Skill
        BaseMod.addCard(DeepCut())               //Attack

        //Other Cards
        BaseMod.addCard(Happy())                 //Skill

    }

    private fun initImagePaths() {
        println("ThePain: Creating Card Images")
        //There's probably a better way to do this.
        //Requires all IDs to match their class name.
        imagePaths.put(Bleed::class.java.name,"bleed.png")
        imagePaths.put(Defend_Pain::class.java.name,"defend_purple.png")
        imagePaths.put(Strike_Pain::class.java.name,"strike_purple.png")

    }


    fun getImagePath(ID: String): String {
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }


}
