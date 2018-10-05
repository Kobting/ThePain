package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Sacrifice : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF) {
    init {
        this.baseMagicNumber = CARD_DRAW_AMT
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_CARD_DRAW_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Sacrifice()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, SELF_DMG)))
        AbstractDungeon.actionManager.addToBottom(DrawCardAction(abstractMonster, this.magicNumber))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Sacrifice"

        private val COST = 1
        private val SELF_DMG = 2
        private val CARD_DRAW_AMT = 2
        private val UPGRADE_CARD_DRAW_AMT = 1
    }
}
