package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.WeakPower
import kobting.thepain.patches.AbstractCardEnum

class Cripple : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF_AND_ENEMY) {
    init {
        this.baseMagicNumber = HEAL_AMT
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!this.upgraded) {
            this.upgradeName()
            this.upgradeMagicNumber(HEAL_UPG_AMT)

        }
    }

    override fun makeCopy(): AbstractCard {
        return Cripple()
    }


    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(abstractMonster, abstractPlayer, WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber))
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
    }

    companion object {

        @JvmStatic
        val ID = "thepain:Cripple"

        private val COST = 2
        private val HEAL_AMT = 3
        private val HEAL_UPG_AMT = 2
    }
}
