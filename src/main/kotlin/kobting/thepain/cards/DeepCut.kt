package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum
import kobting.thepain.powers.Protection

class DeepCut : PainCustomCard(ID, COST, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF_AND_ENEMY) {
    init {
        this.damage = DAMAGE_AMT
        this.baseDamage = this.damage
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage(UPGRADE_DAMAGE_AMT)
        }
    }

    override fun makeCopy(): AbstractCard {
        return DeepCut()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractMonster, DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)))
        AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(abstractPlayer, abstractPlayer, Protection(abstractPlayer as AbstractCreature, 1), 1))
    }

    companion object {

        @JvmStatic
        val ID = "thepain:DeepCut"

        private val COST = 2
        private val DAMAGE_AMT = 10
        private val UPGRADE_DAMAGE_AMT = 5
    }
}
