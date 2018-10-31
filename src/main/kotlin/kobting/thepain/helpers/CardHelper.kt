package kobting.thepain.helpers

import basemod.BaseMod
import basemod.interfaces.EditCardsSubscriber
import kobting.thepain.cards.*

object CardHelper : EditCardsSubscriber{
    private var imagePaths = ImageMap("kobting/thepain/images/cards/")
    private val DEFAULT_IMAGE_NAME = "beta_purple.png"

    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditCards() {

        initImagePaths()
        println("ThePain: Adding Cards")
        //23 cards total in reward pool
        //16 skills
        //6  attacks
        //2  powers

        //Basic Starter Cards 3
        BaseMod.addCard(Strike_Pain())           //Attack
        BaseMod.addCard(Defend_Pain())           //Skill
        BaseMod.addCard(CutDry())                //Skill

        //Common Cards 6
        BaseMod.addCard(Cuts())                  //Attack
        BaseMod.addCard(Bandaid())               //Skill
        BaseMod.addCard(Cripple())               //Skill
        BaseMod.addCard(SpikedShield())          //Skill
        BaseMod.addCard(RepairBoot())            //Attack
        BaseMod.addCard(Dark())                  //Skill

        //UnCommon Cards 9
        BaseMod.addCard(Gulp())                  //Skill
        BaseMod.addCard(Sacrifice())             //Skill
        BaseMod.addCard(Lost())                  //Skill
        BaseMod.addCard(Light())                 //Skill
        BaseMod.addCard(Sad())                   //Skill
        BaseMod.addCard(BloodySkewer())          //Attack
        BaseMod.addCard(Flatten())               //Attack
        BaseMod.addCard(Relief())                //Power
        BaseMod.addCard(Bleed())                 //Power

        //Rare Cards 4
        BaseMod.addCard(DropOfBlood())           //Skill
        BaseMod.addCard(LegBreak())              //Skill
        BaseMod.addCard(DeepCut())               //Attack
        BaseMod.addCard(DoubleTrouble())         //Skill
        BaseMod.addCard(LastResort())            //Skill

        //Other Cards 1
        BaseMod.addCard(Happy())                 //Skill

    }

    private fun initImagePaths() {
        println("ThePain: Creating Card Images")
        //There's probably a better way to do this.
        //Requires all IDs to match their class name.
        imagePaths.put(Bleed.ID,"bleed.png")
        imagePaths.put(Defend_Pain.ID,"defend_purple.png")
        imagePaths.put(Strike_Pain.ID,"strike_purple.png")

    }


    fun getImagePath(ID: String): String {
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }


}
