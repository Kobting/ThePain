package kobting.thepain.cards

import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.actions.DiscardHealAction
import kobting.thepain.patches.AbstractCardEnum

class Lost : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF) {
    init {
        this.magicNumber = HEAL_AMT
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Lost()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        val healAction = DiscardHealAction(abstractPlayer as AbstractCreature, abstractPlayer, DISCARD_AMT, true, this.magicNumber)
        AbstractDungeon.actionManager.addToBottom(healAction)
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Lost"

        private val COST = 1
        private val DISCARD_AMT = 3
        private val HEAL_AMT = 1
        private val UPGRADE_HEAL_AMT = 1
    }


}
