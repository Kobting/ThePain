package kobting.thepain.relics

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.relics.AbstractRelic
import kobting.thepain.helpers.RelicHelper

open class PainCustomRelic(private var id: String, tier: RelicTier, landingSound: LandingSound) : AbstractRelic(id, "", tier, landingSound) {


    init {
        val imageUrl = RelicHelper.getImagePath(id)
        val image = Texture(imageUrl)
        image.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        this.img = image
        this.largeImg = image
        this.outlineImg = image
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