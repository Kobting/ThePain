package kobting.thepain.relics

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.helpers.RelicHelper

open class PainCustomRelic(private var id: String, tier: RelicTier, landingSound: LandingSound) : AbstractRelic(id, "", tier, landingSound) {

    init {
        //println("ThePain: Relic Classname ${this::class.java.name}")
        val imageUrl = RelicHelper.getImagePath(id)
        val outlineImageUrl = RelicHelper.getOutlineImagePath(id)?: imageUrl
        val image = Texture(imageUrl)
        val outlineImage = Texture(outlineImageUrl)
        image.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        outlineImage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        this.img = image
        this.largeImg = image
        this.outlineImg = outlineImage
    }



    override fun makeCopy(): AbstractRelic {
        try {
            return this::class.java.newInstance() as AbstractRelic
        } catch (exception: IllegalAccessException) {
            throw RuntimeException("Failed to auto-generate makeCopy for relic: $id")
        } catch (exception: InstantiationException) {
            throw RuntimeException("Failed to auto-generate makeCopy for relic: $id")
        }
    }


}