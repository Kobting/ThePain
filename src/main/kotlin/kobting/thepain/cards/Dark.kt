package kobting.thepain.cards

import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Dark : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE) {
    init {
        this.magicNumber = LOSE_HP_AMT
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeMagicNumber(UPGRADE_LOSE_HP_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Dark()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {

    }

    override fun canUse(p: AbstractPlayer?, m: AbstractMonster?): Boolean {
        this.cantUseMessage = "Must be Discarded."
        return false
    }

    override fun triggerOnManualDiscard() {
        AbstractDungeon.player.damage(DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.HP_LOSS))
    }

    companion object {

        @JvmStatic
        val ID = "thepain:Dark"

        private val COST = -2
        private val LOSE_HP_AMT = 3
        private val UPGRADE_LOSE_HP_AMT = 2
    }
}
