package kobting.thepain.helpers

import basemod.BaseMod
import basemod.interfaces.EditRelicsSubscriber
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.relics.BloodBag
import kobting.thepain.relics.ShatteredGlass

object RelicHelper : EditRelicsSubscriber{

    private var imagePaths = ImageMap("kobting.thepain.relics.", "kobting/thepain/images/relics/", "thepain:")
    private var DEFAULT_IMAGE_NAME = "arcanosphere.png"
    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditRelics() {

        initImagePaths()
        //Starter relic
        BaseMod.addRelicToCustomPool(ShatteredGlass(), AbstractCardEnum.THE_PAIN_PURPLE)

        //Common relics


        //Uncommon relics
        BaseMod.addRelicToCustomPool(BloodBag(), AbstractCardEnum.THE_PAIN_PURPLE)
    }



    fun getImagePath(ID: String) : String {
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }

    private fun initImagePaths(){
        imagePaths[ShatteredGlass::class.java.name] = "shattered_glass.png"

    }
}