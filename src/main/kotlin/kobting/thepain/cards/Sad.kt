package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Sad : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF) {
    init {
        this.magicNumber = SELF_DAMAGE
        this.baseMagicNumber = this.magicNumber
        this.exhaust = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDescription()
            upgradeMagicNumber(UPGRADE_SELF_DAMAGE)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Sad()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS)))
        val happy = Happy()
        if (upgraded) happy.upgrade()
        AbstractDungeon.actionManager.addToBottom(MakeTempCardInDiscardAction(happy, 1))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Sad"

        private val COST = 0
        private val SELF_DAMAGE = 3
        private val UPGRADE_SELF_DAMAGE = 2
    }
}
