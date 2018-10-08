package kobting.thepain.powers

import com.megacrit.cardcrawl.core.AbstractCreature
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



}