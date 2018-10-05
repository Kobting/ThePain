package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Bandaid :
        PainCustomCard(
                ID,
                COST,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.THE_PAIN_PURPLE,
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.SELF)
{

    companion object {

        @JvmStatic
        val ID = "thepain:Bandaid"

        private const val COST = 1
        private const val BLOCK_AMT = 8
        private const val HEAL_AMT = 2
        private const val BLOCK_UPG_AMT = 2
        private const val HEAL_UPG = 1
    }

    init {
        this.baseBlock = BLOCK_AMT
        this.block = this.baseBlock
        this.baseMagicNumber = HEAL_AMT
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!this.upgraded) {
            this.upgradeName()
            this.upgradeBlock(BLOCK_UPG_AMT)
            this.upgradeMagicNumber(HEAL_UPG)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Bandaid()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(GainBlockAction(abstractPlayer, abstractPlayer, this.block))
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))

    }


}
