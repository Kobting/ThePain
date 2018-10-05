package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.HealAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class RepairBoot : PainCustomCard(ID, COST, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY) {
    init {
        this.damage = ATTACK_DMG
        this.baseDamage = this.damage
        this.magicNumber = HEAL_AMT
        this.baseMagicNumber = this.magicNumber
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_PLUS_DMG)
        }
    }

    override fun makeCopy(): AbstractCard {
        return RepairBoot()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractMonster, DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)))
        AbstractDungeon.actionManager.addToBottom(HealAction(abstractPlayer, abstractPlayer, this.magicNumber))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:RepairBoot"

        private val COST = 2
        private val ATTACK_DMG = 15
        private val UPGRADE_PLUS_DMG = 5
        private val HEAL_AMT = 5
    }
}
