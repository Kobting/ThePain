package kobting.thepain.powers

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.powers.AbstractPower
import kobting.thepain.helpers.PowerHelper

class Relief(owner: AbstractCreature) : AbstractPower(){

    companion object {
        @JvmStatic
        val id = "thepain:Relief"

        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(id)
        val NAME = powerStrings.NAME
        private val DESCRIPTIONS = powerStrings.DESCRIPTIONS
    }

    init {
        this.owner = owner
        this.ID = id
        this.type = PowerType.BUFF
        this.amount = 0
        this.isTurnBased = false
        this.name = NAME
        this.description = DESCRIPTIONS[0]
        this.img = ImageMaster.loadImage(PowerHelper.getImagePath(id))

    }

    override fun atStartOfTurn() {
        val player = AbstractDungeon.player
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player, 2, DamageInfo.DamageType.HP_LOSS)))
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        val player = AbstractDungeon.player
        AbstractDungeon.actionManager.addToBottom(HealAction(player, player, 2))
    }
}