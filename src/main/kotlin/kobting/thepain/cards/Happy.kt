package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Happy : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF) {
    init {
        this.magicNumber = HEAL_AMT
        this.baseMagicNumber = this.magicNumber
        this.exhaust = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDescription()
            upgradeMagicNumber(UPGRADE_HEAL_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Happy()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
        val sad = Sad()
        if (upgraded) sad.upgrade()
        AbstractDungeon.actionManager.addToBottom(MakeTempCardInDiscardAction(sad, 1))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Happy"

        private val COST = 1
        private val HEAL_AMT = 3
        private val UPGRADE_HEAL_AMT = 2
    }

}
