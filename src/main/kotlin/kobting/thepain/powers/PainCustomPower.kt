package kobting.thepain.powers

import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower
import kobting.thepain.helpers.PowerHelper

open class PainCustomPower(id: String, owner: AbstractCreature, amount: Int) : AbstractPower() {

    private var powerStrings: PowerStrings? = null

    init {
        this.powerStrings = CardCrawlGame.languagePack.getPowerStrings(id)

        this.name = powerStrings?.NAME ?: "Error: No Name"

        this.description = powerStrings!!.DESCRIPTIONS[0] ?: arrayOf("Error: No Description. NL You didn't add this power to power strings.")[0]

        this.img = ImageMaster.loadImage(PowerHelper.getImagePath(id))

        this.ID = id
        this.owner = owner
        this.amount = amount
    }

}