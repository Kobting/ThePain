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

class Relief(owner: AbstractCreature, amount: Int) : PainCustomPower(id, owner, amount){

    companion object {
        @JvmStatic
        val id = "thepain:Relief"
    }

    init {
        this.type = PowerType.BUFF
        this.isTurnBased = false
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        val player = AbstractDungeon.player
        AbstractDungeon.actionManager.addToBottom(HealAction(player, player, 2))
    }

    override fun atStartOfTurn() {
        val player = AbstractDungeon.player
        println("ThePain: Relief endofturn damage")
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player, 2, DamageInfo.DamageType.HP_LOSS)))
    }
}