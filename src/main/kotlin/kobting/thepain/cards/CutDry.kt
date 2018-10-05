package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class CutDry : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF) {
    init {
        this.baseMagicNumber = HEAL_AMT
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBaseCost(0)
        }
    }

    override fun makeCopy(): AbstractCard {
        return CutDry()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        //Not using default damage variable to prevent weakness and strength from effecting the self damage.
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, SELF_DMG, DamageInfo.DamageType.HP_LOSS)))

        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
    }

    companion object {

        @JvmStatic
        val ID = "thepain:CutDry"

        private val COST = 1
        private val SELF_DMG = 2
        private val HEAL_AMT = 3
    }
}
