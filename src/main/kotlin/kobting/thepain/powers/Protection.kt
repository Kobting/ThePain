package kobting.thepain.powers

import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.powers.AbstractPower
import kobting.thepain.helpers.PowerHelper
import kobting.thepain.helpers.RelicHelper

class Protection(owner: AbstractCreature, amount: Int) : AbstractPower() {

    companion object {

        @JvmStatic
        var id = "thepain:Protection"

        private val powerStrings = CardCrawlGame.languagePack.getPowerStrings(id)
        val NAME = powerStrings.NAME
        val DESCRIPTIONS = powerStrings.DESCRIPTIONS
    }

    init {
        this.owner = owner
        this.type = AbstractPower.PowerType.BUFF
        this.amount = amount
        this.ID = id
        this.name = NAME
        this.description = DESCRIPTIONS[0]
        this.isTurnBased = true
        this.img = ImageMaster.loadImage(PowerHelper.getImagePath(id))
        this.priority = 98
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        if (isPlayer) {
            val player = AbstractDungeon.player
            if(player.hasPower(Blood.id)){
                AbstractDungeon.actionManager.addToTop(HealAction(this.owner, this.owner, player.getPower(Blood.id).amount))
                flash()
            }
        }
        val player = AbstractDungeon.player
        val power = player.getPower(id)
        if(power.amount <= 0){
            AbstractDungeon.actionManager.addToBottom(RemoveSpecificPowerAction(owner, owner, this.ID))
        } else {
            AbstractDungeon.actionManager.addToBottom(ReducePowerAction(player,player,power,1))
        }
    }

}
