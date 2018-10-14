package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Relief : PainCustomCard(ID,COST,CardType.POWER, AbstractCardEnum.THE_PAIN_MAROON, CardRarity.UNCOMMON, CardTarget.SELF) {

    companion object {

        @JvmStatic
        val ID = "thepain:Relief"
        private val COST = 2
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(abstractPlayer, abstractPlayer, kobting.thepain.powers.Relief(abstractPlayer as AbstractCreature, 0)))
    }

    override fun upgrade() {
        if(!upgraded) {
            this.upgradeName()
            this.upgradeBaseCost(1)
        }
    }


}