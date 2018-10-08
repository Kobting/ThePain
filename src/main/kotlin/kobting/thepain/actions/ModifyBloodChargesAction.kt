package kobting.thepain.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import kobting.thepain.patches.JCharacterPatches

class ModifyBloodChargesAction(amount: Int) : AbstractGameAction() {

    private var DURATION: Float = 0.0f

    init{
        this.amount = amount
        this.duration = DURATION
    }

    override fun update() {
        if(this.duration == DURATION) {
            JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount = this.amount
        }
        this.tickDuration()
    }
}