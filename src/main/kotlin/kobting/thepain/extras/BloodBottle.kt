package kobting.thepain.extras

import basemod.ClickableUIElement
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.TipHelper
import com.megacrit.cardcrawl.helpers.input.InputHelper
import kobting.thepain.ThePainMod
import kobting.thepain.patches.JCharacterPatches

class BloodBottle(texture: Texture) : BloodBottleOnSelfDamage, ClickableUIElement(texture, 0f,0f, 128f, 128f) {

    private val xPos = 0f * Settings.scale
    private val yPos = 128f * Settings.scale
    private val textXPos = 64f * Settings.scale
    private val textYPos = 190f * Settings.scale
    private val tipText = "Every time you take #bself HP damage gain charges equal to that amount."
    var bloodCount: Int = 0
    var retainTurns: Int = 0

    init {
        ThePainMod.subscribeToOnSelfDamage(this)
        this.setX(xPos)
        this.setY(yPos)
    }


    override fun onSelfDamage(damage: Int) {
        bloodCount+= damage
    }

    override fun onUnhover() {

    }

    override fun onHover() {
        TipHelper.renderGenericTip(50.0f * Settings.scale, 380.0f * Settings.scale, "Blood Bottle", tipText)
    }

    override fun onClick() {
        if(Settings.isDebug) {
            JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount++
        }
    }

    override fun update() {
        super.update()

        if(this.hitbox.hovered && InputHelper.justClickedRight && Settings.isDebug) {
            JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount--
        }
    }

    override fun render(sb: SpriteBatch?) {
        super.render(sb)
        FontHelper.renderFontCentered(sb, FontHelper.energyNumFontRed, bloodCount.toString(), textXPos,textYPos, Color.WHITE)
    }

}