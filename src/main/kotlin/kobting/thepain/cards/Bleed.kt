package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.powers.Bleed

class Bleed :
        PainCustomCard(
                ID,
                COST,
                CardType.POWER,
                AbstractCardEnum.THE_PAIN_MAROON,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        )

{

    companion object {

        @JvmStatic
        val ID = "thepain:Bleed"
        private val COST = 3
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(abstractPlayer, abstractPlayer, Bleed(abstractPlayer as AbstractCreature, -1)))
    }

    override fun upgrade() {
        if(!upgraded) {
            this.upgradeName()
            this.upgradeBaseCost(2)
        }
    }

}