package kobting.thepain.powers

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import kobting.thepain.patches.PowerTypeEnum

class Bleed(owner: AbstractCreature, amount: Int) : PainCustomPower(id, owner, amount) {


    companion object {

        @JvmStatic
        var id = "thepain:Bleed"
    }

    init {
        this.type = PowerTypeEnum.NEUTRAL
        this.isTurnBased = true
    }

    override fun atStartOfTurn() {
        super.atStartOfTurn()
        var player = AbstractDungeon.player
        AbstractDungeon.actionManager.addToBottom(DamageAction(player, DamageInfo(player,2, DamageInfo.DamageType.HP_LOSS)))
        AbstractDungeon.actionManager.addToBottom(DrawCardAction(player, 1))
    }


}