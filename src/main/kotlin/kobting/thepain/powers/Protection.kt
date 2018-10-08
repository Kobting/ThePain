package kobting.thepain.powers

import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower
import kobting.thepain.patches.JCharacterPatches

class Protection(owner: AbstractCreature, amount: Int) : PainCustomPower(id, owner, amount) {

    companion object {

        @JvmStatic
        var id = "thepain:Protection"
    }

    init {
        this.type = AbstractPower.PowerType.BUFF
        this.isTurnBased = true
        this.priority = 98
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        if (isPlayer) {
            val player = AbstractDungeon.player
            val bloodCount = JCharacterPatches.bloodBottle_f.get(player).bloodCount
            AbstractDungeon.actionManager.addToTop(HealAction(this.owner, this.owner, bloodCount))
            flash()
        }
        decrement()
    }

    private fun decrement(){
        val player = AbstractDungeon.player
        val power = player.getPower(id)
        if(power.amount <= 0){
            AbstractDungeon.actionManager.addToBottom(RemoveSpecificPowerAction(owner, owner, this.ID))
        } else {
            AbstractDungeon.actionManager.addToBottom(ReducePowerAction(player,player,power,1))
        }
    }

}
