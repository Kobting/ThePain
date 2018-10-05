package kobting.thepain.cards

import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import kobting.thepain.patches.AbstractCardEnum

class Strike_Pain : PainCustomCard(ID, COST, AbstractCard.CardType.ATTACK, AbstractCardEnum.THE_PAIN_PURPLE, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY) {
    init {
        this.baseDamage = ATTACK_DMG
        this.damage = this.baseDamage
    }

    override fun upgrade() {
        if (!upgraded) {
            this.upgradeName()
            this.upgradeDamage(UPGRADE_PLUS_DMG)
        }
    }

    override fun makeCopy(): AbstractCard {
        return Strike_Pain()
    }

    override fun use(abstractPlayer: AbstractPlayer?, abstractMonster: AbstractMonster?) {
        AbstractDungeon.actionManager.addToBottom(DamageAction(abstractMonster, DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL)))
    }

    companion object {
        @JvmStatic
        val ID = "thepain:Strike_Pain"

        private val COST = 1
        private val ATTACK_DMG = 6
        private val UPGRADE_PLUS_DMG = 3
    }
}
