package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class SpikedShield : PainCustomCard(ID, COST, AbstractCard.CardType.SKILL, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF) {
    init {
        this.baseBlock = BLOCK_AMT
        this.block = this.baseBlock
        this.baseMagicNumber = SELF_DMG_AMT
        this.magicNumber = this.baseMagicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeBlock(UPGRADE_PLUS_BLOCK)
            upgradeMagicNumber(UPGRADE_SELF_DMG_ATM)
        }
    }

    override fun makeCopy(): AbstractCard {
        return SpikedShield()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractPlayer, DamageInfo(abstractPlayer, this.magicNumber, DamageInfo.DamageType.HP_LOSS)))
        AbstractDungeon.actionManager.addToBottom(GainBlockAction(abstractPlayer, abstractPlayer, this.block))
    }

    companion object {

        val ID = "thepain:SpikedShield"

        private val COST = 1
        private val BLOCK_AMT = 10
        private val UPGRADE_PLUS_BLOCK = 2
        private val SELF_DMG_AMT = 2
        private val UPGRADE_SELF_DMG_ATM = 2
    }
}
