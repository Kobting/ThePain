package kobting.thepain.cards

import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Light : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_MAROON, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE) {
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
        return Light()
    }

    override fun canUse(p: AbstractPlayer?, m: AbstractMonster?): Boolean {
        this.cantUseMessage = "Must be Discarded."
        return false
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {}

    override fun triggerOnManualDiscard() {
        AbstractDungeon.player.heal(this.magicNumber)
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Light"

        private val COST = -2
        private val HEAL_AMT = 5
        private val UPGRADE_HEAL_AMT = 2
    }
}
