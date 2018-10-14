package kobting.thepain.helpers

import basemod.BaseMod
import basemod.interfaces.EditRelicsSubscriber
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.relics.BloodBag
import kobting.thepain.relics.ShatteredGlass

object RelicHelper : EditRelicsSubscriber{

    private var imagePaths = ImageMap( "kobting/thepain/images/relics/")
    private var outlineImagePaths = ImageMap("kobting/thepain/images/relics/")
    private var DEFAULT_IMAGE_NAME = "arcanosphere.png"
    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditRelics() {

        initImagePaths()
        //Starter relic
        BaseMod.addRelicToCustomPool(ShatteredGlass(), AbstractCardEnum.THE_PAIN_MAROON)

        //Common relics


        //Uncommon relics
        BaseMod.addRelicToCustomPool(BloodBag(), AbstractCardEnum.THE_PAIN_MAROON)
    }



    fun getImagePath(ID: String) : String {
        return imagePaths.getOrDefault(ID, DEFAULT_IMAGE_NAME)
    }

    fun getOutlineImagePath(ID: String) : String? {
        return outlineImagePaths[ID]
    }

    private fun initImagePaths(){
        imagePaths[ShatteredGlass.ID] = "shattered_glass.png"
        outlineImagePaths[ShatteredGlass.ID] = "outline-shattered_glass.png"

    }
}