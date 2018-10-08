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

    //TODO: Fix so damage can be done at end of turn
    override fun update() {

        JCharacterPatches.bloodBottle_f.get(AbstractDungeon.player).bloodCount = this.amount
        this.isDone = true
        return

        //this.tickDuration()
    }
}