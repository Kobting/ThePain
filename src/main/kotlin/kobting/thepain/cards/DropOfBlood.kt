package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class DropOfBlood : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF) {
    init {
        this.baseMagicNumber = HEAL_AMT
        this.magicNumber = this.baseMagicNumber
        this.exhaust = true
        this.isEthereal = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return DropOfBlood()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:DropOfBlood"

        private val COST = 2
        private val HEAL_AMT = 15
        private val UPGRADE_HEAL_AMT = 5
    }
}
