package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Defend_Pain : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF) {
    init {
        this.baseBlock = BLOCK_AMT
        this.block = this.baseBlock
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Defend_Pain()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(GainBlockAction(abstractPlayer, abstractPlayer, this.block))
    }

    companion object {

        @JvmStatic
        val ID = "thepain:Defend_Pain"

        private val COST = 1
        private val BLOCK_AMT = 5
        private val UPGRADE_PLUS_BLOCK = 3
    }
}
