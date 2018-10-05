package kobting.thepain.powers

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.powers.AbstractPower
import kobting.thepain.patches.PowerTypeEnum

class BloodPower(owner: AbstractCreature) : AbstractPower() {

    private var fromThorns: Boolean = false
    private var playerTurn: Boolean = false

    init {
        this.owner = owner
        this.type = PowerTypeEnum.NEUTRAL
        this.amount = 0
        this.ID = id
        this.name = NAME
        this.description = DESCRIPTIONS[0]
        this.img = ImageMaster.loadImage("kobting/thepain/images/powers/blood.png")
        playerTurn = true
        fromThorns = false
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        super.atEndOfTurn(isPlayer)
        if (isPlayer) {
            playerTurn = false
            this.amount = 0
        }
    }


    override fun atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw()
        playerTurn = true
        fromThorns = false
    }

    override fun atDamageReceive(damage: Float, damageType: DamageInfo.DamageType?): Float {
        if (damageType == DamageInfo.DamageType.THORNS && owner.currentBlock > damage) {
            fromThorns = true
        }
        return super.atDamageReceive(damage, damageType)
    }

    override fun onLoseHp(damageAmount: Int): Int {
        if (!fromThorns && playerTurn) {
            this.amount += damageAmount
        }
        fromThorns = false
        return super.onLoseHp(damageAmount)
    }

    override fun renderAmount(sb: SpriteBatch, x: Float, y: Float, c: Color) {
        super.renderAmount(sb, x, y, c)
        if (this.amount == 0) {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c)
        }
    }

    companion object {

        @JvmStatic
        var id = "thepain:Blood"

        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(id)
        val NAME = powerStrings.NAME
        private val DESCRIPTIONS = powerStrings.DESCRIPTIONS
    }
}
