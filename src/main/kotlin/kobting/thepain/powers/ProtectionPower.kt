package kobting.thepain.powers

import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower

class ProtectionPower(owner: AbstractCreature) : AbstractPower() {

    init {
        this.owner = owner
        this.type = AbstractPower.PowerType.BUFF
        this.amount = 1
        this.ID = "ProtectionPower"
        this.name = "Protection"
        this.description = AbstractPower.DESCRIPTIONS[0]
        this.img = Texture("images/powers/protection.png")
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        if (isPlayer) {
            val player = AbstractDungeon.player
            if(player.hasPower(BloodPower.id)){
                AbstractDungeon.actionManager.addToBottom(HealAction(this.owner, this.owner, player.getPower(BloodPower.id).amount))
            }
        }
        flash()
        AbstractDungeon.actionManager.addToBottom(RemoveSpecificPowerAction(owner, owner, this.ID))
    }

}
